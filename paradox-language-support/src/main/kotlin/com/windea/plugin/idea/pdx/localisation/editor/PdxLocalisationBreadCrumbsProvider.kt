package com.windea.plugin.idea.pdx.localisation.editor

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.ui.breadcrumbs.*
import com.windea.plugin.idea.pdx.localisation.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationBreadCrumbsProvider : BreadcrumbsProvider {
	companion object{
		val defaultLanguages:Array<Language> = arrayOf(PdxLocalisationLanguage)
	}

	override fun getLanguages(): Array<Language> {
		return defaultLanguages
	}

	override fun getElementInfo(element: PsiElement): String {
		return when(element){
			is PdxLocalisationProperty -> element.name
			else -> null
		} ?: "<anonymous element>"
	}

	override fun acceptElement(element: PsiElement): Boolean {
		return element is PdxLocalisationProperty
	}
}
