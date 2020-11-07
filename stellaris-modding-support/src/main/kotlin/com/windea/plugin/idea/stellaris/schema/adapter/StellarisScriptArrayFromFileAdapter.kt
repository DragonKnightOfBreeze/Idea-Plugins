package com.windea.plugin.idea.stellaris.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptArrayFromFileAdapter(
	private val element: StellarisScriptFile
): JsonArrayValueAdapter {
	override fun getDelegate() = element

	override fun getElements() = element.items.map{ StellarisScriptValueAdapter(it.value) }

	override fun isBooleanLiteral() = false

	override fun isNumberLiteral() = false

	override fun isStringLiteral() = false

	override fun isArray() = true

	override fun isObject() = false

	override fun getAsArray() = this

	override fun getAsObject() = null
}
