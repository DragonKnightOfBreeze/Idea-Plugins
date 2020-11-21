package com.windea.plugin.idea.stellaris.localization.editor

import com.intellij.lang.*
import com.intellij.lang.cacheBuilder.*
import com.intellij.lang.findUsages.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationFindUsagesProvider : FindUsagesProvider {
	override fun getDescriptiveName(element: PsiElement): String {
		return if(element is PsiNamedElement) "${element.name}" else ""
	}

	override fun getType(element: PsiElement): String {
		return when(element) {
			is StellarisLocalizationProperty -> message("stellaris.localization.findUsages.property")
			is StellarisLocalizationLocale -> message("stellaris.localization.findUsages.Locale")
			is StellarisLocalizationIcon -> message("stellaris.localization.findUsages.icon")
			is StellarisLocalizationColorfulText -> message("stellaris.localization.findUsages.color")
			is StellarisLocalizationSerialNumber -> message("stellaris.localization.findUsages.serialNumber")
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
		return StellarisLocalizationWordScanner()
	}
}
