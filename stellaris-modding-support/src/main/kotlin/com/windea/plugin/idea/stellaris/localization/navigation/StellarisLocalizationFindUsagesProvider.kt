package com.windea.plugin.idea.stellaris.localization.navigation

import com.intellij.lang.*
import com.intellij.lang.cacheBuilder.*
import com.intellij.lang.findUsages.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.psi.*

//查找使用：
//key: value - 键，注释，字符串
//TODO key: value - 属性引用

@ExtensionPoint
class StellarisLocalizationFindUsagesProvider : FindUsagesProvider {
	override fun getDescriptiveName(element: PsiElement): String {
		return when(element) {
			is StellarisLocalizationProperty -> "${element.name}"
			else -> ""
		}
	}

	override fun getType(element: PsiElement): String {
		return when(element) {
			is StellarisLocalizationProperty -> message("stellaris.localization.findUsages.property")
			//is StellarisLocalizationLocale -> message("stellaris.localization.findUsages.Locale")
			//is StellarisLocalizationIcon -> message("stellaris.localization.findUsages.icon")
			//is StellarisLocalizationColorfulText -> message("stellaris.localization.findUsages.color")
			//is StellarisLocalizationSerialNumber -> message("stellaris.localization.findUsages.serialNumber")
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

