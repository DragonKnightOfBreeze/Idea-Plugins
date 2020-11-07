package com.windea.plugin.idea.stellaris.schema

import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.impl.*
import com.windea.plugin.idea.stellaris.script.*

class StellarisScriptSchemaProvider(
	private val name:String,
	private val isDirectory :Boolean = false,
	private val schemaFile: VirtualFile
) : JsonSchemaFileProvider {
	private val reversedPathList = name.split("/").reversed()

	override fun isAvailable(file: VirtualFile): Boolean {
		return file.fileType == StellarisScriptFileType && isMatched(file)
		//val fileType = file.fileType
		//return fileType is LanguageFileType && fileType.language is StellarisScriptLanguage && isMatched(file)  && isMatched(file)
	}

	private fun isMatched(file: VirtualFile): Boolean {
		//匹配名字与当前文件的文件名和目录
		var current = file
		if(isDirectory) current = file.parent
		reversedPathList.forEach {
			if(it != current.name) return false
			current = current.parent
		}
		return true
	}

	override fun getName(): String {
		return name
	}

	override fun getSchemaFile(): VirtualFile? {
		return schemaFile
	}

	override fun getSchemaType(): SchemaType {
		return SchemaType.schema
	}

	override fun getSchemaVersion(): JsonSchemaVersion {
		return JsonSchemaVersion.SCHEMA_7
	}
}
