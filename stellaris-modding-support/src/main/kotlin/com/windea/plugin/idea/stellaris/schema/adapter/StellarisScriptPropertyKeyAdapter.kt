package com.windea.plugin.idea.stellaris.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptPropertyKeyAdapter(
	private val element: StellarisScriptPropertyKey
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
