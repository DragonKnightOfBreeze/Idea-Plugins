package com.windea.plugin.idea.paradox.localisation.editor

import com.intellij.lang.*
import com.intellij.lang.cacheBuilder.*
import com.intellij.lang.findUsages.*
import com.intellij.psi.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.localisation.psi.*

class ParadoxLocalisationFindUsagesProvider : FindUsagesProvider {
	override fun getDescriptiveName(element: PsiElement): String {
		return if(element is PsiNamedElement) "${element.name}" else ""
	}

	override fun getType(element: PsiElement): String {
		return when(element) {
			is ParadoxLocalisationProperty -> message("paradox.localisation.findUsages.property")
			is ParadoxLocalisationLocale -> message("paradox.localisation.findUsages.Locale")
			is ParadoxLocalisationIcon -> message("paradox.localisation.findUsages.icon")
			is ParadoxLocalisationColorfulText -> message("paradox.localisation.findUsages.color")
			is ParadoxLocalisationSerialNumber -> message("paradox.localisation.findUsages.serialNumber")
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
		return ParadoxLocalisationWordScanner()
	}
}
