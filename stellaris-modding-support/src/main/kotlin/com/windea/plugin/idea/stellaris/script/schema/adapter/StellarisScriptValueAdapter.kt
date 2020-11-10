package com.windea.plugin.idea.stellaris.script.schema.adapter

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

	//TODO isArray和isObject两者只能有一个是true，如果block是空的，需要推断到底是array还是object
	//TODO 首先得到rootSchema，然后得到jsonPosition，然后再得到需要的schema，根据schema.typeVariants进行推断

	override fun isArray() = element is StellarisScriptBlock && element.isArray

	override fun isObject() = element is StellarisScriptBlock && element.isObject

	private fun isArrayWhenEmpty() :Boolean{
		TODO()
	}

	override fun getAsArray() = if(isArray) StellarisScriptArrayAdapter(element as StellarisScriptBlock) else null

	override fun getAsObject() = if(isObject) StellarisScriptObjectAdapter(element as StellarisScriptBlock) else null
}
