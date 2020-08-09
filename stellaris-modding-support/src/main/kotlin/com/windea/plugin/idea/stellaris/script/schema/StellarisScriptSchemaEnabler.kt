package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.ide.scratch.*
import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.*

//org.jetbrains.yaml.schema.StellarisScriptSchemaEnabler

class StellarisScriptSchemaEnabler:JsonSchemaEnabler {
	override fun isEnabledForFile(file: VirtualFile, project: Project?): Boolean {
		val fileType = file.fileType
		if(fileType is LanguageFileType && fileType.language is StellarisScriptLanguage){
			return true
		}else if(project != null && ScratchUtil.isScratch(file)){
			val rootType = ScratchFileService.findRootType(file)
			return rootType != null && rootType.substituteLanguage(project,file) is StellarisScriptLanguage
		}else {
			return false
		}
	}

	override fun canBeSchemaFile(file: VirtualFile?): Boolean {
		return false
	}
}

