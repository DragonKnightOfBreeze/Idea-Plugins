package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.psi.*
import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptObjectAdapter(
	private val element: PsiElement
): JsonObjectValueAdapter {
	private val adapters by lazy {
		computeAdapters()
	}

	private fun computeAdapters():List<JsonPropertyAdapter>{
		return when(element){
			is StellarisScriptBlock ->element.propertyList.map { StellarisScriptPropertyAdapter(it) }
			is StellarisScriptFile -> element.properties.map {  StellarisScriptPropertyAdapter(it)}
			else -> listOf()
		}
	}

	override fun getDelegate() = element

	override fun getPropertyList() = adapters

	override fun getAsObject() = this

	override fun getAsArray() = null

	override fun isStringLiteral() = false

	override fun isBooleanLiteral() = false

	override fun isNumberLiteral() = false

	override fun isObject() = true

	override fun isArray() = false
}
