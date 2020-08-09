package com.windea.plugin.idea.stellaris.script.refactoring

import com.intellij.openapi.project.*
import com.intellij.patterns.*
import com.intellij.psi.*
import com.intellij.refactoring.rename.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptPropertyRenameInputValidator : RenameInputValidatorEx {
	override fun isInputValid(newName: String, element: PsiElement, context: ProcessingContext): Boolean {
		return stellarisScriptPropertyRegex.matches(newName)
	}

	override fun getPattern(): ElementPattern<out PsiElement> {
		return PlatformPatterns.psiElement(StellarisScriptTypes.PROPERTY)
	}

	override fun getErrorMessage(newName: String, project: Project): String? {
		return StellarisBundle.message("stellaris.script.rename.property")
	}
}

