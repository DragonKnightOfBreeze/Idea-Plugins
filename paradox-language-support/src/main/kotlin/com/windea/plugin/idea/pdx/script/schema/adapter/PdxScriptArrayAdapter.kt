package com.windea.plugin.idea.pdx.script.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptArrayAdapter(
	private val element: PdxScriptBlock
): JsonArrayValueAdapter {
	override fun getDelegate() = element

	override fun getElements() = element.valueList.mapNotNull{ PdxScriptValueAdapter(it) }

	override fun isBooleanLiteral() = false

	override fun isNumberLiteral() = false

	override fun isStringLiteral() = false

	override fun isArray() = true

	override fun isObject() = false

	override fun getAsArray() = this

	override fun getAsObject() = null
}

