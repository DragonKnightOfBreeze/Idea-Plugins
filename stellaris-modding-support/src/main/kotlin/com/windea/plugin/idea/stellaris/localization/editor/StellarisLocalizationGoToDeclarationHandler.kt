package com.windea.plugin.idea.stellaris.localization.editor

import com.intellij.codeInsight.navigation.actions.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationGoToDeclarationHandler : GotoDeclarationHandler {
	override fun getGotoDeclarationTargets(sourceElement: PsiElement?, offset: Int, editor: Editor?): Array<out PsiElement?>? {
		return when(sourceElement) {
			is StellarisLocalizationProperty -> {
				val name = sourceElement.name ?: return null
				val locale = (sourceElement.containingFile as? StellarisLocalizationFile)?.stellarisLocale
				//查找当前项目
				findLocalizationProperties(name, sourceElement.project, locale).toTypedArray()
			}
			else -> null
		}
	}
}
