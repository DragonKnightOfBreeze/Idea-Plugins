package com.windea.plugin.idea.stellaris.schema

import com.intellij.json.pointer.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.intellij.util.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.schema.adapter.*
import com.windea.plugin.idea.stellaris.script.psi.*

object StellarisScriptJsonLikePsiWalker : JsonLikePsiWalker {
	override fun findElementToCheck(element: PsiElement): PsiElement? {
		//得到需要检查的元素
		var current = element
		//TODO 是否仍有必要？
		//如果是item，则得到父元素（限于语法解析）
		//如果是空白，判断之前的元素是否是item，若是则得到之前的元素
		if(current is PsiWhiteSpace){
			val prev = current.prevSibling
			if(prev is StellarisScriptItem) current = prev
		}
		//向上查找直到psiFile，如果是property/propertyKey/value，则认为已找到
		while(current !is PsiFile){
			when(current) {
				is StellarisScriptProperty, is StellarisScriptPropertyKey, is StellarisScriptValue -> return current
				else -> current = current.parent
			}
		}
		return null
	}

	override fun isName(element: PsiElement): ThreeState {
		//将所有YES改为UNSURE
		return when(element.parent) {
			is StellarisScriptProperty -> ThreeState.UNSURE
			is StellarisScriptPropertyKey -> ThreeState.YES
			is StellarisScriptPropertyValue -> ThreeState.NO
			is StellarisScriptItem -> ThreeState.UNSURE
			else -> ThreeState.UNSURE
		}
	}

	override fun isTopJsonElement(element: PsiElement): Boolean {
		//可能是psiFile，也可能子元素是psiFile，因为底层逻辑是判断root的父元素是否是topJsonElement
		return element is StellarisScriptFile || element is PsiDirectory
	}

	override fun isPropertyWithValue(element: PsiElement): Boolean {
		return element is StellarisScriptProperty
	}

	//用于验证的Json字符串，字符串需要带双引号
	override fun getNodeTextForValidation(element: PsiElement?): String {
		return when{
			element == null -> "null"
			element is StellarisScriptBoolean -> if(element.value == "yes") "true" else "false"
			element is StellarisScriptNumber -> element.value
			element is StellarisScriptStringValue -> element.value.quote()
			else -> element.text.quote()
		}
	}

	override fun getRoots(file: PsiFile): Collection<PsiElement> {
		return  PsiTreeUtil.findChildOfType(file,StellarisScriptValue::class.java).toSingletonOrEmpty()
	}

	override fun getParentPropertyAdapter(element: PsiElement): JsonPropertyAdapter? {
		val parent = element.parentOfType<StellarisScriptProperty>(true)
		return if(parent != null) StellarisScriptPropertyAdapter(parent) else null
	}

	override fun getPropertyNameElement(property: PsiElement?): PsiElement? {
		return when(property) {
			is StellarisScriptProperty -> property.propertyKey
			//首先会识别item而非property，因此这里是必须的
			is StellarisScriptItem -> if(property.value is StellarisScriptString) property else null
			else -> null
		}
	}

	override fun getPropertyNamesOfParentObject(originalPosition: PsiElement, computedPosition: PsiElement?): MutableSet<String> {
		return when(val parent = originalPosition.parent) {
			is StellarisScriptBlock -> parent.propertyList.mapNotNullTo(mutableSetOf()) { it.name }
			//也可以是file，这也合法
			is StellarisScriptFile -> parent.properties.mapNotNullTo(mutableSetOf()){it.name}
			else -> mutableSetOf()
		}
	}

	override fun createValueAdapter(element: PsiElement): JsonValueAdapter? {
		return when(element) {
			is StellarisScriptPropertyValue -> StellarisScriptValueAdapter(element.value)
			is StellarisScriptItem -> StellarisScriptValueAdapter(element.value)
			is StellarisScriptValue -> StellarisScriptValueAdapter(element)
			else -> null
		}
	}

	override fun findPosition(element: PsiElement, forceLastTransition: Boolean): JsonPointerPosition? {
		val position = JsonPointerPosition()
		var current = element
		while(true) {
			when(current){
				is PsiFile -> return position
				is StellarisScriptProperty -> {
					val name = current.name
					position.addPrecedingStep(name)
					current = current.parent
				}
				is StellarisScriptItem ->{
					val parent = current.parent
					val index = parent.indexOfChild(current)
					position.addPrecedingStep(index)
					current = parent
				}
				else -> {
					current = current.parent
				}
			}
		}
	}

	override fun acceptsEmptyRoot(): Boolean {
		return true
	}

	override fun allowsSingleQuotes(): Boolean {
		return false
	}

	override fun requiresNameQuotes(): Boolean {
		return false
	}

	override fun requiresValueQuotes(): Boolean {
		return false
	}

	override fun getDefaultObjectValue(): String {
		return "{}"
	}

	override fun getDefaultArrayValue(): String {
		return "{}"
	}

	override fun hasWhitespaceDelimitedCodeBlocks(): Boolean {
		return false
	}

	override fun hasMissingCommaAfter(element: PsiElement): Boolean {
		return false
	}
}
