package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*
import com.windea.plugin.idea.stellaris.*
import java.io.*

class StellarisScriptSchemaProviderFactory : JsonSchemaProviderFactory {
	override fun getProviders(project: Project): MutableList<JsonSchemaFileProvider> {
		val providers = mutableListOf<JsonSchemaFileProvider>()

		//实际上读取的是jar中的文件
		val schemaUrl = "schema".toClassPathResource()
		if(schemaUrl != null) {
			//替换成标准的路径
			val schemaPath = schemaUrl.path.replace("%20", " ").removePrefix("file:/").removeSuffix("/")
			val jarFileSystem = JarFileSystem.getInstance()
			val schemaFile = jarFileSystem.findFileByPath(schemaPath)
			if(schemaFile != null) {
				addProviders(schemaFile, providers)
			}
		}
		return providers
	}

	private fun addProviders(file: VirtualFile, providers: MutableList<JsonSchemaFileProvider>, pathPrefix: String = "") {
		file.children.forEach {
			when {
				it.isDirectory -> {
					addProviders(it, providers, "$pathPrefix/${it.name}")
				}
				it.name == "descriptor.yaml" -> {
					providers += StellarisScriptSchemaProvider("descriptor.mod", false, it)
				}
				it.extension == "yaml" -> {
					//TODO 等待Schema文件编写完毕
					providers += StellarisScriptSchemaProvider("$pathPrefix/${it.nameWithoutExtension}", true, it)
				}
			}
		}
	}

	private fun File.toVirtualFileUrl(): String {
		return this.toURI().toURL().toString()
	}

	private fun File.toModDirectory(): String? {
		var current = this
		var result = current.nameWithoutExtension
		while(current != workDirectory) {
			current = current.parentFile
			if(current.name == "schema") return result
			result = current.name + "/" + result
		}
		return null
	}
}

