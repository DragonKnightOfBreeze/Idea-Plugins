package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.openapi.progress.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationRecursiveVisitor: StellarisLocalizationVisitor(),PsiRecursiveVisitor{
	override fun visitPsiElement(element: PsiElement) {
		ProgressManager.checkCanceled()
		element.acceptChildren(this)
	}
}
