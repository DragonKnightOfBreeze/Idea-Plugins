package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.json.pointer.*
import com.intellij.psi.*
import com.intellij.util.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*

//org.jetbrains.yaml.schema.YamlJsonPsiWalker

object StellarisScriptJsonLikePsiWalker : JsonLikePsiWalker {
	override fun findElementToCheck(element: PsiElement): PsiElement? {
		//得到需要检查的元素
		var current = element
		//如果是空白元素，则需要特殊处理
		if(current is PsiWhiteSpace) {
			val prev = current.prevSibling
			//（输入字符之后）如果之前存在元素，则得到之前的元素
			if(prev != null) current = current.prevSibling
		}
		while(true){
			if(current is PsiFile) return null
			if(current is StellarisScriptProperty || current is StellarisScriptPropertyKey ||
			   current is StellarisScriptPropertyValue || current is StellarisScriptItem) {
				return current
			}
			current = current.parent
		}
	}

	override fun isName(element: PsiElement): ThreeState {
		//将所有YES改为UNSURE
		return when(element) {
			is StellarisScriptProperty -> ThreeState.UNSURE
			is StellarisScriptPropertyKey -> ThreeState.UNSURE
			is StellarisScriptPropertyValue -> ThreeState.NO
			is StellarisScriptItem -> ThreeState.UNSURE
			else -> ThreeState.UNSURE
		}
	}

	override fun isTopJsonElement(element: PsiElement): Boolean {
		return element is StellarisScriptFile
	}

	override fun isPropertyWithValue(element: PsiElement): Boolean {
		return element is StellarisScriptProperty
	}

	override fun getRoots(file: PsiFile): MutableCollection<PsiElement> {
		return file.toSingletonOrEmpty()
	}

	override fun getParentPropertyAdapter(element: PsiElement): JsonPropertyAdapter? {
		val parent = element.parent
		return if(parent is StellarisScriptProperty) StellarisScriptPropertyAdapter(parent) else null
	}

	override fun getPropertyNameElement(property: PsiElement?): PsiElement? {
		return when(property) {
			is StellarisScriptProperty -> property.propertyKey
			is StellarisScriptItem -> property //首先会识别item而非property，因此这里是必须的？
			else -> null
		}
	}

	override fun getPropertyNamesOfParentObject(originalPosition: PsiElement, computedPosition: PsiElement?): MutableSet<String> {
		//parent也可以是file，这也合法
		return when(val parent = originalPosition.parent) {
			is StellarisScriptBlock -> parent.propertyList.mapNotNullTo(mutableSetOf()) { it.name }
			is StellarisScriptFile -> parent.properties.mapNotNullTo(mutableSetOf()) { it.name }
			else -> mutableSetOf()
		}
	}

	override fun createValueAdapter(element: PsiElement): JsonValueAdapter? {
		return when(element) {
			is StellarisScriptPropertyValue -> StellarisScriptValueAdapter(element)
			is StellarisScriptItem -> StellarisScriptItemAdapter(element)
			else -> null
		}
	}

	override fun findPosition(element: PsiElement, forceLastTransition: Boolean): JsonPointerPosition? {
		//如果当前输入的是/root/p，则需要得到的是/root

		val position = JsonPointerPosition()
		//var current = element
		var current = element.parent
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
		return true
	}

	override fun hasMissingCommaAfter(element: PsiElement): Boolean {
		return false
	}
}
