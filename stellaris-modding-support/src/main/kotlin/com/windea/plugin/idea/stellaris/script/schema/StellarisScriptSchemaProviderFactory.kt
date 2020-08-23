package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*
import com.windea.plugin.idea.stellaris.*
import java.io.*

class StellarisScriptSchemaProviderFactory : JsonSchemaProviderFactory {
	override fun getProviders(project: Project): MutableList<JsonSchemaFileProvider> {
		val result = mutableListOf<JsonSchemaFileProvider>()
		val schemaUrl = "schema".toClassPathResource()
		if(schemaUrl!=null){
			val manager = VirtualFileManager.getInstance()
			val schemaFile = File(schemaUrl.toURI())
			schemaFile.walk().forEach {
				when {
					it.isDirectory || it.extension != "yml" || it.extension != "yaml" -> return@forEach
					it.name == "descriptor.mod" -> {
						val pattern = "descriptor.mod"
						val virtualFile = manager.findFileByUrl(it.toVirtualFileUrl())?:return@forEach
						result += StellarisScriptSchemaProvider(pattern,false,virtualFile)
					}
					else ->{
						//TODO 等待完全完成Schema文件的编写
						//val pattern = it.toModDirectory()?:return@forEach
						//val virtualFile = manager.findFileByUrl(it.toVirtualFileUrl())?:return@forEach
						//result += StellarisScriptSchemaProvider(pattern,true,virtualFile)
					}
				}
			}
		}
		return result
	}

	private fun File.toVirtualFileUrl(): String {
		return this.toURI().toURL().toString()
	}

	private fun File.toModDirectory():String? {
		var current = this
		var result = current.nameWithoutExtension
		while(current != workDirectory){
			current = current.parentFile
			if(current.name=="schema") return result
			result = current.name + "/" + result
		}
		return null
	}
}

