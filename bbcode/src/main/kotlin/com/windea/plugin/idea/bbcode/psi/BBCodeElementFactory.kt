package com.windea.plugin.idea.bbcode.psi

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.bbcode.*

object BBCodeElementFactory {
	@JvmStatic
	fun createDummyFile(project: Project, text: String): BBCodeFile {
		return PsiFileFactory.getInstance(project).createFileFromText(BBCodeLanguage, text) as BBCodeFile
	}
}

