package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*
import com.windea.plugin.idea.stellaris.script.*

class StellarisScriptSchemaProvider(
	private val pattern:String,
	private val isDirectory :Boolean = false,
	private val schemaFile: VirtualFile
): JsonSchemaFileProvider {
	override fun isAvailable(file: VirtualFile): Boolean {
		val fileType = file.fileType
		return fileType is LanguageFileType && fileType.language is StellarisScriptLanguage && isMatched(file)
	}

	private fun isMatched(file: VirtualFile):Boolean{
		return if(isDirectory){
			val dirs = pattern.split("/")
			val current = file.parent
			dirs.reversed().forEach { it ->
				if(it != current.name) return false
			}
			true
		}else{
			file.name == pattern
		}
	}

	override fun getName(): String {
		return schemaFile.name
	}

	override fun getSchemaFile(): VirtualFile? {
		return schemaFile
	}

	override fun getSchemaType(): SchemaType {
		return SchemaType.schema
	}
}
