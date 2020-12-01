package com.windea.plugin.idea.pdx.localisation.editor

import com.intellij.lang.*
import com.intellij.lang.cacheBuilder.*
import com.intellij.lang.findUsages.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationFindUsagesProvider : FindUsagesProvider {
	override fun getDescriptiveName(element: PsiElement): String {
		return if(element is PsiNamedElement) "${element.name}" else ""
	}

	override fun getType(element: PsiElement): String {
		return when(element) {
			is PdxLocalisationProperty -> message("pdx.localisation.findUsages.property")
			is PdxLocalisationLocale -> message("pdx.localisation.findUsages.Locale")
			is PdxLocalisationIcon -> message("pdx.localisation.findUsages.icon")
			is PdxLocalisationColorfulText -> message("pdx.localisation.findUsages.color")
			is PdxLocalisationSerialNumber -> message("pdx.localisation.findUsages.serialNumber")
			else -> ""
		}
	}

	override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
		return getDescriptiveName(element)
	}

	override fun getHelpId(psiElement: PsiElement): String? {
		return HelpID.FIND_OTHER_USAGES
	}

	override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
		return psiElement is PsiNamedElement
	}

	override fun getWordsScanner(): WordsScanner? {
		//return  null
		return PdxLocalisationWordScanner()
	}
}
