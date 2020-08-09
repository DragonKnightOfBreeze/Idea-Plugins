package com.windea.plugin.idea.stellaris.localization.refactoring

import com.intellij.openapi.project.*
import com.intellij.patterns.*
import com.intellij.psi.*
import com.intellij.refactoring.rename.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationPropertyRenameInputValidator : RenameInputValidatorEx {
	override fun isInputValid(newName: String, element: PsiElement, context: ProcessingContext): Boolean {
		return stellarisLocalizationPropertyKeyRegex.matches(newName)
	}

	override fun getPattern(): ElementPattern<out PsiElement> {
		return PlatformPatterns.psiElement(StellarisLocalizationTypes.PROPERTY)
	}

	override fun getErrorMessage(newName: String, project: Project): String? {
		return message("stellaris.localization.rename.property")
	}
}

