package com.windea.plugin.idea.pdx.script.refactoring

import com.intellij.lang.refactoring.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptInlineHandler: InlineHandler {
	override fun createInliner(element: PsiElement, settings: InlineHandler.Settings): InlineHandler.Inliner? {
		return when{
			element is PdxScriptVariable -> null
			element is PdxScriptVariableReference -> null
			else -> null
		}
	}

	override fun removeDefinition(element: PsiElement, settings: InlineHandler.Settings) {

	}

	override fun prepareInlineElement(element: PsiElement, editor: Editor?, invokedOnReference: Boolean): InlineHandler.Settings? {
		return null
	}
}
