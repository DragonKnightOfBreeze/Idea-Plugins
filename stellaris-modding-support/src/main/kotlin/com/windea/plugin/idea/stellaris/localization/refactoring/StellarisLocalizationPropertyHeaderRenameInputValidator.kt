package com.windea.plugin.idea.stellaris.localization.refactoring

import com.intellij.openapi.project.*
import com.intellij.patterns.*
import com.intellij.psi.*
import com.intellij.refactoring.rename.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*

@ExtensionPoint
class StellarisLocalizationPropertyHeaderRenameInputValidator : RenameInputValidatorEx {
	override fun isInputValid(newName: String, element: PsiElement, context: ProcessingContext): Boolean {
		return stellarisLocalizationPropertyHeaderRegex.matches(newName)
	}

	override fun getPattern(): ElementPattern<out PsiElement> {
		return PlatformPatterns.psiElement(StellarisLocalizationTypes.PROPERTY_HEADER)
	}

	override fun getErrorMessage(newName: String, project: Project): String? {
		return StellarisBundle.message("stellaris.localization.rename.propertyHeader")
	}
}
