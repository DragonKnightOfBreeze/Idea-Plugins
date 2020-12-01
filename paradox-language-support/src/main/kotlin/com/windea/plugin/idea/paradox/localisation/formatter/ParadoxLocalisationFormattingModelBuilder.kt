@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.paradox.localisation.formatter

import com.intellij.formatting.*
import com.intellij.psi.*
import com.intellij.psi.codeStyle.*
import com.windea.plugin.idea.paradox.localisation.formatter.*

class ParadoxLocalisationFormattingModelBuilder : FormattingModelBuilder {
	override fun createModel(element: PsiElement, settings: CodeStyleSettings): FormattingModel {
		return FormattingModelProvider.createFormattingModelForPsiFile(
				element.containingFile,
			ParadoxLocalisationBlock(element.node, settings),
				settings
			)
	}
}

