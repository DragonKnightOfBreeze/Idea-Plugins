package com.windea.plugin.idea.paradox.script.editor

import com.intellij.lang.*
import com.intellij.lang.cacheBuilder.*
import com.intellij.lang.findUsages.*
import com.intellij.psi.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.script.psi.*

class ParadoxScriptFindUsagesProvider : FindUsagesProvider {
	override fun getDescriptiveName(element: PsiElement): String {
		return if(element is ParadoxScriptNamedElement) "${element.name}" else ""
	}

	override fun getType(element: PsiElement): String {
		return when(element) {
			is ParadoxScriptVariable -> message("paradox.script.findUsages.variable")
			is ParadoxScriptProperty -> message("paradox.script.findUsages.property")
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
		return element is ParadoxScriptNamedElement
	}

	override fun getWordsScanner(): WordsScanner? {
		return null
		//return ParadoxScriptWordScanner()
	}
}
