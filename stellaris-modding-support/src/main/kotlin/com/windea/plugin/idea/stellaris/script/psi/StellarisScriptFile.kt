package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.extapi.psi.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.script.*

class StellarisScriptFile(
	viewProvider: FileViewProvider
) : PsiFileBase(viewProvider, StellarisScriptLanguage) {
	override fun getFileType() = StellarisScriptFileType

	val rootBlock get() = findChildByClass(StellarisScriptRootBlock::class.java)
	
	val variables get() = rootBlock?.variableList?:listOf()

	val properties get() =  rootBlock?.propertyList?:listOf()

	val values get() = rootBlock?.valueList?:listOf()
}
