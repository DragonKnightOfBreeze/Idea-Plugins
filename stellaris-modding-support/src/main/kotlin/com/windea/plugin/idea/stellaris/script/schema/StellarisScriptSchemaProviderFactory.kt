package com.windea.plugin.idea.stellaris.script.schema

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
			//得到schema文件路径，并替换成标准的路径
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
		//file：schema文件的根目录
		file.children.forEach {
			when {
				//如果是描述符文件，则添加特殊的provider
				it.name == descriptorModFileName -> {
					providers += StellarisScriptSchemaProvider(descriptorModFileName, it)
				}
				//如果是目录，则递归
				it.isDirectory -> {
					val path = if(pathPrefix.isEmpty()) it.name else "$pathPrefix/${it.name}"
					addProviders(it, providers, path)
				}
				//如果是json schema文件，则添加provider
				it.extension == "json" -> {
					val nameWithoutExtension = it.nameWithoutExtension
					val wildcardExtension = nameWithoutExtension.substringAfterLast('.',"txt")
					providers += StellarisScriptSchemaProvider("$pathPrefix/*.${wildcardExtension}", it)
				}
			}
		}
	}
}

