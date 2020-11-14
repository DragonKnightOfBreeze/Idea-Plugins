package com.windea.plugin.idea.stellaris.script.editor

import com.intellij.lang.*
import com.intellij.lang.cacheBuilder.*
import com.intellij.lang.findUsages.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptFindUsagesProvider : FindUsagesProvider {
	override fun getDescriptiveName(element: PsiElement): String {
		return if(element is StellarisScriptNamedElement) "${element.name}" else ""
	}

	override fun getType(element: PsiElement): String {
		return when(element) {
			is StellarisScriptVariable -> StellarisBundle.message("stellaris.script.findUsages.variable")
			is StellarisScriptProperty -> StellarisBundle.message("stellaris.script.findUsages.property")
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
		return element is StellarisScriptNamedElement
	}

	override fun getWordsScanner(): WordsScanner? {
		return null
		//return StellarisScriptWordScanner()
	}
}
