package com.windea.plugin.idea.pdx.script.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.jetbrains.jsonSchema.ide.*
import com.jetbrains.jsonSchema.impl.*
import com.windea.plugin.idea.pdx.script.psi.*
import com.windea.plugin.idea.pdx.script.schema.*

class PdxScriptValueAdapter(
	private val element: PdxScriptValue
) : JsonValueAdapter {
	override fun getDelegate() = element

	override fun isNull() = false

	override fun isBooleanLiteral() = element is PdxScriptBoolean

	override fun isNumberLiteral() = element is PdxScriptNumber

	override fun isStringLiteral() = element is PdxScriptStringValue

	//isArray和isObject两者只能有一个是true，如果block是空的，需要推断到底是array还是object
	//首先得到rootSchema，然后得到jsonPosition，然后再得到需要的schema，根据schema.typeVariants进行推断
	//如果不接受schema，默认为object

	override fun isArray() = element is PdxScriptBlock &&
	                         (element.isArray || element.isEmpty && isArrayOrObjectWhenEmpty() == true)

	override fun isObject() = element is PdxScriptBlock &&
	                          (element.isObject || element.isEmpty && isArrayOrObjectWhenEmpty() != true)

	//TODO 提高性能

	private fun isArrayOrObjectWhenEmpty(): Boolean? {
		//有schemaObject和position可以确定result
		val jsonSchemaService = JsonSchemaService.Impl.get(element.project)
		val schemaObject = jsonSchemaService.getSchemaObject(element.containingFile) ?: return null
		val position = PdxScriptJsonLikePsiWalker.findPosition(element, false) ?: return null
		val schemas = JsonSchemaResolver(element.project, schemaObject, position).resolve()
		for(schema in schemas) {
			when {
				schema.acceptArrayType(jsonSchemaService) -> return true
				schema.acceptObjectType(jsonSchemaService) -> return false
			}
		}
		return null
	}

	private fun JsonSchemaObject.acceptArrayType(service: JsonSchemaService): Boolean {
		if(type == JsonSchemaType._array) return true
		if(typeVariants?.contains(JsonSchemaType._array) == true) return true
		if(hasArrayChecks()) return true
		//允许只有$ref，但不允许只有anyOf等
		if(!ref.isNullOrBlank() && resolveRefSchema(service)?.acceptArrayType(service) == true) return true
		return false
	}

	private fun JsonSchemaObject.acceptObjectType(service: JsonSchemaService): Boolean {
		if(type == JsonSchemaType._object) return true
		if(typeVariants?.contains(JsonSchemaType._object) == true) return true
		if(hasArrayChecks()) return true
		//允许只有$ref，但不允许只有anyOf等
		if(!ref.isNullOrBlank() && resolveRefSchema(service)?.acceptObjectType(service) == true) return true
		return false
	}

	override fun getAsArray() = if(isArray) PdxScriptArrayAdapter(element as PdxScriptBlock) else null

	override fun getAsObject() = if(isObject) PdxScriptObjectAdapter(element as PdxScriptBlock) else null
}
