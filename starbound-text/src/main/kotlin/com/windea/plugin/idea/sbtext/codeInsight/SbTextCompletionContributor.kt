@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.sbtext.codeInsight

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.util.*
import com.windea.plugin.idea.sbtext.*

//在"^"后面：常用的颜色名，reset

class SbTextCompletionContributor : CompletionContributor() {
	class ColorCodeCompletionProvider : CompletionProvider<CompletionParameters>() {
		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
			for(color in StarboundColor.values) {
				result.addElement(LookupElementBuilder.create(color.key).withIcon(color.icon))
			}
			result.addElement(LookupElementBuilder.create("reset"))
		}
	}

	init {
		extend(
			CompletionType.BASIC,
			psiElement().afterLeaf("^"),
			ColorCodeCompletionProvider()
		)
	}

	override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
		super.fillCompletionVariants(parameters, result)
	}
}

