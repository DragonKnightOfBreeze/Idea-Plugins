package com.windea.plugin.idea.pdx.script.schema

import com.intellij.ide.scratch.*
import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*
import com.windea.plugin.idea.pdx.script.*

//org.jetbrains.yaml.schema.PdxScriptSchemaEnabler

class PdxScriptSchemaEnabler:JsonSchemaEnabler {
	override fun isEnabledForFile(file: VirtualFile, project: Project?): Boolean {
		return file.fileType == PdxScriptFileType
		//val fileType = file.fileType
		//return fileType is LanguageFileType && fileType.language is PdxScriptLanguage
	}

	override fun canBeSchemaFile(file: VirtualFile?): Boolean {
		return false
	}
}

