package com.windea.plugin.idea.stellaris.script

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.ui.breadcrumbs.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.psi.*

@ExtensionPoint
class StellarisScriptBreadCrumbsProvider :BreadcrumbsProvider{
	companion object{
		val defaultLanguages: Array<Language> = arrayOf(StellarisScriptLanguage)
	}

	override fun getLanguages(): Array<Language> {
		return defaultLanguages
	}

	override fun getElementInfo(element: PsiElement): String {
		return when(element){
			is StellarisScriptProperty -> element.name
			is StellarisScriptVariableDefinition -> element.name
			is StellarisScriptString -> element.value
			else -> breadCrumbsElement
		} ?: anonymousElement
	}

	override fun acceptElement(element: PsiElement): Boolean {
		return element is StellarisScriptProperty || element is StellarisScriptVariableName || element is StellarisScriptString
	}
}
