@file:Suppress("NAME_SHADOWING", "UNUSED_PARAMETER", "MapGetWithNotNullAssertionOperator")

package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.codeInsight.*
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.icons.*
import com.intellij.json.psi.*
import com.intellij.lang.*
import com.intellij.openapi.application.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.editor.actions.*
import com.intellij.openapi.project.*
import com.intellij.openapi.util.text.*
import com.intellij.openapi.vfs.*
import com.intellij.openapi.vfs.impl.http.*
import com.intellij.psi.*
import com.intellij.psi.codeStyle.*
import com.intellij.psi.impl.source.tree.*
import com.intellij.psi.injection.*
import com.intellij.psi.util.*
import com.intellij.util.*
import com.intellij.util.containers.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.extension.adapters.*
import com.jetbrains.jsonSchema.ide.*
import com.jetbrains.jsonSchema.impl.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*
import java.util.*
import javax.swing.*

//TODO 重构和完善

class StellarisScriptSchemaCompletionContributor : CompletionContributor() {
	private val mayBePropertyKeyTypes = arrayOf(STRING_TOKEN, NUMBER_TOKEN)

	override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
		//当用户正在输入一个string或number，但之前的节点不是string或number时提示
		val position = parameters.position
		val elementType = position.elementType
		if(elementType !in mayBePropertyKeyTypes) return
		val prevElementType = position.parent?.prevSibling?.firstChild?.elementType
		if(prevElementType != null && prevElementType in mayBePropertyKeyTypes) return

