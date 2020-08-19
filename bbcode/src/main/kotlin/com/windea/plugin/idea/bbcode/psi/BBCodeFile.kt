package com.windea.plugin.idea.bbcode.psi

import com.intellij.extapi.psi.*
import com.intellij.psi.*
import com.windea.plugin.idea.bbcode.*

class BBCodeFile(
	viewProvider: FileViewProvider
): PsiFileBase(viewProvider, BBCodeLanguage){
	override fun getFileType() = BBCodeFileType
}

