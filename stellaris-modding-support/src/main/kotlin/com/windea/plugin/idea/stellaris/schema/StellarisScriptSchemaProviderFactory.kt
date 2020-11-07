package com.windea.plugin.idea.stellaris.schema

import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*
import com.windea.plugin.idea.stellaris.*

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
				//如果是目录，则递归
				it.isDirectory -> {
					val path = "$pathPrefix/${it.name}"
					addProviders(it, providers, path)
				}
				//如果是描述符文件
				it.nameWithoutExtension == "descriptor" -> {
					providers += StellarisScriptSchemaProvider("descriptor.mod", false, it)
				}
				//如果是json schema文件，需要添加provider，并且将短名称中的"."替换成"/"
				//TODO 等待Schema文件编写完毕
				it.extension == "json" -> {
					val path = "$pathPrefix/${it.nameWithoutExtension.replace('.','/')}".removePrefix("/")
					providers += StellarisScriptSchemaProvider(path, true, it)
				}
			}
		}
	}
}

