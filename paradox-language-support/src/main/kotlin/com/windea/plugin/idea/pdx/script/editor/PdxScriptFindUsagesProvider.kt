package com.windea.plugin.idea.pdx.script.editor

import com.intellij.lang.*
import com.intellij.lang.cacheBuilder.*
import com.intellij.lang.findUsages.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptFindUsagesProvider : FindUsagesProvider {
	override fun getDescriptiveName(element: PsiElement): String {
		return if(element is PdxScriptNamedElement) "${element.name}" else ""
	}

	override fun getType(element: PsiElement): String {
		return when(element) {
			is PdxScriptVariable -> message("pdx.script.findUsages.variable")
			is PdxScriptProperty -> message("pdx.script.findUsages.property")
			else -> ""
		}
	}

	override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
		return getDescriptiveName(element)
	}

	override fun getHelpId(psiElement: PsiElement): String? {
		return HelpID.FIND_OTHER_USAGES
	}

	override fun canFindUsagesFor(element: PsiElement): Boolean {
		return element is PdxScriptNamedElement
	}

	override fun getWordsScanner(): WordsScanner? {
		return null
		//return PdxScriptWordScanner()
	}
}
