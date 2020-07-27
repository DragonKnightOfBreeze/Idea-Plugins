package com.windea.plugin.idea.stellaris.script.schema

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptArrayAdapter(
	private val element:StellarisScriptBlock
): JsonArrayValueAdapter{
	private val adapters by lazy {
		computeAdapters()
	}

	private fun computeAdapters():List<JsonValueAdapter>{
		return element.itemList.map { StellarisScriptItemAdapter(it) }
	}

	override fun getDelegate() = element

	override fun getElements() = adapters

	override fun getAsObject() = null

	override fun getAsArray() = this

	override fun isStringLiteral() = false

	override fun isBooleanLiteral() = false

	override fun isNumberLiteral() = false

	override fun isObject() = false

	override fun isArray() = true
}
