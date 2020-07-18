package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.ui.breadcrumbs.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*

@ExtensionPoint
class StellarisLocalizationBreadCrumbsProvider :BreadcrumbsProvider{
	companion object{
		val defaultLanguages:Array<Language> = arrayOf(StellarisLocalizationLanguage)
	}

	override fun getLanguages(): Array<Language> {
		return defaultLanguages
	}

	override fun getElementInfo(element: PsiElement): String {
		return when(element){
			is StellarisLocalizationProperty -> element.name
			else -> null
		} ?: anonymousElement
	}

	override fun acceptElement(element: PsiElement): Boolean {
		return element is StellarisLocalizationProperty
	}
}
