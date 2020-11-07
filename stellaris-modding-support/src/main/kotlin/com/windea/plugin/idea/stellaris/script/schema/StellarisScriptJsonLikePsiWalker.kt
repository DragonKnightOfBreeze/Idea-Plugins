package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.json.pointer.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.intellij.util.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.schema.adapter.*

object StellarisScriptJsonLikePsiWalker : JsonLikePsiWalker {
	override fun findElementToCheck(element: PsiElement): PsiElement? {
		//得到需要检查的元素
		var current = element
		//向上查找直到psiFile，如果是property/propertyKey/value，则认为已找到
		while(current !is PsiFile){
			when(current) {
				is StellarisScriptProperty, is StellarisScriptPropertyKey, is StellarisScriptValue -> return current
				else -> current = current.parent
			}
		}
		return current
	}

	override fun isName(element: PsiElement): ThreeState {
		//将所有YES改为UNSURE
		return when(element) {
			is StellarisScriptProperty -> ThreeState.UNSURE
			is StellarisScriptPropertyKey -> ThreeState.YES
			is StellarisScriptPropertyValue -> ThreeState.NO
			is StellarisScriptNumber -> ThreeState.UNSURE
			is StellarisScriptString -> ThreeState.UNSURE
			is StellarisScriptFile -> ThreeState.UNSURE
			else -> ThreeState.NO
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
		//可以是value也可以是file
		return listOfNotNull(PsiTreeUtil.findChildOfType(file,StellarisScriptValue::class.java),file)
	}

	override fun getParentPropertyAdapter(element: PsiElement): JsonPropertyAdapter? {
		val parent = element.parentOfType<StellarisScriptProperty>(true)
		return if(parent != null) StellarisScriptPropertyAdapter(parent) else null
	}

	override fun getPropertyNameElement(property: PsiElement?): PsiElement? {
		return when(property) {
			is StellarisScriptProperty -> property.propertyKey
			//首先会识别item而非property，因此这里是必须的
			is StellarisScriptNumber, is StellarisScriptString ->  property
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
			is StellarisScriptFile -> StellarisScriptArrayFromFileAdapter(element)
			is StellarisScriptPropertyValue -> StellarisScriptValueAdapter(element.value)
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
				is StellarisScriptValue ->{
					val parent = current.parent
					//如果在数组中
					if(parent is StellarisScriptBlock || parent is StellarisScriptFile) {
						val index = parent.indexOfChild(current)
						position.addPrecedingStep(index)
					}
					current = parent
				}
				else -> {
					current = current.parent
				}
			}
		}
	}

	override fun acceptsEmptyRoot() = true

	override fun allowsSingleQuotes() = false

	override fun requiresNameQuotes() = false

	override fun requiresValueQuotes() = false

	override fun getDefaultObjectValue() = "{}"

	override fun getDefaultArrayValue() = "{}"

	override fun hasWhitespaceDelimitedCodeBlocks() = false

	override fun hasMissingCommaAfter(element: PsiElement) = false
}
