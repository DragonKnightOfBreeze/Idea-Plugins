package com.windea.plugin.idea.stellaris.localization.codeInsight

import com.intellij.codeInsight.completion.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.domain.*
import com.windea.plugin.idea.stellaris.localization.psi.*

//代码补全：
//"l_" -> "l_xxx"

@ExtensionPoint
class StellarisLocalizationCompletionContributor : CompletionContributor() {
	companion object{
		val inPropertyHeader get() = psiElement()
			.inFile(psiFile(StellarisLocalizationFile::class.java))
			.afterLeaf("l_")
	}

	class PropertyHeaderCompletionProvider : CompletionProvider<CompletionParameters>() {
		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
			for(locale in enumValues<StellarisLocalizationLocale>()) {
				result.addElement(createLookupElement(locale.text,typeText = locale.description))
			}
		}
	}

	init {
		extend(CompletionType.BASIC, inPropertyHeader, PropertyHeaderCompletionProvider())
	}
}

