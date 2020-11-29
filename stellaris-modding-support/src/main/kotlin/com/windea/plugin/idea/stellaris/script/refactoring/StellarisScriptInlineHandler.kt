package com.windea.plugin.idea.stellaris.script.refactoring

import com.intellij.lang.refactoring.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptInlineHandler: InlineHandler {
	override fun createInliner(element: PsiElement, settings: InlineHandler.Settings): InlineHandler.Inliner? {
		return when{
			element is StellarisScriptVariable -> null
			element is StellarisScriptVariableReference -> null
			else -> null
		}
	}

	override fun removeDefinition(element: PsiElement, settings: InlineHandler.Settings) {

	}

	override fun prepareInlineElement(element: PsiElement, editor: Editor?, invokedOnReference: Boolean): InlineHandler.Settings? {
		return null
	}
}
