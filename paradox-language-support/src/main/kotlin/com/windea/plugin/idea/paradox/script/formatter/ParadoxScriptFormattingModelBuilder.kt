package com.windea.plugin.idea.paradox.script.formatter

import com.intellij.formatting.*
import com.intellij.psi.*
import com.intellij.psi.codeStyle.*

class ParadoxScriptFormattingModelBuilder : FormattingModelBuilder {
	override fun createModel(element: PsiElement, settings: CodeStyleSettings): FormattingModel {
		return FormattingModelProvider.createFormattingModelForPsiFile(
			element.containingFile,
			ParadoxScriptBlock(element.node, settings),
			settings
		)
	}
}
