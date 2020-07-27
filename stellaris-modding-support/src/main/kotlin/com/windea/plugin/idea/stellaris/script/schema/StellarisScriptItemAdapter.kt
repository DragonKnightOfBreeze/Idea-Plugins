package com.windea.plugin.idea.stellaris.script.schema

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptItemAdapter(
	private val element: StellarisScriptItem
): JsonValueAdapter {
	override fun getDelegate() = element

	override fun isNull() = false

	override fun getAsObject() = null

	override fun getAsArray() = null

	override fun isStringLiteral() = true

	override fun isBooleanLiteral() = false

	override fun isNumberLiteral() = false

	override fun isObject() = false

	override fun isArray() = false
}
