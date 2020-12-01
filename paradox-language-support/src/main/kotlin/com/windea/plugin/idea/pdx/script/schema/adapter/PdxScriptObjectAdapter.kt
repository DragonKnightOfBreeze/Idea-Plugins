package com.windea.plugin.idea.pdx.script.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptObjectAdapter(
	private val element: PdxScriptBlock
): JsonObjectValueAdapter {
	override fun getDelegate() = element

	override fun getPropertyList() = element.propertyList.map { PdxScriptPropertyAdapter(it) }

	override fun isNumberLiteral() = false

	override fun isBooleanLiteral() = false

	override fun isStringLiteral() = false

	override fun isArray() = false

	override fun isObject() = true

	override fun getAsArray() = null

	override fun getAsObject() = this
}

