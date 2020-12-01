package com.windea.plugin.idea.pdx.script.codeInsight

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.openapi.util.*
import com.intellij.patterns.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.psi.*
import com.intellij.psi.TokenType.*
import com.intellij.psi.util.*
import com.intellij.util.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.*
import com.windea.plugin.idea.pdx.localisation.psi.*
import com.windea.plugin.idea.pdx.script.psi.*
import com.windea.plugin.idea.pdx.script.psi.PdxScriptTypes.*

class PdxScriptCompletionContributor : CompletionContributor() {
	class BooleanCompletionProvider : CompletionProvider<CompletionParameters>() {
		private val lookupElements = booleanValues.map{value->
			LookupElementBuilder.create(value).bold().withPriority(80.0)
		}

		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
			result.addAllElements(lookupElements)
		}
	}

	init {
		//当用户正在输入一个string时提示
		extend(CompletionType.BASIC, psiElement(STRING_TOKEN), BooleanCompletionProvider())
	}
}
