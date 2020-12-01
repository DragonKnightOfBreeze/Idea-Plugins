@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.pdx.localisation.formatter

import com.intellij.formatting.*
import com.intellij.psi.*
import com.intellij.psi.codeStyle.*
import com.windea.plugin.idea.pdx.localisation.formatter.*

class PdxLocalisationFormattingModelBuilder : FormattingModelBuilder {
	override fun createModel(element: PsiElement, settings: CodeStyleSettings): FormattingModel {
		return FormattingModelProvider.createFormattingModelForPsiFile(
				element.containingFile,
			PdxLocalisationBlock(element.node, settings),
				settings
			)
	}
}

