package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.*
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.script.*

class StellarisLocalizationLanguageSubstitutor: LanguageSubstitutor() {
	override fun getLanguage(file: VirtualFile, project: Project): Language? {
		var currentFile: VirtualFile? = file.parent
		while(currentFile != null){
			if(currentFile.findChild(descriptorFileName) != null){
				return when(file.extension){
					"yml" -> StellarisLocalizationLanguage
					else -> null
				}
			}
			currentFile = currentFile.parent
		}
		return null
	}
}