		val jsonSchemaService = JsonSchemaService.Impl.get(position.project)
		val schemaObject = jsonSchemaService.getSchemaObject(parameters.originalFile) ?: return
		Worker(schemaObject, position, result).work()
	}

	private class Worker(
		private val rootSchema: JsonSchemaObject,
		private val position: PsiElement,
		private val resultConsumer: Consumer<LookupElement>,
	) {
		private val project = position.project
		private val variants = mutableSetOf<LookupElement>()
		private val isInsideStringLiteral = position.parent is StellarisScriptStringValue

		fun work() {
			//注意：如果matchedElement是string或number且parent是block，表示用户可能正在输入一个propertyKey
			val checkable = walker.findElementToCheck(position) ?: return
			val isName = walker.isName(checkable)
			val checkablePosition = walker.findPosition(checkable, isName == ThreeState.NO)
			if(checkablePosition == null || checkablePosition.isEmpty && isName == ThreeState.NO) return
			//直接通过直接得到的jsonPointer查找schema
			//如果结果为空，且用户可能正在输入一个propertyKey，需要移除jsonPointer的最后一个path继续查找schema
			val schemas = JsonSchemaResolver(project, rootSchema, checkablePosition).resolve()
			if(schemas.isEmpty() && mayBePropertyKey(checkable) && !checkablePosition.isEmpty) {
				schemas.addAll(JsonSchemaResolver(project, rootSchema, checkablePosition.trimTail(1)!!).resolve())
			}
			//TODO 表示已经存在的属性名，需要定制规则当属性名已存在时仍然可以进行补全
			val knownNames = mutableSetOf<String>()
			//开始适用所有匹配到的schema规则
			schemas.forEach { schema ->
				if(isName != ThreeState.NO) {
					val hasValue = walker.isPropertyWithValue(checkable)
					val propertieNames = walker.getPropertyNamesOfParentObject(position, position)
					val adapter = walker.getParentPropertyAdapter(position)
					addPropertyNameSchemaVariants(schema)
					addAllPropertyVariants(hasValue, propertieNames, adapter, schema.properties, knownNames)
					addIfThenElsePropertyNameVariants(schema, hasValue, propertieNames, adapter, knownNames)
				}
				if(isName != ThreeState.YES) {
					suggestValues(schema, isName == ThreeState.NO)
				}
			}
			for(variant in variants) {
				resultConsumer.consume(variant)
			}
		}

		private fun mayBePropertyKey(checkable: PsiElement): Boolean {
			return checkable is StellarisScriptString || checkable is StellarisScriptNumber
			       && checkable.parent !is StellarisScriptPropertyValue
		}

		private fun addPropertyNameSchemaVariants(schema: JsonSchemaObject) {
			val propertyNamesSchema = schema.propertyNamesSchema ?: return
			val enums = propertyNamesSchema.enum ?: return
			for(e in enums) {
				if(e is String) {
					variants.add(LookupElementBuilder.create(e.onlyQuoteIfNecessary()))
				}
			}
		}

		private fun addIfThenElsePropertyNameVariants(schema: JsonSchemaObject, hasValue: Boolean,
			properties: Collection<String>, adapter: JsonPropertyAdapter?, knownNames: MutableSet<String>) {
			val ifThenElseList = schema.ifThenElse ?: return
			val propertyAdapter = walker.getParentPropertyAdapter(position) ?: return
			val parentObject = propertyAdapter.parentObject ?: return
			for(ifThenElse in ifThenElseList) {
				try {
					//访问受限，因此用反射去实例化
					val constructor = JsonSchemaAnnotatorChecker::class.java.getConstructor(Project::class.java, JsonComplianceCheckerOptions::class.java)
					constructor.trySetAccessible()
					val checker = constructor.newInstance(project, JsonComplianceCheckerOptions.RELAX_ENUM_CHECK)

					checker.checkByScheme(parentObject, ifThenElse.getIf())
					if(checker.isCorrect) {
						val schemaThen = ifThenElse.then ?: return
						addAllPropertyVariants(hasValue, properties, adapter, schemaThen.properties, knownNames)
					} else {
						val schemaElse = ifThenElse.`else` ?: return
						addAllPropertyVariants(hasValue, properties, adapter, schemaElse.properties, knownNames)
					}
				} catch(e: Exception) {
					e.printStackTrace()
				}
			}
		}

		private fun addAllPropertyVariants(hasValue: Boolean, propertyNames: Collection<String>,
			adapter: JsonPropertyAdapter?, schemaProperties: Map<String, JsonSchemaObject>, knownNames: MutableSet<String>) {
			val unknownNames = schemaProperties.keys.filter { name ->
				!propertyNames.contains(name) && !knownNames.contains(name) || adapter != null && (name == adapter.name)
			}
			for(name in unknownNames) {
				knownNames.add(name)
				addPropertyVariant(name, schemaProperties[name]!!, hasValue)
			}
		}

		private fun suggestValues(schema: JsonSchemaObject, isSurelyValue: Boolean) {
			suggestValuesForSchemaVariants(schema.anyOf, isSurelyValue)
			suggestValuesForSchemaVariants(schema.oneOf, isSurelyValue)
			suggestValuesForSchemaVariants(schema.allOf, isSurelyValue)
			if(schema.enum != null) {
				val metadata = schema.enumMetadata
				for(o in schema.enum!!) {
					if(isInsideStringLiteral && o !is String) continue
					val variant = o.toString()
					if(!filtered.contains(variant)) {
						val valueMetadata = metadata?.get(variant.unquote())
						val description = valueMetadata?.get("description")
						addValueVariant(variant, description, null, null)
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
			if(JsonSchemaVersion.isSchemaSchemaId(rootSchema.id) && type == JsonSchemaType._string) {
				val propertyAdapter = walker.getParentPropertyAdapter(position) ?: return
				val name = propertyAdapter.name ?: return
				when(name) {
					"required" -> addRequiredPropVariants()
					JsonSchemaObject.X_INTELLIJ_LANGUAGE_INJECTION -> addInjectedLanguageVariants()
					"language" -> {
						val parent = propertyAdapter.parentObject
						if(parent != null) {
							val adapter = walker.getParentPropertyAdapter(parent.delegate)
							if(adapter != null && (JsonSchemaObject.X_INTELLIJ_LANGUAGE_INJECTION == adapter.name)) {
								addInjectedLanguageVariants()
							}
						}
					}
				}
			}
		}

		private fun addInjectedLanguageVariants() {
			val checkable = walker.findElementToCheck(position)
			if(checkable !is StellarisScriptString) return
			JBIterable.from(Language.getRegisteredLanguages())
				.filter { language -> LanguageUtil.isInjectableLanguage(language!!) }
				.map { language -> Injectable.fromLanguage(language) }
				.forEach { injectable ->
					variants.add(LookupElementBuilder.create(injectable.id)
						.withIcon(injectable.icon)
						.withTailText("(" + injectable.displayName + ")", true))
				}
		}

		private fun addRequiredPropVariants() {
			val checkable = walker.findElementToCheck(position)
			if(checkable !is StellarisScriptStringValue) return
			val propertiesObject = JsonRequiredPropsReferenceProvider.findPropertiesObject(checkable) ?: return
			val items = when(val parent = checkable.parent) {
				is StellarisScriptBlock -> parent.valueList.filterIsInstance<JsonStringLiteral>().map { it.value }.toSet()
				else -> setOf()
			}
			propertiesObject.propertyList.map { it.name }.filter { !items.contains(it) }.forEach { addStringVariant(it) }
		}

		private fun suggestByType(schema: JsonSchemaObject, type: JsonSchemaType) {
			if(type == JsonSchemaType._string) addPossibleStringValue(schema)
			if(isInsideStringLiteral) return
			when {
				type == JsonSchemaType._boolean -> {
					addPossibleBooleanValue(type)
				}
				type == JsonSchemaType._null -> {
					addValueVariant("null", null)
				}
				type == JsonSchemaType._array -> {
					val value = walker.defaultArrayValue
					addValueVariant(value, null, "{...}", createArrayOrObjectLiteralInsertHandler(value.length))
				}
				type == JsonSchemaType._object -> {
					val value = walker.defaultObjectValue
					addValueVariant(value, null, "{...}", createArrayOrObjectLiteralInsertHandler(value.length))
				}
			}
		}

		//schema中存在默认值时添加默认值提示
		private fun addPossibleStringValue(schema: JsonSchemaObject) {
			val defaultValue = schema.default?.toString() ?: return
			addStringVariant(defaultValue)
		}

		//添加字符串提示，去除双引号，但必要时用双引号包围
		private fun addStringVariant(defaultValueString: String) {
			if(defaultValueString.isEmpty()) return
			addValueVariant(defaultValueString.unquote().onlyQuoteIfNecessary(), null)
		}

		//为schema提供建议的值
		private fun suggestValuesForSchemaVariants(list: List<JsonSchemaObject>?, isSurelyValue: Boolean) {
			if(list != null && list.isNotEmpty()) {
				for(schemaObject in list) {
					suggestValues(schemaObject, isSurelyValue)
				}
			}
		}

		//添加布尔值提示
		private fun addPossibleBooleanValue(type: JsonSchemaType) {
			if(JsonSchemaType._boolean == type) {
				addValueVariant("yes", null)
				addValueVariant("no", null)
			}
		}

		//添加值提示
		private fun addValueVariant(
			key: String, description: String?, altText: String? = null,
			handler: InsertHandler<LookupElement>? = null,
		) {
			var builder = LookupElementBuilder.create(key.onlyQuoteIfNecessary())
			if(altText != null) builder = builder.withPresentableText(altText)
			if(description != null) builder = builder.withTypeText(description)
			if(handler != null) builder = builder.withInsertHandler(handler)
			variants.add(builder)
		}

		//添加属性的提示
		private fun addPropertyVariant(key: String, jsonSchemaObject: JsonSchemaObject, hasValue: Boolean) {
			var jsonSchemaObject = jsonSchemaObject
			val variants = JsonSchemaResolver(project, jsonSchemaObject).resolve()
			jsonSchemaObject = ObjectUtils.coalesce(ContainerUtil.getFirstItem(variants), jsonSchemaObject)
			var builder = LookupElementBuilder.create(key.onlyQuoteIfNecessary())
			val typeText = JsonSchemaDocumentationProvider.getBestDocumentation(true, jsonSchemaObject)
			if(typeText != null && typeText.isNotBlank()) {
				val text = StringUtil.removeHtmlTags(typeText)
				val firstSentenceMark = text.indexOf(". ")
				val typeText = if(firstSentenceMark == -1) text else text.substring(0, firstSentenceMark + 1)
				builder = builder.withTypeText(typeText, true)
			} else {
				val type = jsonSchemaObject.getTypeDescription(true)
				if(type != null) builder = builder.withTypeText(type, true)
			}
			builder = builder.withIcon(getIcon(jsonSchemaObject.guessType()))
			builder = if(hasSameType(variants)) {
				val type = jsonSchemaObject.guessType()
				val values = jsonSchemaObject.enum
				val defaultValue = jsonSchemaObject.default
				val hasValues = !ContainerUtil.isEmpty(values)
				if(type != null || hasValues || defaultValue != null) {
					val insertHandler = if(!hasValues || values!!.map { it.javaClass }.distinct().count() == 1) {
						createPropertyInsertHandler(jsonSchemaObject, hasValue)
					} else {
						createDefaultPropertyInsertHandler(true)
					}
					builder.withInsertHandler(insertHandler)
				} else {
					builder.withInsertHandler(createDefaultPropertyInsertHandler(hasValue))
				}
			} else {
				builder.withInsertHandler(createDefaultPropertyInsertHandler(hasValue))
			}
			this.variants.add(builder)
		}

		private fun createDefaultPropertyInsertHandler(hasValue: Boolean): InsertHandler<LookupElement> {
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
							handleWhitespaceAfterSeparator(editor, docChars, initialOffset + 1)
						}
						return
					}
					if(offset < docChars.length && docChars[offset] == SEPARATOR_CHAR) {
						handleWhitespaceAfterSeparator(editor, docChars, offset + 1)
					} else {
						// inserting longer string for proper formatting
						val stringToInsert = "${SEPARATOR}1"
						EditorModificationUtil.insertStringAtCaret(editor, stringToInsert, false, true, SEPARATOR_LENGTH)
						offset = editor.caretModel.offset
						context.document.deleteString(offset, offset + 1)
					}
					PsiDocumentManager.getInstance(project).commitDocument(editor.document)
					AutoPopupController.getInstance(context.project).autoPopupMemberLookup(context.editor, null)
				}

				fun handleWhitespaceAfterSeparator(editor: Editor, docChars: CharSequence, nextOffset: Int) {
					if(nextOffset < docChars.length && docChars[nextOffset] == ' ') {
						editor.caretModel.moveToOffset(nextOffset + 1)
					} else {
						editor.caretModel.moveToOffset(nextOffset)
						EditorModificationUtil.insertStringAtCaret(editor, " ", false, true, 1)
					}
				}
			}
		}

		private fun createPropertyInsertHandler(jsonSchemaObject: JsonSchemaObject, hasValue: Boolean): InsertHandler<LookupElement> {
			var type = jsonSchemaObject.guessType()
			val values = jsonSchemaObject.enum
			if(type == null && values != null && values.isNotEmpty()) type = detectType(values)
			val defaultValue = jsonSchemaObject.default
			val defaultValueString = when {
				defaultValue == null -> null
				defaultValue is JsonSchemaObject -> null
				defaultValue is Boolean -> defaultValue.toStringYesNo()
				defaultValue is String -> defaultValue.toString().onlyQuoteIfNecessary()
				else -> defaultValue.toString()
			}
			val finalType = type
			return InsertHandler { context, _ ->
				ApplicationManager.getApplication().assertWriteAccessAllowed()
				val editor = context.editor
				val project = context.project
				var stringToInsert: String? = null
				if(handleInsideQuotesInsertion(context, editor, hasValue)) return@InsertHandler
				val element = context.file.findElementAt(editor.caretModel.offset)
				val insertSeparator = element == null || SEPARATOR != element.text
				if(!insertSeparator) {
					editor.caretModel.moveToOffset(editor.caretModel.offset + 1)
				}
				if(finalType != null) {
					when(finalType) {
						JsonSchemaType._boolean -> {
							val value = defaultValueString ?: "no"
							stringToInsert = (if(insertSeparator) SEPARATOR else " ") + value
							val model = editor.selectionModel
							EditorModificationUtil.insertStringAtCaret(editor, stringToInsert, false, true, stringToInsert.length)
							//格式化插入字符串
							if(stringToInsert.isNotEmpty()) {
								formatInsertedString(context, stringToInsert.length)
							}
							//选中默认值
							val start = editor.selectionModel.selectionStart
							model.setSelection(start - value.length, start)
							//弹出默认值的popup
							AutoPopupController.getInstance(context.project).autoPopupMemberLookup(context.editor, null)
						}
						JsonSchemaType._string, JsonSchemaType._integer, JsonSchemaType._number -> {
							insertPropertyWithEnum(context, editor, defaultValueString, values, finalType, insertSeparator)
						}
						JsonSchemaType._array -> {
							EditorModificationUtil.insertStringAtCaret(editor, if(insertSeparator) SEPARATOR else " ", false, true, if(insertSeparator) SEPARATOR_LENGTH else 1)
							if(insertSeparator) {
								stringToInsert = walker.defaultArrayValue
								EditorModificationUtil.insertStringAtCaret(editor, stringToInsert, false, true, 1)
							}
							PsiDocumentManager.getInstance(project).commitDocument(editor.document)
							//则格式化插入字符串
							if(stringToInsert != null && stringToInsert.isNotEmpty()) {
								formatInsertedString(context, stringToInsert.length)
							}
						}
						JsonSchemaType._object -> {
							EditorModificationUtil.insertStringAtCaret(editor, if(insertSeparator) SEPARATOR else " ", false, true, if(insertSeparator) SEPARATOR_LENGTH else 1)
							if(insertSeparator) {
								stringToInsert = walker.defaultObjectValue
								EditorModificationUtil.insertStringAtCaret(editor, stringToInsert, false, true, 1)
							}
							if(!insertSeparator) {
								EditorActionUtil.moveCaretToLineEnd(editor, false, false)
							}
							PsiDocumentManager.getInstance(project).commitDocument(editor.document)
							//格式化插入字符串
							if(stringToInsert != null && stringToInsert.isNotEmpty()) {
								formatInsertedString(context, stringToInsert.length)
							}
						}
						else -> {
						}
					}
				} else {
					insertPropertyWithEnum(context, editor, defaultValueString, values, null, insertSeparator)
				}
			}
		}

		private fun handleInsideQuotesInsertion(context: InsertionContext, editor: Editor, hasValue: Boolean): Boolean {
			if(isInsideStringLiteral) {
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
				if(hasValue) return true
				editor.caretModel.moveToOffset(guessEndOffset)
			} else {
				editor.caretModel.moveToOffset(context.tailOffset)
			}
			return false
		}

		companion object {
			// some schemas provide empty array / empty object in enum values...
			private val filtered = setOf("{}", "{ }")
			private val walker = StellarisScriptJsonLikePsiWalker

			private const val SEPARATOR = " = "
			private const val SEPARATOR_LENGTH = SEPARATOR.length
			private const val SEPARATOR_CHAR = '='
			private const val BUILTIN_USAGE_KEY = "builtin"
			private const val SCHEMA_USAGE_KEY = "jsonSchema"
			private const val USER_USAGE_KEY = "user"
			private const val REMOTE_USAGE_KEY = "remote"

			private fun getIcon(type: JsonSchemaType?): Icon {
				return when {
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

			private fun handleIncompleteString(editor: Editor, element: PsiElement): Boolean {
				if((element as LeafPsiElement).elementType === TokenType.WHITE_SPACE) {
					val prevSibling = element.prevSibling
					if(prevSibling is StellarisScriptProperty) {
						val nameElement = prevSibling.propertyKey
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

			fun getCompletionVariants(schema: JsonSchemaObject, position: PsiElement): List<LookupElement> {
				val result: MutableList<LookupElement> = ArrayList()
				Worker(schema, position) { element: LookupElement -> result.add(element) }.work()
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

			private fun insertPropertyWithEnum(context: InsertionContext, editor: Editor, defaultValue: String?,
				values: List<Any>?, type: JsonSchemaType?, insertSeparator: Boolean) {
				val hasValues = values != null && values.isNotEmpty()
				val hasDefaultValue = defaultValue != null && defaultValue.isNotEmpty()
				val offset = editor.caretModel.offset
				val charSequence = editor.document.charsSequence
				val ws = if(charSequence.length > offset && charSequence[offset] == ' ') "" else " "
				val separatorWs = if(insertSeparator) SEPARATOR else ws
				val stringToInsert = separatorWs + if(hasDefaultValue) defaultValue else ""
				val caretShift = if(insertSeparator) SEPARATOR_LENGTH else 1
				EditorModificationUtil.insertStringAtCaret(editor, stringToInsert, false, true, caretShift)
				if(hasDefaultValue) {
					val model = editor.selectionModel
					val caretStart = model.selectionStart
					var newOffset = caretStart + if(hasDefaultValue) defaultValue!!.length else SEPARATOR_LENGTH
					if(hasDefaultValue) newOffset--
					model.setSelection(caretStart + SEPARATOR_LENGTH, newOffset)
					editor.caretModel.moveToOffset(newOffset)
				}
				//格式化插入字符串
				if(stringToInsert.isNotEmpty()) {
					formatInsertedString(context, stringToInsert.length)
				}
				//如果有默认值，则弹出popup
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
}
