package com.windea.plugin.idea.stellaris.localization.psi

import com.intellij.extapi.psi.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.localization.*

class StellarisLocalizationFile(
	viewProvider: FileViewProvider
) : PsiFileBase(viewProvider, StellarisLocalizationLanguage){
	override fun getFileType() = StellarisLocalizationFileType

	val locale: StellarisLocalizationLocale?
		get() = findChildByClass(StellarisLocalizationLocale::class.java)

	val properties: Array<StellarisLocalizationProperty>
		get() = findChildrenByClass(StellarisLocalizationProperty::class.java)
}
