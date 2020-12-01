package com.windea.plugin.idea.pdx.script.editor

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.ui.breadcrumbs.*
import com.windea.plugin.idea.pdx.script.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptBreadCrumbsProvider : BreadcrumbsProvider {
	companion object{
		val defaultLanguages: Array<Language> = arrayOf(PdxScriptLanguage)
	}

	override fun getLanguages(): Array<Language> {
		return defaultLanguages
	}

	override fun getElementInfo(element: PsiElement): String {
		return when(element){
			is PdxScriptVariable -> element.name
			is PdxScriptProperty -> element.name
			is PdxScriptBoolean -> element.value
			is PdxScriptNumber -> element.value
			is PdxScriptString -> element.value
			else -> "<anonymous element>"
		} ?: "<anonymous element>"
	}

	override fun acceptElement(element: PsiElement): Boolean {
		return element is PdxScriptVariable
		       || element is PdxScriptProperty
		       || element is PdxScriptBoolean
		       || element is PdxScriptNumber
		       || element is PdxScriptString
	}
}
