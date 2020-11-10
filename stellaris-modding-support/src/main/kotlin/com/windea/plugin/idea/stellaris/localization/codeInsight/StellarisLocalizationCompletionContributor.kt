@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.stellaris.localization.codeInsight

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*

//pattern是通过调试确定的

class StellarisLocalizationCompletionContributor : CompletionContributor() {
	class LocaleCompletionProvider : CompletionProvider<CompletionParameters>() {
		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
			//当用户正在输入一个locale时提示
			//当在文档开始（忽略注释）处输入时，认为正在输入语言区域，因此需要进行代码提示
			//由于用户已经输入了一个字符，因此不能直接补全，需要添加一个前缀，一般是"l"
			val prefix = parameters.position.prevSibling?.text
			val resultWithPrefix = if(prefix == null) result else result.withPrefixMatcher(prefix)
			for(locale in StellarisLocale.values()) {
				val lookupElement = LookupElementBuilder.create(locale.key).withTypeText(locale.description)
				resultWithPrefix.addElement(lookupElement)
			}
		}
	}

	init {
		extend(
			CompletionType.BASIC,
			psiElement().afterSibling(psiElement(LOCALE)),
			LocaleCompletionProvider()
		)
	}

	override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
		super.fillCompletionVariants(parameters, result)
	}
}

