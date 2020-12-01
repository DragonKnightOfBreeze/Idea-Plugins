package com.windea.plugin.idea.paradox.script.schema

import com.intellij.json.pointer.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.intellij.util.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.script.psi.*
import com.windea.plugin.idea.paradox.script.schema.adapter.*

object ParadoxScriptJsonLikePsiWalker : JsonLikePsiWalker {
	override fun findElementToCheck(element: PsiElement): PsiElement? {
		//得到需要检查的元素
		var current = element
		//向上查找直到psiFile，如果是property/value，则认为已找到
		while(current !is PsiFile){
			when(current) {
				is ParadoxScriptProperty, is ParadoxScriptValue -> return current
				else -> current = current.parent?:return null
			}
		}
		return null
	}

	override fun isName(element: PsiElement): ThreeState {
		return when(element) {
			is ParadoxScriptBlock -> ThreeState.UNSURE
			is ParadoxScriptProperty -> ThreeState.UNSURE
			is ParadoxScriptPropertyKey -> ThreeState.YES
			is ParadoxScriptPropertyValue -> ThreeState.NO
			is ParadoxScriptNumber -> ThreeState.UNSURE
			is ParadoxScriptString -> ThreeState.UNSURE
			else -> ThreeState.NO
		}
	}

	override fun isTopJsonElement(element: PsiElement): Boolean {
		return element is ParadoxScriptFile
	}

	override fun isPropertyWithValue(element: PsiElement): Boolean {
		return element is ParadoxScriptProperty
	}

	//用于验证的Json字符串，字符串需要带双引号
	override fun getNodeTextForValidation(element: PsiElement?): String {
		return when{
			element == null -> "null"
			element is ParadoxScriptBoolean -> if(element.value == "yes") "true" else "false"
			element is ParadoxScriptNumber -> element.value
			element is ParadoxScriptStringValue -> element.value.quote()
			else -> element.text.quote()
		}
	}

	override fun getRoots(file: PsiFile): Collection<PsiElement> {
		return PsiTreeUtil.findChildOfType(file,ParadoxScriptValue::class.java).toSingletonOrEmpty()
	}

	override fun getParentPropertyAdapter(element: PsiElement): JsonPropertyAdapter? {
		var current = element
		while(current !is PsiFile){
			if(current is ParadoxScriptProperty) return ParadoxScriptPropertyAdapter(current)
			current = current.parent ?:return null
		}
		return null
	}

	override fun getPropertyNameElement(property: PsiElement?): PsiElement? {
		return when(property) {
			is ParadoxScriptProperty -> property.propertyKey
			//首先会识别item而非property，因此这里是必须的
			is ParadoxScriptNumber, is ParadoxScriptString ->  property
			else -> null
		}
	}

	override fun getPropertyNamesOfParentObject(originalPosition: PsiElement, computedPosition: PsiElement?): MutableSet<String> {
		return when(val parent = originalPosition.parent) {
			is ParadoxScriptBlock -> parent.propertyList.mapNotNullTo(mutableSetOf()) { it.name }
			else -> mutableSetOf()
		}
	}

	override fun createValueAdapter(element: PsiElement): JsonValueAdapter? {
		return when(element) {
			is ParadoxScriptPropertyValue -> ParadoxScriptValueAdapter(element.value)
			is ParadoxScriptValue -> ParadoxScriptValueAdapter(element)
			else -> null
		}
	}

	override fun findPosition(element: PsiElement, forceLastTransition: Boolean): JsonPointerPosition? {
		val position = JsonPointerPosition()
		var current = element
		while(current !is PsiFile) {
			when(current){
				//如果是属性
				is ParadoxScriptProperty -> {
					val name = current.name
					position.addPrecedingStep(name)
					current = current.parent?:return null
				}
				//如果是指并且在数组中
				is ParadoxScriptValue ->{
					val parent = current.parent?:return null
					if(parent is ParadoxScriptBlock) {
						val index = parent.indexOfChild(current)
						position.addPrecedingStep(index)
					}
					current = parent
				}
				else -> {
					current = current.parent?:return null
				}
			}
		}
		return position
	}

	override fun allowsSingleQuotes() = false

	override fun requiresNameQuotes() = false

	override fun requiresValueQuotes() = false

	override fun getDefaultObjectValue() = "{}"

	override fun getDefaultArrayValue() = "{}"

	override fun hasWhitespaceDelimitedCodeBlocks() = false

	override fun hasMissingCommaAfter(element: PsiElement) = false
}
