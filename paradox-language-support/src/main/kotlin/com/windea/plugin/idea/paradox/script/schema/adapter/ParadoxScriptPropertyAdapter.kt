package com.windea.plugin.idea.paradox.script.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.script.psi.*

class ParadoxScriptPropertyAdapter(
	private val element: ParadoxScriptProperty
) : JsonPropertyAdapter {
	override fun getDelegate() = element

	override fun getName() = element.name

	override fun getNameValueAdapter() = ParadoxScriptPropertyKeyAdapter(element.propertyKey)

	override fun getValues(): Collection<JsonValueAdapter> {
		//TODO 如何对待重复的key的情况？需要确认
		return element.propertyValue?.value?.let{ ParadoxScriptValueAdapter(it) }.toSingletonOrEmpty()
	}

	override fun getParentObject(): JsonObjectValueAdapter? {
		val parent = element.parent
		return when{
			parent is ParadoxScriptBlock -> ParadoxScriptObjectAdapter(parent)
			else ->  null
		}
	}
}
