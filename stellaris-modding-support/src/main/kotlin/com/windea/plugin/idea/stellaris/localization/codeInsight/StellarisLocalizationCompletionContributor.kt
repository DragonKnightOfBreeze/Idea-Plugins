package com.windea.plugin.idea.stellaris.localization.codeInsight

import com.intellij.codeInsight.completion.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.domain.*
import com.windea.plugin.idea.stellaris.localization.psi.*


@ExtensionPoint
class StellarisLocalizationCompletionContributor : CompletionContributor() {
	//companion object{
	//	val inLocale get() = psiElement()
	//		.inFile(psiFile(StellarisLocalizationFile::class.java))
	//		.afterLeaf("l_")
	//		.withParent(StellarisLocalizationFile::class.java)
	//}
	//
	//class LocaleCompletionProvider : CompletionProvider<CompletionParameters>() {
	//	override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
	//		for(locale in enumValues<StellarisLocale>()) {
	//			result.addElement(createLookupElement(locale.key,typeText = locale.description))
	//		}
	//	}
	//}
	//
	//init {
	//	extend(CompletionType.BASIC, inLocale, LocaleCompletionProvider())
	//}
}

