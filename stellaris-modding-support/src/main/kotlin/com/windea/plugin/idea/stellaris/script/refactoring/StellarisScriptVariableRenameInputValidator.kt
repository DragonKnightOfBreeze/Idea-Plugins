package com.windea.plugin.idea.stellaris.script.refactoring

import com.intellij.openapi.project.*
import com.intellij.patterns.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.psi.*
import com.intellij.refactoring.rename.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

class StellarisScriptVariableRenameInputValidator : RenameInputValidatorEx {
	override fun isInputValid(newName: String, element: PsiElement, context: ProcessingContext): Boolean {
		return stellarisScriptVariableRegex.matches(newName)
	}

	override fun getPattern(): ElementPattern<out PsiElement> {
		return psiElement(VARIABLE_DEFINITION).andOr(psiElement(VARIABLE_REFERENCE))
	}

	override fun getErrorMessage(newName: String, project: Project): String? {
		return message("stellaris.script.rename.variable")
	}
}

