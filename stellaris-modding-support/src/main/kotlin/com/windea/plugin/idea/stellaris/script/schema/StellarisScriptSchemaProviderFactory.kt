package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*

class StellarisScriptSchemaProviderFactory : JsonSchemaProviderFactory {
	override fun getProviders(project: Project): MutableList<JsonSchemaFileProvider> {
		val result = mutableListOf<JsonSchemaFileProvider>()
		val manager = VirtualFileManager.getInstance()
		//result += StellarisScriptSchemaProvider("descriptor",)
		return result
	}
}

