package com.windea.plugin.idea.stellaris.script

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.ui.breadcrumbs.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptBreadCrumbsProvider :BreadcrumbsProvider{
	companion object{
		val defaultLanguages: Array<Language> = arrayOf(StellarisScriptLanguage)
	}

	override fun getLanguages(): Array<Language> {
		return defaultLanguages
	}

	override fun getElementInfo(element: PsiElement): String {
		return when(element){
			is StellarisScriptVariableDefinition -> element.name
			is StellarisScriptProperty -> element.name
			is StellarisScriptBoolean -> element.value
			is StellarisScriptNumber -> element.value
			is StellarisScriptString -> element.value
			else -> anonymousElement
		} ?: anonymousElement
	}

	override fun acceptElement(element: PsiElement): Boolean {
		return element is StellarisScriptVariableDefinition
		       || element is StellarisScriptProperty
		       || element is StellarisScriptBoolean
		       || element is StellarisScriptNumber
		       || element is StellarisScriptString
	}
}
