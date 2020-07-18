package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.extapi.psi.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.script.*

class StellarisScriptFile(
	viewProvider: FileViewProvider
) : PsiFileBase(viewProvider, StellarisScriptLanguage) {
	override fun getFileType() = StellarisScriptFileType

	val variableDefinitions get() = findChildrenByClass(StellarisScriptVariableDefinition::class.java)

	val properties get() = findChildrenByClass(StellarisScriptProperty::class.java)
}
