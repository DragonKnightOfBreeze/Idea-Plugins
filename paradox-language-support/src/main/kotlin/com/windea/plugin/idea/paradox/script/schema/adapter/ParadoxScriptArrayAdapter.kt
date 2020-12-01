package com.windea.plugin.idea.paradox.script.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.paradox.script.psi.*

class ParadoxScriptArrayAdapter(
	private val element: ParadoxScriptBlock
): JsonArrayValueAdapter {
	override fun getDelegate() = element

	override fun getElements() = element.valueList.mapNotNull{ ParadoxScriptValueAdapter(it) }

	override fun isBooleanLiteral() = false

	override fun isNumberLiteral() = false

	override fun isStringLiteral() = false

	override fun isArray() = true

	override fun isObject() = false

	override fun getAsArray() = this

	override fun getAsObject() = null
}

