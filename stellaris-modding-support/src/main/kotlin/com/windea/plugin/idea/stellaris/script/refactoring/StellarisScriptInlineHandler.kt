package com.windea.plugin.idea.stellaris.script.refactoring

import com.intellij.lang.refactoring.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*

class StellarisScriptInlineHandler: InlineHandler {
	override fun createInliner(element: PsiElement, settings: InlineHandler.Settings): InlineHandler.Inliner? {
		return null
	}

	override fun removeDefinition(element: PsiElement, settings: InlineHandler.Settings) {

	}

	override fun prepareInlineElement(element: PsiElement, editor: Editor?, invokedOnReference: Boolean): InlineHandler.Settings? {
		return null
	}
}
