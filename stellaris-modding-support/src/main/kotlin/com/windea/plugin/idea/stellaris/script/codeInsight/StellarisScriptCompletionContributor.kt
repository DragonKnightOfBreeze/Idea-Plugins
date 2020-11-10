package com.windea.plugin.idea.stellaris.script.codeInsight

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.openapi.util.*
import com.intellij.patterns.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.psi.*
import com.intellij.psi.TokenType.*
import com.intellij.psi.util.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

//pattern是通过调试确定的

class StellarisScriptCompletionContributor : CompletionContributor() {
	class BooleanCompletionProvider : CompletionProvider<CompletionParameters>() {
		private val values = arrayOf("yes", "no")

		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
			//当用户正在输入一个string时提示
			val position = parameters.position
			val resultWithPrefix = if(position is PsiWhiteSpace) result.withPrefixMatcher(position.prevLeaf()?.text.orEmpty()) else result
			for(value in values) {
				val lookupElement = LookupElementBuilder.create(value).bold().withPriority(80.0)
				resultWithPrefix.addElement(lookupElement)
			}
		}
	}

	init {
		extend(
			CompletionType.BASIC,
			or(
				psiElement(STRING_TOKEN),
				psiElement().whitespace().afterLeaf(psiElement(STRING_TOKEN))
			),
			BooleanCompletionProvider()
		)
	}

	override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
		super.fillCompletionVariants(parameters, result)
	}
}
