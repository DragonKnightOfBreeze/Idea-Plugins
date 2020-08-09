@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.stellaris.localization.formatter

import com.intellij.formatting.*
import com.intellij.psi.*
import com.intellij.psi.codeStyle.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.formatter.*

class StellarisLocalizationFormattingModelBuilder : FormattingModelBuilder {
	override fun createModel(element: PsiElement, settings: CodeStyleSettings): FormattingModel {
		return FormattingModelProvider.createFormattingModelForPsiFile(
				element.containingFile,
			StellarisLocalizationBlock(element.node, settings),
				settings
			)
	}
}

