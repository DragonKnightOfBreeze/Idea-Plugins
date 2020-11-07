package com.windea.plugin.idea.stellaris.script.schema.adapter

import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptPropertyAdapter(
	private val element: StellarisScriptProperty
) : JsonPropertyAdapter {
	override fun getDelegate() = element

	override fun getName() = element.name

	override fun getNameValueAdapter() = StellarisScriptPropertyKeyAdapter(element.propertyKey)

	override fun getValues(): Collection<JsonValueAdapter> {
		//TODO 如何对待重复的key的情况？需要确认
		return element.propertyValue?.value?.let{ StellarisScriptValueAdapter(it) }.toSingletonOrEmpty()
	}

	override fun getParentObject(): JsonObjectValueAdapter? {
		val parent = element.parent
		return when{
			parent is StellarisScriptBlock -> StellarisScriptObjectAdapter(parent)
			parent is StellarisScriptFile -> StellarisScriptObjectFromFileAdapter(parent)
			else ->  null
		}
	}
}
