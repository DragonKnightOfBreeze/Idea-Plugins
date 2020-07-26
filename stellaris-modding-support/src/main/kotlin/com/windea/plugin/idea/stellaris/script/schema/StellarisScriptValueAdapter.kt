package com.windea.plugin.idea.stellaris.script.schema

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptValueAdapter(
	private val element: StellarisScriptPropertyValue
): JsonValueAdapter {
	override fun getDelegate() = element

	override fun isNull() = false

	override fun getAsObject(): JsonObjectValueAdapter? {
		val block = element.block?:return null
		return if(block.isObject) StellarisScriptObjectAdapter(block) else null
	}

	override fun getAsArray(): JsonArrayValueAdapter? {
		val block = element.block?:return null
		return if(block.isArray) StellarisScriptArrayAdapter(block) else null
	}

	override fun isStringLiteral() = element.string!=null

	override fun isBooleanLiteral() = element.boolean!= null

	override fun isNumberLiteral() = element.number != null

	override fun isObject() = element.block?.isObject?:false

	override fun isArray() = element.block?.isArray?:false
}
