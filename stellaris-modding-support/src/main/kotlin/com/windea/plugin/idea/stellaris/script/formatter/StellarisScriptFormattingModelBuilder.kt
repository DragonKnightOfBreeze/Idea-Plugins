package com.windea.plugin.idea.stellaris.script.formatter

import com.intellij.formatting.*
import com.intellij.psi.*
import com.intellij.psi.codeStyle.*

class StellarisScriptFormattingModelBuilder : FormattingModelBuilder {
	override fun createModel(element: PsiElement, settings: CodeStyleSettings): FormattingModel {
		return FormattingModelProvider.createFormattingModelForPsiFile(
			element.containingFile,
			StellarisScriptBlock(element.node, settings),
			settings
		)
	}
}
