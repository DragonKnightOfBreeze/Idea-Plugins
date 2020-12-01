package com.windea.plugin.idea.pdx.script.schema

import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.impl.*
import com.windea.plugin.idea.pdx.script.*

//name: descriptor.mod, events/*.txt

class PdxScriptSchemaProvider(
	private val name:String,
	private val schemaFile: VirtualFile
) : JsonSchemaFileProvider {
	private val reversedPathList = name.split("/").reversed()

	override fun isAvailable(file: VirtualFile): Boolean {
		return file.fileType == PdxScriptFileType && isMatched(file)
	}

	//判断被匹配的文件（脚本文件）是否与schema名字（ant路径）相匹配
	private fun isMatched(file: VirtualFile): Boolean {
		var current = file
		reversedPathList.forEach { path ->
			if(!matches(current.name,path)) return false
			current = current.parent
		}
		return true
	}
	
	//判断指定的路径是否匹配指定的路径表达式，允许的表达式：abc.txt, *.txt
	private fun matches(path:String,pattern:String):Boolean{
		return when {
			pattern.startsWith("*.") -> path.substringAfterLast('.') == pattern.removePrefix("*.")
			else -> path == pattern
		}
	}

	override fun getName(): String {
		return name
	}

	override fun getSchemaFile(): VirtualFile {
		return schemaFile
	}

	override fun getSchemaType(): SchemaType {
		return SchemaType.schema
	}

	override fun getSchemaVersion(): JsonSchemaVersion {
		return JsonSchemaVersion.SCHEMA_7
	}
}
