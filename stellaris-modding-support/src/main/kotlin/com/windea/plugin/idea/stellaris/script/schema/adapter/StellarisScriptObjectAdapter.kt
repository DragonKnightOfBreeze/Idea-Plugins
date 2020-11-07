package com.windea.plugin.idea.stellaris.script.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptObjectAdapter(
	private val element: StellarisScriptBlock
): JsonObjectValueAdapter {
	override fun getDelegate() = element

	override fun getPropertyList() = element.propertyList.map { StellarisScriptPropertyAdapter(it) }

	override fun isNumberLiteral() = false

	override fun isBooleanLiteral() = false

	override fun isStringLiteral() = false

	override fun isArray() = false

	override fun isObject() = true

	override fun getAsArray() = null

	override fun getAsObject() = this
}

