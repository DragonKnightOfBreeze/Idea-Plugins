package com.windea.plugin.idea.stellaris.schema

import com.intellij.ide.scratch.*
import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*
import com.windea.plugin.idea.stellaris.script.*

//org.jetbrains.yaml.schema.StellarisScriptSchemaEnabler

class StellarisScriptSchemaEnabler:JsonSchemaEnabler {
	override fun isEnabledForFile(file: VirtualFile, project: Project?): Boolean {
		return file.fileType == StellarisScriptFileType
		//val fileType = file.fileType
		//return fileType is LanguageFileType && fileType.language is StellarisScriptLanguage
	}

	override fun canBeSchemaFile(file: VirtualFile?): Boolean {
		return false
	}
}

