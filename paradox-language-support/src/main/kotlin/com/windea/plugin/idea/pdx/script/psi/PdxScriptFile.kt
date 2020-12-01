package com.windea.plugin.idea.pdx.script.psi

import com.intellij.extapi.psi.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.script.*

class PdxScriptFile(
	viewProvider: FileViewProvider
) : PsiFileBase(viewProvider, PdxScriptLanguage) {
	override fun getFileType() = PdxScriptFileType

	val rootBlock get() = findChildByClass(PdxScriptRootBlock::class.java)
	
	val variables get() = rootBlock?.variableList?:listOf()

	val properties get() =  rootBlock?.propertyList?:listOf()

	val values get() = rootBlock?.valueList?:listOf()
}
