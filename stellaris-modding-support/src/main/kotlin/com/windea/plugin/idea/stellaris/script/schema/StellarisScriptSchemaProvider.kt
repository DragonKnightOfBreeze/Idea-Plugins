package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*
import com.windea.plugin.idea.stellaris.script.*

class StellarisScriptSchemaProvider(
	private val name:String,
	private val schemaFile: VirtualFile
): JsonSchemaFileProvider {
	override fun isAvailable(file: VirtualFile): Boolean {
		val fileType = file.fileType
		return fileType is LanguageFileType && fileType.language is StellarisScriptLanguage
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
}
