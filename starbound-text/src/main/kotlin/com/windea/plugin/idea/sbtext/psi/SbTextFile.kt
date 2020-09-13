package com.windea.plugin.idea.sbtext.psi

import com.intellij.extapi.psi.*
import com.intellij.openapi.fileTypes.*
import com.intellij.psi.*
import com.windea.plugin.idea.sbtext.*

class SbTextFile(
	viewProvider: FileViewProvider
): PsiFileBase(viewProvider, SbTextLanguage){
	override fun getFileType(): FileType {
		return SbTextFileType
	}
}
