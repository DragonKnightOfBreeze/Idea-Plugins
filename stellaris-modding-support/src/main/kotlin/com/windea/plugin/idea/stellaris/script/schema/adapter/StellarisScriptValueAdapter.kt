package com.windea.plugin.idea.stellaris.script.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.jetbrains.jsonSchema.ide.*
import com.jetbrains.jsonSchema.impl.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.schema.*
import java.util.concurrent.*

class StellarisScriptValueAdapter(
	private val element: StellarisScriptValue
): JsonValueAdapter {
	override fun getDelegate() = element

	override fun isNull() = false

	override fun isBooleanLiteral() = element is StellarisScriptBoolean

	override fun isNumberLiteral() = element is StellarisScriptNumber

	override fun isStringLiteral() = element is StellarisScriptStringValue

	//isArray和isObject两者只能有一个是true，如果block是空的，需要推断到底是array还是object
	//首先得到rootSchema，然后得到jsonPosition，然后再得到需要的schema，根据schema.typeVariants进行推断

	override fun isArray() = element is StellarisScriptBlock &&
	                         (element.isArray || element.isEmpty && isArrayWhenEmpty() == true)

	override fun isObject() = element is StellarisScriptBlock &&
	                          (element.isObject || element.isEmpty && isArrayWhenEmpty() == false)

	private fun isArrayWhenEmpty():Boolean?{
		//有schemaObject和position可以确定result
		val jsonSchemaService = JsonSchemaService.Impl.get(element.project)
		val schemaObject = jsonSchemaService.getSchemaObject(element.containingFile)?:return null
		val position = StellarisScriptJsonLikePsiWalker.findPosition(element,false)?:return null
		val schemas = JsonSchemaResolver(element.project,schemaObject,position).resolve()
		for(schema in schemas) {
			val types = schema.typeVariants ?: continue
			when{
				JsonSchemaType._array in types -> return true
				JsonSchemaType._object in types -> return false
			}
		}
		return null
	}

	override fun getAsArray() = if(isArray) StellarisScriptArrayAdapter(element as StellarisScriptBlock) else null

	override fun getAsObject() = if(isObject) StellarisScriptObjectAdapter(element as StellarisScriptBlock) else null
}
