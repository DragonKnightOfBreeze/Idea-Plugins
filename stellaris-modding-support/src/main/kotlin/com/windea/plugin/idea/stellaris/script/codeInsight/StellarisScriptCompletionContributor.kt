package com.windea.plugin.idea.stellaris.script.codeInsight

import com.intellij.codeInsight.completion.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.psi.*

//注意这里一旦有问题可能会导致Editor卡顿！！！

@ExtensionPoint
class StellarisScriptCompletionContributor : CompletionContributor() {
	companion object {
		//等号之后，不能在变量定义内
		private val afterEqualSignInProperty
			get() = psiElement()
				.inFile(psiFile(StellarisScriptFile::class.java))
				.afterSibling(psiElement(StellarisScriptTypes.PROPERTY_SEPARATOR))

		//列表之内
		private val inList
			get() = psiElement()
				.inFile(psiFile(StellarisScriptFile::class.java))
				.withParent(StellarisScriptList::class.java)

		//limit之内
		private val inLimitList
			get() = psiElement()
				.inFile(psiFile(StellarisScriptFile::class.java))
				.withParent(StellarisScriptList::class.java)
				.withSuperParent(3, psiElement(StellarisScriptProperty::class.java).withName("limit"))
	}

	class BooleanCompletionProvider : CompletionProvider<CompletionParameters>() {
		private val values = arrayOf("yes", "no")

		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
			for(value in values) result.addElement(createKeywordLookupElement(value))
		}
	}

	class ExpressionCompletionProvider : CompletionProvider<CompletionParameters>() {
		private val values = arrayOf("if", "else_if", "else", "where", "limit")

		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
			for(value in values) result.addElement(createKeywordLookupElement(value))
		}
	}

	class ConditionCompletionProvider : CompletionProvider<CompletionParameters>() {
		private val values = arrayOf("AND", "OR", "NOT", "NOR")

		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
			for(value in values) result.addElement(createKeywordLookupElement(value))
		}
	}

	init {
		extend(CompletionType.BASIC, afterEqualSignInProperty, BooleanCompletionProvider())
		//extend(CompletionType.BASIC, inList, ExpressionCompletionProvider())
		//extend(CompletionType.BASIC, inLimitList, ConditionCompletionProvider())
	}
}
