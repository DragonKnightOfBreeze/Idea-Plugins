package com.windea.plugin.idea.pdx.script.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptPropertyKeyAdapter(
	private val element: PdxScriptPropertyKey
): JsonValueAdapter {
	override fun getDelegate() = element

	override fun isNull() = false

	override fun isBooleanLiteral() = element.text.unquote().isBoolean()

	override fun isNumberLiteral() = element.text.unquote().isNumber()

	override fun isStringLiteral() = true

	override fun isArray() = false

	override fun isObject() = false

	override fun getAsArray() = null

	override fun getAsObject() = null

}
