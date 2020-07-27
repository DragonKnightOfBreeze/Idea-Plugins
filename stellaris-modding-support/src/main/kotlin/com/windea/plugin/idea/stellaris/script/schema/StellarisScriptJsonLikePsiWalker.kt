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
	override fun isName(element: PsiElement): ThreeState {
		return when(element) {
			is StellarisScriptPropertyKey -> ThreeState.YES
			is StellarisScriptPropertyValue -> ThreeState.NO
			else -> {
				val parent = element.parent
				if(parent is StellarisScriptBlock) {
					when {
						parent.isEmpty -> ThreeState.UNSURE
						parent.isObject -> ThreeState.YES
						parent.isArray -> ThreeState.NO
						else -> ThreeState.UNSURE
					}
				} else ThreeState.UNSURE
			}
		}
	}

	override fun isPropertyWithValue(element: PsiElement): Boolean {
		return element is StellarisScriptProperty
	}

	override fun isTopJsonElement(element: PsiElement): Boolean {
		return element is StellarisScriptFile
	}

	override fun acceptsEmptyRoot(): Boolean {
		return true
	}

	override fun findElementToCheck(element: PsiElement): PsiElement? {
		//得到需要检查的元素（是property，或者parent是block）
		var current = element
		while(true){
			if(current !is PsiFile){
				if(current !is StellarisScriptProperty && current.parent !is StellarisScriptBlock){
					current = current.parent
					continue
				}
				return current
			}
			return null
		}
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

	override fun createValueAdapter(element: PsiElement): JsonValueAdapter? {
		return when{
			element is StellarisScriptPropertyValue -> StellarisScriptValueAdapter(element)
			element is StellarisScriptItem && element.parent is StellarisScriptBlock -> StellarisScriptItemAdapter(element)
			else -> null
		}
	}

	override fun getParentPropertyAdapter(element: PsiElement): JsonPropertyAdapter? {
		val parent = element.parent
		return if(parent is StellarisScriptProperty) StellarisScriptPropertyAdapter(parent) else null
	}

	override fun getPropertyNameElement(property: PsiElement?): PsiElement? {
		return if(property is StellarisScriptProperty) property.propertyKey else null
	}

	override fun getPropertyNamesOfParentObject(originalPosition: PsiElement, computedPosition: PsiElement?): MutableSet<String> {
		//parent也可以是file，这也合法
		return when(val parent =  originalPosition.parent) {
			is StellarisScriptBlock -> parent.propertyList.mapNotNullTo(mutableSetOf()){it.name}
			is StellarisScriptFile -> parent.properties.mapNotNullTo(mutableSetOf()){it.name}
			else -> mutableSetOf()
		}
	}

	override fun getRoots(file: PsiFile): MutableCollection<PsiElement> {
		return file.toSingletonOrEmpty()
	}

	//TODO 检查
	override fun findPosition(element: PsiElement, forceLastTransition: Boolean): JsonPointerPosition? {
		val pos = JsonPointerPosition()

		var current = element

		while(true) {
			if(!breakCondition(current)) {
				val position = current
				current = current.parent
				if(current is StellarisScriptBlock && current.isArray) {
					val array: StellarisScriptBlock = current
					val expressions: List<StellarisScriptItem> = array.itemList
					var idx = -1
					for(i in expressions.indices) {
						val value = expressions[i]
						if(position == value) {
							idx = i
							break
						}
					}
					if(idx != -1) {
						pos.addPrecedingStep(idx)
					}
					continue
				}
				if(current is StellarisScriptString && current.parent is StellarisScriptBlock) {
					continue
				}
				if(current is StellarisScriptProperty) {
					val propertyName = current.name?:return null
					current = current.parent
					if(current !is StellarisScriptBlock) return null
					pos.addPrecedingStep(propertyName)
					continue
				}
				if(!breakCondition(current)) {
					if(current is StellarisScriptBlock && current.isObject) {
						val properties = current.propertyList
						if(properties.isEmpty()) return null
						if(position in properties) continue
					}
					return null
				}
			}
			return pos
		}
	}

	private fun breakCondition(current: PsiElement): Boolean {
		return current is PsiFile
	}
}
