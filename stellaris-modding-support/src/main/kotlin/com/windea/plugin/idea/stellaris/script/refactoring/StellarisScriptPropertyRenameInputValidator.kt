package com.windea.plugin.idea.stellaris.script.refactoring

import com.intellij.patterns.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.psi.*
import com.intellij.refactoring.rename.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

class StellarisScriptPropertyRenameInputValidator : RenameInputValidator {
	private val regex = "[a-zA-Z0-9.:\$_-]+".toRegex()
	private val pattern = or( psiElement(PROPERTY))
	
	override fun isInputValid(newName: String, element: PsiElement, context: ProcessingContext): Boolean {
		return regex.matches(newName)
	}

	override fun getPattern(): ElementPattern<out PsiElement> {
		return pattern
	}
}

