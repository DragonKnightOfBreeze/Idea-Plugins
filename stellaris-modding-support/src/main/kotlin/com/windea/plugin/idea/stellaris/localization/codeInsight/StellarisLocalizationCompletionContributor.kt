@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.stellaris.localization.codeInsight

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.openapi.roots.ui.configuration.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*

class StellarisLocalizationCompletionContributor : CompletionContributor() {
	class LocaleCompletionProvider : CompletionProvider<CompletionParameters>() {
		private val lookupElements = StellarisLocale.values.map{value->
			LookupElementBuilder.create(value.key).withTypeText(value.description)
		}

		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
			result.addAllElements(lookupElements)
		}
	}

	init {
		//当用户正在输入一个locale时提示
		extend(CompletionType.BASIC, psiElement().afterSibling(psiElement(LOCALE)), LocaleCompletionProvider())
	}

	override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
		super.fillCompletionVariants(parameters, result)
	}
}

