package com.windea.plugin.idea.paradox.localisation.editor

import com.intellij.lang.*
import com.intellij.lang.cacheBuilder.*
import com.intellij.lang.findUsages.*
import com.intellij.psi.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.localisation.psi.*

class ParadoxLocalisationFindUsagesProvider : FindUsagesProvider {
	companion object{
		private val propertyName = message("paradox.localisation.name.property")
		private val localeName = message("paradox.localisation.name.locale")
		private val iconName = message("paradox.localisation.name.icon")
		private val serialNumberName = message("paradox.localisation.name.serialNumber")
		private val colorfulTextName = message("paradox.localisation.name.colorfulText")
	}
	
	override fun getDescriptiveName(element: PsiElement): String {
		return if(element is PsiNamedElement) element.name.orEmpty() else ""
	}

	override fun getType(element: PsiElement): String {
		return when(element) {
			is ParadoxLocalisationProperty -> propertyName
			is ParadoxLocalisationLocale -> localeName
			is ParadoxLocalisationIcon ->iconName
			is ParadoxLocalisationColorfulText -> serialNumberName
			is ParadoxLocalisationSerialNumber -> colorfulTextName
			else -> ""
		}
	}

	override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
		return getDescriptiveName(element)
	}

	override fun getHelpId(psiElement: PsiElement): String {
		return HelpID.FIND_OTHER_USAGES
	}

	override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
		return psiElement is PsiNamedElement
	}

	override fun getWordsScanner(): WordsScanner {
		return ParadoxLocalisationWordScanner()
	}
}
