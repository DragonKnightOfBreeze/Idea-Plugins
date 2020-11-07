@file:Suppress("NAME_SHADOWING", "UNUSED_PARAMETER", "MapGetWithNotNullAssertionOperator")

package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.codeInsight.*
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.icons.*
import com.intellij.ide.*
import com.intellij.json.*
import com.intellij.json.psi.*
import com.intellij.lang.*
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.editor.actionSystem.*
import com.intellij.openapi.editor.actions.*
import com.intellij.openapi.project.*
import com.intellij.openapi.util.text.*
import com.intellij.openapi.vfs.*
import com.intellij.openapi.vfs.impl.http.*
import com.intellij.psi.*
import com.intellij.psi.codeStyle.*
import com.intellij.psi.impl.source.tree.*
import com.intellij.psi.injection.*
import com.intellij.util.*
import com.intellij.util.containers.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.extension.adapters.*
import com.jetbrains.jsonSchema.ide.*
import com.jetbrains.jsonSchema.impl.*
import com.windea.plugin.idea.stellaris.*
import java.util.*
import java.util.HashSet
import javax.swing.*

//com.jetbrains.jsonSchema.impl.JsonSchemaCompletionContributor
//org.jetbrains.yaml.schema.YamlJsonSchemaCompletionContributor

//需要包含作为前缀的字符到结果中
//需要将结果中的冒号替换为等号

class StellarisScriptSchemaCompletionContributor : CompletionContributor() {
	override fun beforeCompletion(context: CompletionInitializationContext) {
		context.dummyIdentifier = ""
	}

