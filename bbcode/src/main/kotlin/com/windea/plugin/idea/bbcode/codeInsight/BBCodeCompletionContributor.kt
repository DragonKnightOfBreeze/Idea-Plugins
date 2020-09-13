package com.windea.plugin.idea.bbcode.codeInsight

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.psi.util.*
import com.intellij.util.*
import com.windea.plugin.idea.bbcode.psi.*

class BBCodeCompletionContributor : CompletionContributor() {
	class TagSuffixCompletionProvider : CompletionProvider<CompletionParameters>() {
		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
			val tag = parameters.position.parentOfType<BBCodeTag>() ?: return
			val lookupElement = LookupElementBuilder.create("${tag.name}]")
			result.addElement(lookupElement)
		}
	}

	init {
		extend(
			CompletionType.BASIC,
			psiElement().afterSibling(psiElement(BBCodeTypes.TAG_SUFFIX_START)),
			TagSuffixCompletionProvider()
		)
	}

	override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
		super.fillCompletionVariants(parameters, result)
	}
}
