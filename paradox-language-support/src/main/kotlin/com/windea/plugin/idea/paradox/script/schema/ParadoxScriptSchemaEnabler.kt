package com.windea.plugin.idea.paradox.script.schema

import com.intellij.ide.scratch.*
import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*
import com.windea.plugin.idea.paradox.script.*

//org.jetbrains.yaml.schema.ParadoxScriptSchemaEnabler

class ParadoxScriptSchemaEnabler:JsonSchemaEnabler {
	override fun isEnabledForFile(file: VirtualFile, project: Project?): Boolean {
		return file.fileType == ParadoxScriptFileType
		//val fileType = file.fileType
		//return fileType is LanguageFileType && fileType.language is ParadoxScriptLanguage
	}

	override fun canBeSchemaFile(file: VirtualFile?): Boolean {
		return false
	}
}

