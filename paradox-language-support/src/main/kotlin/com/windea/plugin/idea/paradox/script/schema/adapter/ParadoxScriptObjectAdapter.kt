package com.windea.plugin.idea.paradox.script.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.paradox.script.psi.*

class ParadoxScriptObjectAdapter(
	private val element: ParadoxScriptBlock
): JsonObjectValueAdapter {
	override fun getDelegate() = element

	override fun getPropertyList() = element.propertyList.map { ParadoxScriptPropertyAdapter(it) }

	override fun isNumberLiteral() = false

	override fun isBooleanLiteral() = false

	override fun isStringLiteral() = false

	override fun isArray() = false

	override fun isObject() = true

	override fun getAsArray() = null

	override fun getAsObject() = this
}

