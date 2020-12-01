package com.windea.plugin.idea.pdx.localisation.editor

import com.intellij.codeInsight.navigation.actions.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationGoToDeclarationHandler : GotoDeclarationHandler {
	override fun getGotoDeclarationTargets(sourceElement: PsiElement?, offset: Int, editor: Editor?): Array<out PsiElement?>? {
		return when(sourceElement) {
			is PdxLocalisationProperty -> {
				val name = sourceElement.name
				val locale = (sourceElement.containingFile as? PdxLocalisationFile)?.pdxLocale
				//查找当前项目
				findLocalisationProperties(name, sourceElement.project, locale).toTypedArray()
			}
			else -> null
		}
	}
}
