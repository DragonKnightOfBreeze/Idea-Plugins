package com.windea.plugin.idea.pdx.script.refactoring

import com.intellij.lang.refactoring.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.localisation.psi.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptRefactoringSupportProvider : RefactoringSupportProvider() {
	override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
		return element is PsiNameIdentifierOwner
	}
}
