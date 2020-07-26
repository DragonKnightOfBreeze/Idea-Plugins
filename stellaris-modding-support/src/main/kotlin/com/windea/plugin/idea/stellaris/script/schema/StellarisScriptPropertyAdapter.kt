package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.psi.util.*
import com.jetbrains.jsonSchema.extension.adapters.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*
import java.util.*

class StellarisScriptPropertyAdapter(
	private val element: StellarisScriptProperty
) : JsonPropertyAdapter {
	override fun getDelegate() = element

	override fun getName() = element.name

	override fun getNameValueAdapter(): JsonValueAdapter? {
		val key = element.propertyKey
		return StellarisScriptKeyAdapter(key)
	}

	override fun getValues(): MutableCollection<JsonValueAdapter> {
		//认为property的key可以重复，因此value自然可能有多个
		val parent = element.parent
		return if(parent is StellarisScriptBlock){
			val name = element.name
			val values = parent.propertyList.mapNotNull { if(it.name == name) it.propertyValue else null}
			values.mapTo(mutableListOf()) { StellarisScriptValueAdapter(it) }
		}else{
			val value = element.propertyValue
			value?.let { StellarisScriptValueAdapter(it) }.toSingletonOrEmpty()
		}

		//val value = element.propertyValue
		//return value?.let { StellarisScriptValueAdapter(it) }.toSingletonOrEmpty()
	}

	override fun getParentObject(): JsonObjectValueAdapter? {
		val parent = element.parent
		return if(parent is StellarisScriptBlock) StellarisScriptObjectAdapter(parent) else null
	}
}
