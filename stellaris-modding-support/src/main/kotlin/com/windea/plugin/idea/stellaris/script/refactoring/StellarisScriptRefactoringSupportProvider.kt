package com.windea.plugin.idea.stellaris.script.refactoring

import com.intellij.lang.refactoring.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptRefactoringSupportProvider : RefactoringSupportProvider() {
	override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
		return element is StellarisScriptVariable || element is StellarisScriptVariableReference
	}

	override fun isSafeDeleteAvailable(element: PsiElement): Boolean {
		return element is StellarisScriptVariable
	}
}
