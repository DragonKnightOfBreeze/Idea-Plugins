package com.windea.plugin.idea.stellaris.localization.psi

import com.intellij.extapi.psi.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.localization.*

class StellarisLocalizationFile(
	viewProvider: FileViewProvider
) : PsiFileBase(viewProvider, StellarisLocalizationLanguage){
	override fun getFileType() = StellarisLocalizationFileType

	val Locale get() = findChildByClass(StellarisLocalizationLocale::class.java)
	val properties get() = findChildrenByClass(StellarisLocalizationProperty::class.java)
}
