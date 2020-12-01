package com.windea.plugin.idea.pdx.script.formatter

import com.intellij.formatting.*
import com.intellij.psi.*
import com.intellij.psi.codeStyle.*

class PdxScriptFormattingModelBuilder : FormattingModelBuilder {
	override fun createModel(element: PsiElement, settings: CodeStyleSettings): FormattingModel {
		return FormattingModelProvider.createFormattingModelForPsiFile(
			element.containingFile,
			PdxScriptBlock(element.node, settings),
			settings
		)
	}
}
