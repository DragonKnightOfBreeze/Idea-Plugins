package com.windea.plugin.idea.pdx.localisation.psi

import com.intellij.extapi.psi.*
import com.intellij.psi.*
import com.intellij.psi.stubs.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.*

class PdxLocalisationFile(
	viewProvider: FileViewProvider
) : PsiFileBase(viewProvider, PdxLocalisationLanguage){
	override fun getFileType() = PdxLocalisationFileType

	val locale: PdxLocalisationLocale?
		get() = findChildByClass(PdxLocalisationLocale::class.java)
	
	val properties: List<PdxLocalisationProperty>
		get() = findChildrenByClass(PdxLocalisationProperty::class.java).toList()
	
	val pdxLocale: PdxLocale?
		get() = locale?.pdxLocale
}
