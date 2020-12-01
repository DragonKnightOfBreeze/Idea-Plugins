package com.windea.plugin.idea.pdx.script.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptPropertyAdapter(
	private val element: PdxScriptProperty
) : JsonPropertyAdapter {
	override fun getDelegate() = element

	override fun getName() = element.name

	override fun getNameValueAdapter() = PdxScriptPropertyKeyAdapter(element.propertyKey)

	override fun getValues(): Collection<JsonValueAdapter> {
		//TODO 如何对待重复的key的情况？需要确认
		return element.propertyValue?.value?.let{ PdxScriptValueAdapter(it) }.toSingletonOrEmpty()
	}

	override fun getParentObject(): JsonObjectValueAdapter? {
		val parent = element.parent
		return when{
			parent is PdxScriptBlock -> PdxScriptObjectAdapter(parent)
			else ->  null
		}
	}
}
