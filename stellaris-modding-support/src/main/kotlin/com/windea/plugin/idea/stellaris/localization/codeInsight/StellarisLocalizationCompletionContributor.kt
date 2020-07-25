@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.stellaris.localization.codeInsight

import com.intellij.codeInsight.completion.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.domain.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*

//pattern是通过调试确定的

@ExtensionPoint
class StellarisLocalizationCompletionContributor : CompletionContributor() {
	class LocaleCompletionProvider : CompletionProvider<CompletionParameters>() {
		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
			for(locale in StellarisLocale.values()) {
				result.addElement(createLookupElement(locale.key,typeText = locale.description))
			}
		}
	}

	//虽然不知道为什么，但是还是一个一个试吧……

	init {
		extend(
			CompletionType.BASIC,
			//psiElement().withParent(psiElement(PsiErrorElement::class.java).withParent(PsiFile::class.java)),
			//psiElement().afterSibling(psiElement(LOCALE)),
			psiElement(LOCALE),
			LocaleCompletionProvider()
		)
	}

	//将光标向左移一位
	override fun beforeCompletion(context: CompletionInitializationContext) {
		context.replacementOffset
	}

	override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
		super.fillCompletionVariants(parameters, result)
	}
}

