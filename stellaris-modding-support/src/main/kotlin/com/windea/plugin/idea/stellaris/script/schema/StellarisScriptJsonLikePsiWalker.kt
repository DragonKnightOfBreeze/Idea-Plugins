package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.json.pointer.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.intellij.util.*
import com.intellij.util.containers.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*

//org.jetbrains.yaml.schema.YamlJsonPsiWalker

object StellarisScriptJsonLikePsiWalker : JsonLikePsiWalker {
	override fun findElementToCheck(element: PsiElement): PsiElement? {
		//得到需要检查的元素
		//如果是item，则得到父元素（限于语法解析）
		//如果是空白，判断之前的元素是否是item，若是则得到之前的元素
		var current = element
		if(current is PsiWhiteSpace){
			val prev = current.prevSibling
			if(prev is StellarisScriptItem) current = prev
		}
		while(current !is PsiFile){
			when(current) {
				is StellarisScriptProperty, is StellarisScriptPropertyKey, is StellarisScriptPropertyValue -> {
					return current
				}
				is StellarisScriptItem -> {
					return current.parent
				}
				else -> current = current.parent
			}
		}
		return null
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
		//可能是psiFile，也可能子元素是psiFile，因为底层逻辑是判断root的父元素是否是topJsonElement
		return element is StellarisScriptFile || (element is PsiDirectory)
	}

	override fun isPropertyWithValue(element: PsiElement): Boolean {
		return element is StellarisScriptProperty
	}

	override fun getRoots(file: PsiFile): MutableCollection<PsiElement> {
		val root =  PsiTreeUtil.findChildOfType(file,StellarisScriptPropertyValue::class.java)
		return ContainerUtil.createMaybeSingletonList(root)
	}

	override fun getParentPropertyAdapter(element: PsiElement): JsonPropertyAdapter? {
		val parent = element.parentOfType<StellarisScriptProperty>(true)
		return if(parent != null) StellarisScriptPropertyAdapter(parent) else null
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
