package com.windea.plugin.idea.stellaris.localization.psi

import com.intellij.extapi.psi.*
import com.intellij.psi.*
import com.intellij.psi.stubs.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.*

class StellarisLocalizationFile(
	viewProvider: FileViewProvider
) : PsiFileBase(viewProvider, StellarisLocalizationLanguage){
	override fun getFileType() = StellarisLocalizationFileType

	val locale: StellarisLocalizationLocale?
		get() = findChildByClass(StellarisLocalizationLocale::class.java)
	
	val properties: List<StellarisLocalizationProperty>
		get() = findChildrenByClass(StellarisLocalizationProperty::class.java).toList()
	
	val stellarisLocale: StellarisLocale?
		get() = locale?.stellarisLocale
}