	override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
		val position = parameters.position
		val prefix = position.prevSibling?.text
		val resultWithPrefix = if(prefix == null) result else result.withPrefixMatcher(prefix)
		val jsonSchemaService = JsonSchemaService.Impl.get(position.project)
		val schemaObject = jsonSchemaService.getSchemaObject(parameters.originalFile) ?: return
		doCompletion(parameters, resultWithPrefix, schemaObject, false)
	}

	private class Worker(private val myRootSchema: JsonSchemaObject, private val myPosition: PsiElement,
		private val myOriginalPosition: PsiElement, resultConsumer: Consumer<LookupElement>) {
		private val myResultConsumer: Consumer<LookupElement> = resultConsumer
		private val myWrapInQuotes: Boolean = myPosition.parent !is JsonStringLiteral
		private val myInsideStringLiteral: Boolean = myPosition.parent is JsonStringLiteral

		// we need this set to filter same-named suggestions (they can be suggested by several matching schemes)
		private val myVariants: MutableSet<LookupElement> = HashSet()
		private val myWalker: JsonLikePsiWalker? = JsonLikePsiWalker.getWalker(myPosition, myRootSchema)
		private val myProject: Project = myOriginalPosition.project

		fun work() {
			if(myWalker == null) return
			val checkable = myWalker.findElementToCheck(myPosition) ?: return
			val isName = myWalker.isName(checkable)
			val position = myWalker.findPosition(checkable, isName == ThreeState.NO)
			if(position == null || position.isEmpty && isName == ThreeState.NO) return
			val schemas = JsonSchemaResolver(myProject, myRootSchema, position).resolve()
			val knownNames = HashSet<String>()
			// too long here, refactor further
			schemas.forEach { schema ->
				if(isName != ThreeState.NO) {
					val insertComma = myWalker.hasMissingCommaAfter(myPosition)
					val hasValue = myWalker.isPropertyWithValue(checkable)
					val properties = myWalker.getPropertyNamesOfParentObject(myOriginalPosition, myPosition)
					val adapter = myWalker.getParentPropertyAdapter(myOriginalPosition)
					val schemaProperties = schema.properties
					addAllPropertyVariants(insertComma, hasValue, properties, adapter, schemaProperties, knownNames)
					addIfThenElsePropertyNameVariants(schema, insertComma, hasValue, properties, adapter, knownNames)
					addPropertyNameSchemaVariants(schema)
				}
				if(isName != ThreeState.YES) {
					suggestValues(schema, isName == ThreeState.NO)
				}
			}
			for(variant in myVariants) {
				myResultConsumer.consume(variant)
			}
		}

		private fun addPropertyNameSchemaVariants(schema: JsonSchemaObject) {
			val propertyNamesSchema = schema.propertyNamesSchema ?: return
			val anEnum = propertyNamesSchema.enum ?: return
			for(o in anEnum) {
				if(o !is String) continue
				var key: String? = o
				key = if(!shouldWrapInQuotes(key)) key else StringUtil.wrapWithDoubleQuote(key!!)
				myVariants.add(LookupElementBuilder.create(StringUtil.unquoteString(key!!)))
			}
		}

		private fun addIfThenElsePropertyNameVariants(
			schema: JsonSchemaObject,
			insertComma: Boolean,
			hasValue: Boolean,
			properties: Collection<String>,
			adapter: JsonPropertyAdapter?,
			knownNames: MutableSet<String>
		) {
			val ifThenElseList = schema.ifThenElse ?: return
			val walker = JsonLikePsiWalker.getWalker(myPosition, schema)
			val propertyAdapter = walker?.getParentPropertyAdapter(myPosition) ?: return
			val parentObject = propertyAdapter.parentObject ?: return
			for(ifThenElse in ifThenElseList) {
				try {
					val constructor = JsonSchemaAnnotatorChecker::class.java.getConstructor(Project::class.java, JsonComplianceCheckerOptions::class.java)
					constructor.trySetAccessible()
					val checker = constructor.newInstance(myProject, JsonComplianceCheckerOptions.RELAX_ENUM_CHECK)
					//JsonSchemaAnnotatorChecker checker0 = new JsonSchemaAnnotatorChecker(myProject, JsonComplianceCheckerOptions.RELAX_ENUM_CHECK);
					checker.checkByScheme(parentObject, ifThenElse.getIf())
					if(checker.isCorrect) {
						val then = ifThenElse.then
						if(then != null) {
							addAllPropertyVariants(insertComma, hasValue, properties, adapter, then.properties, knownNames)
						}
					} else {
						val schemaElse = ifThenElse.getElse()
						if(schemaElse != null) {
							addAllPropertyVariants(insertComma, hasValue, properties, adapter, schemaElse.properties, knownNames)
						}
					}
				} catch(e: Exception) {
					e.printStackTrace()
				}
			}
		}

		private fun addAllPropertyVariants(
			insertComma: Boolean,
			hasValue: Boolean,
			properties: Collection<String>,
			adapter: JsonPropertyAdapter?,
			schemaProperties: Map<String, JsonSchemaObject>,
			knownNames: MutableSet<String>
		) {
			schemaProperties.keys.filter { name ->
				!properties.contains(name) && !knownNames.contains(name) || adapter != null && (name == adapter.name)
			}.forEach { name ->
				knownNames.add(name)
				addPropertyVariant(name, schemaProperties[name]!!, hasValue, insertComma)
			}
		}

		private fun suggestValues(schema: JsonSchemaObject, isSurelyValue: Boolean) {
			suggestValuesForSchemaVariants(schema.anyOf, isSurelyValue)
			suggestValuesForSchemaVariants(schema.oneOf, isSurelyValue)
			suggestValuesForSchemaVariants(schema.allOf, isSurelyValue)
			if(schema.enum != null) {
				val metadata = schema.enumMetadata
				for(o in schema.enum!!) {
					if(myInsideStringLiteral && o !is String) continue
					val variant = o.toString()
					if(!filtered.contains(variant)) {
						val valueMetadata = metadata?.get(StringUtil.unquoteString(variant))
						val description = valueMetadata?.get("description")
						val deprecated = valueMetadata?.get("deprecationMessage")
						addValueVariant(variant, description,
							if(deprecated != null) "$variant ($deprecated)" else null, null)
					}
				}
			} else if(isSurelyValue) {
				val type = schema.guessType()
				suggestSpecialValues(type)
				if(type != null) {
					suggestByType(schema, type)
				} else if(schema.typeVariants != null) {
					for(schemaType in schema.typeVariants!!) {
						suggestByType(schema, schemaType)
					}
				}
			}
		}

		private fun suggestSpecialValues(type: JsonSchemaType?) {
			if(JsonSchemaVersion.isSchemaSchemaId(myRootSchema.id) && type == JsonSchemaType._string) {
				val propertyAdapter = myWalker!!.getParentPropertyAdapter(myOriginalPosition) ?: return
				val name = propertyAdapter.name ?: return
				when(name) {
					"required" -> {
						addRequiredPropVariants()
					}
					JsonSchemaObject.X_INTELLIJ_LANGUAGE_INJECTION -> {
						addInjectedLanguageVariants()
					}
					"language" -> {
						val parent = propertyAdapter.parentObject
						if(parent != null) {
							val adapter = myWalker.getParentPropertyAdapter(parent.delegate)
							if(adapter != null && (JsonSchemaObject.X_INTELLIJ_LANGUAGE_INJECTION == adapter.name)) {
								addInjectedLanguageVariants()
							}
						}
					}
				}
			}
		}

		private fun addInjectedLanguageVariants() {
			val checkable = myWalker!!.findElementToCheck(myPosition)
			if(checkable !is JsonStringLiteral && checkable !is JsonReferenceExpression) return
			JBIterable.from(Language.getRegisteredLanguages())
				.filter { language -> LanguageUtil.isInjectableLanguage(language!!) }
				.map { language -> Injectable.fromLanguage(language) }
				.forEach { injectable ->
					myVariants.add(LookupElementBuilder
						.create(injectable.id)
						.withIcon(injectable.icon)
						.withTailText("(" + injectable.displayName + ")", true))
				}
		}

		private fun addRequiredPropVariants() {
			val checkable = myWalker!!.findElementToCheck(myPosition)
			if(checkable !is JsonStringLiteral && checkable !is JsonReferenceExpression) return
			val propertiesObject = JsonRequiredPropsReferenceProvider.findPropertiesObject(checkable) ?: return
			val items = when(val parent = checkable.parent) {
				is JsonArray -> parent.valueList.filterIsInstance<JsonStringLiteral>().map { it.value }.toSet()
				else -> setOf()
			}
			propertiesObject.propertyList.map { it.name }.filter { !items.contains(it) }.forEach { addStringVariant(it) }
		}

		private fun suggestByType(schema: JsonSchemaObject, type: JsonSchemaType) {
			if(JsonSchemaType._string == type) {
				addPossibleStringValue(schema)
			}
			if(myInsideStringLiteral) {
				return
			}
			when {
				JsonSchemaType._boolean == type -> {
					addPossibleBooleanValue(type)
				}
				JsonSchemaType._null == type -> {
					addValueVariant("null", null)
				}
				JsonSchemaType._array == type -> {
					val value = myWalker!!.defaultArrayValue
					addValueVariant(value, null, "{...}", createArrayOrObjectLiteralInsertHandler(value.length))
				}
				JsonSchemaType._object == type -> {
					val value = myWalker!!.defaultObjectValue
					addValueVariant(value, null, "{...}", createArrayOrObjectLiteralInsertHandler(value.length))
				}
			}
		}

		private fun addPossibleStringValue(schema: JsonSchemaObject) {
			val defaultValue = schema.default
			val defaultValueString = defaultValue?.toString()
			addStringVariant(defaultValueString)
		}

		private fun addStringVariant(defaultValueString: String?) {
			if(!StringUtil.isEmpty(defaultValueString)) {
				var normalizedValue = defaultValueString!!
				val shouldQuote = myWalker!!.requiresValueQuotes()
				val isQuoted = StringUtil.isQuotedString(normalizedValue)
				if(shouldQuote && !isQuoted) {
					normalizedValue = StringUtil.wrapWithDoubleQuote(normalizedValue)
				} else if(!shouldQuote && isQuoted) {
					normalizedValue = StringUtil.unquoteString(normalizedValue)
				}
				addValueVariant(normalizedValue, null)
			}
		}

		private fun suggestValuesForSchemaVariants(list: List<JsonSchemaObject>?, isSurelyValue: Boolean) {
			if(list != null && list.isNotEmpty()) {
				for(schemaObject in list) {
					suggestValues(schemaObject, isSurelyValue)
				}
			}
		}

		private fun addPossibleBooleanValue(type: JsonSchemaType) {
			if(JsonSchemaType._boolean == type) {
				addValueVariant("yes", null)
				addValueVariant("no", null)
			}
		}

		private fun addValueVariant(key: String, description: String?, altText: String? = null,
			handler: InsertHandler<LookupElement>? = null) {
			val unquoted = StringUtil.unquoteString(key)
			var builder = LookupElementBuilder.create(if(!shouldWrapInQuotes(unquoted)) unquoted else key)
			if(altText != null) builder = builder.withPresentableText(altText)
			if(description != null) builder = builder.withTypeText(description)
			if(handler != null) builder = builder.withInsertHandler(handler)
			myVariants.add(builder)
		}

		private fun shouldWrapInQuotes(key: String?): Boolean {
			//当提示的字符串包含空格时，才由双引号包围
			return key != null && key.containsBlank()
		}

		private fun addPropertyVariant(key: String, jsonSchemaObject: JsonSchemaObject, hasValue: Boolean, insertComma: Boolean) {
			var key = key
			var jsonSchemaObject = jsonSchemaObject
			val variants = JsonSchemaResolver(myProject, jsonSchemaObject).resolve()
			jsonSchemaObject = ObjectUtils.coalesce(ContainerUtil.getFirstItem(variants), jsonSchemaObject)
			key = if(!shouldWrapInQuotes(key)) key else StringUtil.wrapWithDoubleQuote(key)
			var builder = LookupElementBuilder.create(key)
			val typeText = JsonSchemaDocumentationProvider.getBestDocumentation(true, jsonSchemaObject)
			if(!StringUtil.isEmptyOrSpaces(typeText)) {
				val text = StringUtil.removeHtmlTags(typeText!!)
				val firstSentenceMark = text.indexOf(". ")
				builder = builder
					.withTypeText(if(firstSentenceMark == -1) text else text.substring(0, firstSentenceMark + 1), true)
			} else {
				val type = jsonSchemaObject.getTypeDescription(true)
				if(type != null) {
					builder = builder.withTypeText(type, true)
				}
			}
			builder = builder.withIcon(getIcon(jsonSchemaObject.guessType()))
			builder = if(hasSameType(variants)) {
				val type = jsonSchemaObject.guessType()
				val values = jsonSchemaObject.enum
				val defaultValue = jsonSchemaObject.default
				val hasValues = !ContainerUtil.isEmpty(values)
				if(type != null || hasValues || defaultValue != null) {
					builder.withInsertHandler(
						if(!hasValues || values!!.map { it.javaClass }.distinct().count() == 1) {
							createPropertyInsertHandler(jsonSchemaObject, hasValue, insertComma)
						} else {
							createDefaultPropertyInsertHandler(true, insertComma)
						})
				} else {
					builder.withInsertHandler(createDefaultPropertyInsertHandler(hasValue, insertComma))
				}
			} else {
				builder.withInsertHandler(createDefaultPropertyInsertHandler(hasValue, insertComma))
			}
			val deprecationMessage = jsonSchemaObject.deprecationMessage
			if(deprecationMessage != null) {
				builder = builder.withTailText(JsonBundle.message("schema.documentation.deprecated.postfix"), true)
					.withStrikeoutness(true)
			}
			myVariants.add(builder)
		}

		private fun createDefaultPropertyInsertHandler(hasValue: Boolean, insertComma: Boolean): InsertHandler<LookupElement> {
			return object : InsertHandler<LookupElement> {
				override fun handleInsert(context: InsertionContext, item: LookupElement) {
					ApplicationManager.getApplication().assertWriteAccessAllowed()
					val editor = context.editor
					val project = context.project
					if(handleInsideQuotesInsertion(context, editor, hasValue)) return
					var offset = editor.caretModel.offset
					val initialOffset = offset
					val docChars = context.document.charsSequence
					while(offset < docChars.length && Character.isWhitespace(docChars[offset])) {
						offset++
					}
					if(hasValue) {
						// fix colon for YAML and alike
						if(offset < docChars.length && docChars[offset] != SEPARATOR_CHAR) {
							editor.document.insertString(initialOffset, SEPARATOR)
							handleWhitespaceAfterColon(editor, docChars, initialOffset + 1)
						}
						return
					}
					if(offset < docChars.length && docChars[offset] == SEPARATOR_CHAR) {
						handleWhitespaceAfterColon(editor, docChars, offset + 1)
					} else {
						// inserting longer string for proper formatting
						val stringToInsert = SEPARATOR + "1" + if(insertComma) "," else ""
						EditorModificationUtil.insertStringAtCaret(editor, stringToInsert, false, true, SEPARATOR_LENGTH)
						formatInsertedString(context, stringToInsert.length)
						offset = editor.caretModel.offset
						context.document.deleteString(offset, offset + 1)
					}
					PsiDocumentManager.getInstance(project).commitDocument(editor.document)
					AutoPopupController.getInstance(context.project).autoPopupMemberLookup(context.editor, null)
				}

				fun handleWhitespaceAfterColon(editor: Editor, docChars: CharSequence, nextOffset: Int) {
					if(nextOffset < docChars.length && docChars[nextOffset] == ' ') {
						editor.caretModel.moveToOffset(nextOffset + 1)
					} else {
						editor.caretModel.moveToOffset(nextOffset)
						EditorModificationUtil.insertStringAtCaret(editor, " ", false, true, 1)
					}
				}
			}
		}

		private fun createPropertyInsertHandler(jsonSchemaObject: JsonSchemaObject, hasValue: Boolean, insertComma: Boolean): InsertHandler<LookupElement> {
			var type = jsonSchemaObject.guessType()
			val values = jsonSchemaObject.enum
			if(type == null && values != null && values.isNotEmpty()) type = detectType(values)
			val defaultValueAsString = when(val defaultValue = jsonSchemaObject.default) {
				null, is JsonSchemaObject -> null
				is String -> "\"" + defaultValue + "\""
				else -> defaultValue.toString()
			}
			val finalType = type
			return InsertHandler { context, _ ->
				ApplicationManager.getApplication().assertWriteAccessAllowed()
				val editor = context.editor
				val project = context.project
				var stringToInsert: String? = null
				val comma = if(insertComma) "," else ""
				if(handleInsideQuotesInsertion(context, editor, hasValue)) return@InsertHandler
				val element = context.file.findElementAt(editor.caretModel.offset)
				val insertColon = element == null || SEPARATOR != element.text
				if(!insertColon) {
					editor.caretModel.moveToOffset(editor.caretModel.offset + 1)
				}
				if(finalType != null) {
					var hadEnter: Boolean
					when(finalType) {
						JsonSchemaType._object -> {
							EditorModificationUtil.insertStringAtCaret(editor, if(insertColon) SEPARATOR else " ", false, true, if(insertColon) SEPARATOR_LENGTH else 1)
							hadEnter = false
							val invokeEnter = myWalker!!.hasWhitespaceDelimitedCodeBlocks()
							if(insertColon && invokeEnter) {
								invokeEnterHandler(editor)
								hadEnter = true
							}
							if(insertColon) {
								stringToInsert = myWalker.defaultObjectValue + comma
								EditorModificationUtil.insertStringAtCaret(editor, stringToInsert, false, true, if(hadEnter) 0 else 1)
							}
							if(hadEnter || !insertColon) {
								EditorActionUtil.moveCaretToLineEnd(editor, false, false)
							}
							PsiDocumentManager.getInstance(project).commitDocument(editor.document)
							if(!hadEnter && stringToInsert != null) {
								formatInsertedString(context, stringToInsert.length)
							}
							if(stringToInsert != null && !invokeEnter) {
								invokeEnterHandler(editor)
							}
						}
						JsonSchemaType._boolean -> {
							val value = (java.lang.Boolean.TRUE.toString() == defaultValueAsString).toString()
							stringToInsert = (if(insertColon) SEPARATOR else " ") + value + comma
							val model = editor.selectionModel
							EditorModificationUtil.insertStringAtCaret(editor, stringToInsert, false, true, stringToInsert.length - comma.length)
							formatInsertedString(context, stringToInsert.length)
							val start = editor.selectionModel.selectionStart
							model.setSelection(start - value.length, start)
							AutoPopupController.getInstance(context.project).autoPopupMemberLookup(context.editor, null)
						}
						JsonSchemaType._array -> {
							EditorModificationUtil.insertStringAtCaret(editor, if(insertColon) SEPARATOR else " ", false, true, if(insertColon) SEPARATOR_LENGTH else 1)
							hadEnter = false
							if(insertColon && myWalker!!.hasWhitespaceDelimitedCodeBlocks()) {
								invokeEnterHandler(editor)
								hadEnter = true
							}
							if(insertColon) {
								stringToInsert = myWalker!!.defaultArrayValue + comma
								EditorModificationUtil.insertStringAtCaret(editor, stringToInsert, false, true, if(hadEnter) 0 else 1)
							}
							if(hadEnter) {
								EditorActionUtil.moveCaretToLineEnd(editor, false, false)
							}
							PsiDocumentManager.getInstance(project).commitDocument(editor.document)
							if(stringToInsert != null) {
								formatInsertedString(context, stringToInsert.length)
							}
						}
						JsonSchemaType._string, JsonSchemaType._integer, JsonSchemaType._number -> {
							insertPropertyWithEnum(context, editor, defaultValueAsString, values, finalType, comma, myWalker, insertColon)
						}
						else -> {}
					}
				} else {
					insertPropertyWithEnum(context, editor, defaultValueAsString, values, null, comma, myWalker, insertColon)
				}
			}
		}

		private fun handleInsideQuotesInsertion(context: InsertionContext, editor: Editor, hasValue: Boolean): Boolean {
			if(myInsideStringLiteral) {
				val offset = editor.caretModel.offset
				val element = context.file.findElementAt(offset)
				val tailOffset = context.tailOffset
				val guessEndOffset = tailOffset + 1
				if(element is LeafPsiElement) {
					if(handleIncompleteString(editor, element)) return false
					val endOffset = element.getTextRange().endOffset
					if(endOffset > tailOffset) {
						context.document.deleteString(tailOffset, endOffset - 1)
					}
				}
				if(hasValue) {
					return true
				}
				editor.caretModel.moveToOffset(guessEndOffset)
			} else {
				editor.caretModel.moveToOffset(context.tailOffset)
			}
			return false
		}

		companion object {
			// some schemas provide empty array / empty object in enum values...
			private val filtered = ContainerUtil.set("{}", "{ }")

			private fun getIcon(type: JsonSchemaType?): Icon {
				return  when {
					type == null -> AllIcons.Nodes.Property
					type == JsonSchemaType._object -> AllIcons.Json.Object
					type == JsonSchemaType._array -> AllIcons.Json.Array
					else -> AllIcons.Nodes.Property
				}
			}

			private fun hasSameType(variants: Collection<JsonSchemaObject>): Boolean {
				return variants.mapNotNull { it.guessType() }.distinct().count() <= 1
			}

			private fun createArrayOrObjectLiteralInsertHandler(insertedTextSize: Int): InsertHandler<LookupElement> {
				return InsertHandler { context, _ ->
					val editor = context.editor
					EditorModificationUtil.moveCaretRelatively(editor, -1)
					AutoPopupController.getInstance(context.project).autoPopupMemberLookup(editor, null)
				}
			}

			private fun invokeEnterHandler(editor: Editor) {
				val handler = EditorActionManager.getInstance().getActionHandler(IdeActions.ACTION_EDITOR_ENTER)
				val caret = editor.caretModel.currentCaret
				handler.execute(editor, caret,
					CaretSpecificDataContext(DataManager.getInstance().getDataContext(editor.contentComponent),
						caret))
			}

			private fun handleIncompleteString(editor: Editor, element: PsiElement): Boolean {
				if((element as LeafPsiElement).elementType === TokenType.WHITE_SPACE) {
					val prevSibling = element.prevSibling
					if(prevSibling is JsonProperty) {
						val nameElement = prevSibling.nameElement
						if(!nameElement.text.endsWith("\"")) {
							editor.caretModel.moveToOffset(nameElement.textRange.endOffset)
							EditorModificationUtil.insertStringAtCaret(editor, "\"", false, true, 1)
							return true
						}
					}
				}
				return false
			}

			private fun detectType(values: List<Any>): JsonSchemaType? {
				var type: JsonSchemaType? = null
				for(value in values) {
					var newType: JsonSchemaType? = null
					if(value is Int) newType = JsonSchemaType._integer
					if(type != null && type != newType) return null
					type = newType
				}
				return type
			}
		}
	}

	companion object {
		private const val SEPARATOR = " = "
		private const val SEPARATOR_LENGTH = SEPARATOR.length
		private const val SEPARATOR_CHAR = '='
		private const val BUILTIN_USAGE_KEY = "builtin"
		private const val SCHEMA_USAGE_KEY = "jsonSchema"
		private const val USER_USAGE_KEY = "user"
		private const val REMOTE_USAGE_KEY = "remote"

		fun doCompletion(parameters: CompletionParameters, result: CompletionResultSet, rootSchema: JsonSchemaObject, stop: Boolean) {
			val completionPosition = if(parameters.originalPosition != null) parameters.originalPosition else parameters.position
			Worker(rootSchema, parameters.position, completionPosition!!, result).work()
			if(stop) result.stopHere()
		}

		fun getCompletionVariants(schema: JsonSchemaObject, position: PsiElement, originalPosition: PsiElement): List<LookupElement> {
			val result: MutableList<LookupElement> = ArrayList()
			Worker(schema, position, originalPosition) { element: LookupElement -> result.add(element) }.work()
			return result
		}

		private fun updateStat(provider: JsonSchemaFileProvider?, schemaFile: VirtualFile?) {
			if(provider == null) {
				if(schemaFile is HttpVirtualFile) {
					// auto-detected and auto-downloaded JSON schemas
					JsonSchemaUsageTriggerCollector.trigger(REMOTE_USAGE_KEY)
				}
				return
			}
			when(provider.schemaType) {
				SchemaType.schema -> JsonSchemaUsageTriggerCollector.trigger(SCHEMA_USAGE_KEY)
				SchemaType.userSchema -> JsonSchemaUsageTriggerCollector.trigger(USER_USAGE_KEY)
				SchemaType.embeddedSchema -> JsonSchemaUsageTriggerCollector.trigger(BUILTIN_USAGE_KEY)
				// this works only for user-specified remote schemas in our settings, but not for auto-detected remote schemas
				SchemaType.remoteSchema -> JsonSchemaUsageTriggerCollector.trigger(REMOTE_USAGE_KEY)
			}
		}

		private fun insertPropertyWithEnum(
			context: InsertionContext,
			editor: Editor,
			defaultValue: String?,
			values: List<Any>?,
			type: JsonSchemaType?,
			comma: String,
			walker: JsonLikePsiWalker?,
			insertColon: Boolean
		) {
			var defaultValue = defaultValue
			if(!walker!!.requiresValueQuotes() && defaultValue != null) {
				defaultValue = StringUtil.unquoteString(defaultValue)
			}
			val isNumber = type != null && (JsonSchemaType._integer == type || JsonSchemaType._number == type) || type == null
			               && (defaultValue != null && !StringUtil.isQuotedString(defaultValue) || values != null && ContainerUtil.and(values) { it !is String })
			val hasValues = !ContainerUtil.isEmpty(values)
			val hasDefaultValue = !StringUtil.isEmpty(defaultValue)
			val hasQuotes = isNumber || !walker.requiresValueQuotes()
			val offset = editor.caretModel.offset
			val charSequence = editor.document.charsSequence
			val ws = if(charSequence.length > offset && charSequence[offset] == ' ') "" else " "
			val colonWs = if(insertColon) SEPARATOR else ws
			val stringToInsert = colonWs + (if(hasDefaultValue) defaultValue else if(hasQuotes) "" else "\"\"") + comma
			EditorModificationUtil.insertStringAtCaret(editor, stringToInsert, false, true, if(insertColon) SEPARATOR_LENGTH else 1)
			if(!hasQuotes || hasDefaultValue) {
				val model = editor.selectionModel
				val caretStart = model.selectionStart
				var newOffset = caretStart + if(hasDefaultValue) defaultValue!!.length else SEPARATOR_LENGTH
				if(hasDefaultValue && !hasQuotes) newOffset--
				model.setSelection(if(hasQuotes) caretStart else caretStart + SEPARATOR_LENGTH, newOffset)
				editor.caretModel.moveToOffset(newOffset)
			}
			if(!walker.hasWhitespaceDelimitedCodeBlocks() && stringToInsert != colonWs + comma) {
				formatInsertedString(context, stringToInsert.length)
			}
			if(hasValues) {
				AutoPopupController.getInstance(context.project).autoPopupMemberLookup(context.editor, null)
			}
		}

		fun formatInsertedString(context: InsertionContext, offset: Int) {
			val project = context.project
			PsiDocumentManager.getInstance(project).commitDocument(context.document)
			val codeStyleManager = CodeStyleManager.getInstance(project)
			codeStyleManager.reformatText(context.file, context.startOffset, context.tailOffset + offset)
		}
	}
}
