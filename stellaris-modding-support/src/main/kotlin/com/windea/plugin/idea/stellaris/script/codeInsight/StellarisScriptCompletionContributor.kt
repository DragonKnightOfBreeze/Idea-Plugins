package com.windea.plugin.idea.stellaris.script.codeInsight

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

//pattern是通过调试确定的

@ExtensionPoint
class StellarisScriptCompletionContributor : CompletionContributor() {
	class BooleanCompletionProvider : CompletionProvider<CompletionParameters>() {
		private val values = arrayOf("yes", "no")

		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
			//基础的代码提示,仅提示布尔值
			//条件语句关键字，布尔表达式关键字，如if, else_if, AND, OR，认为只有在特定的作用域内才需提示
			for(value in values) {
				val lookupElement = LookupElementBuilder.create(value).bold().withPriority(80.0)
				result.addElement(lookupElement)
			}
		}
	}

	init {
		extend(
			CompletionType.BASIC,
			//psiElement().afterSiblingSkipping(psiElement().whitespace(),psiElement(PROPERTY_SEPARATOR)),
			psiElement(UNQUOTED_STRING_TOKEN),
			BooleanCompletionProvider()
		)
	}

	override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
		super.fillCompletionVariants(parameters, result)
	}
}
