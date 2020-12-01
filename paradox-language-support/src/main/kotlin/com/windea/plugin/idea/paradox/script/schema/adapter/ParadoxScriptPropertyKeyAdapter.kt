package com.windea.plugin.idea.paradox.script.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.script.psi.*

class ParadoxScriptPropertyKeyAdapter(
	private val element: ParadoxScriptPropertyKey
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
