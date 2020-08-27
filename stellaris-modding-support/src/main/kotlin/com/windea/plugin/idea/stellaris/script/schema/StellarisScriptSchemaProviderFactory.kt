package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*
import com.windea.plugin.idea.stellaris.*

class StellarisScriptSchemaProviderFactory : JsonSchemaProviderFactory {
	override fun getProviders(project: Project): MutableList<JsonSchemaFileProvider> {
		val providers = mutableListOf<JsonSchemaFileProvider>()

		//实际上读取的是jar中的文件
		val schemaUrl = "jsonSchema".toClassPathResource()
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
				it.nameWithoutExtension == "descriptor" -> {
					providers += StellarisScriptSchemaProvider("descriptor.mod", false, it)
				}
				//TODO 等待Schema文件编写完毕
				//it.extension == "json" -> {
				//	providers += StellarisScriptSchemaProvider("$pathPrefix/${it.nameWithoutExtension}".removePrefix("/"), true, it)
				//}
			}
		}
	}
}

