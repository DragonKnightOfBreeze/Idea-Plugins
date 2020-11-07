package com.windea.plugin.idea.stellaris.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptValueAdapter(
	private val element: StellarisScriptValue
): JsonValueAdapter {
	override fun getDelegate() = element

	override fun isNull() = false

	override fun isBooleanLiteral() = element is StellarisScriptBoolean

	override fun isNumberLiteral() = element is StellarisScriptNumber

	override fun isStringLiteral() = element is StellarisScriptStringValue

	override fun isArray() = element is StellarisScriptBlock && element.isArray

	override fun isObject() = element is StellarisScriptBlock && element.isObject

	override fun getAsArray() = if(isObject) StellarisScriptArrayAdapter(element as StellarisScriptBlock) else null

	override fun getAsObject() = if(isObject) StellarisScriptObjectAdapter(element as StellarisScriptBlock) else null
}
