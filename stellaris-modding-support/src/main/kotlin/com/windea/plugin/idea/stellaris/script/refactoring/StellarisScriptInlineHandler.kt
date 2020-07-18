package com.windea.plugin.idea.stellaris.script.refactoring

import com.intellij.lang.refactoring.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.annotations.*

@ExtensionPoint
class StellarisScriptInlineHandler: InlineHandler {
	override fun removeDefinition(element: PsiElement, settings: InlineHandler.Settings) {
		TODO()
	}

	override fun prepareInlineElement(element: PsiElement, editor: Editor?, invokedOnReference: Boolean): InlineHandler.Settings? {
		TODO()
	}

	override fun createInliner(element: PsiElement, settings: InlineHandler.Settings): InlineHandler.Inliner? {
		TODO()
	}
}
