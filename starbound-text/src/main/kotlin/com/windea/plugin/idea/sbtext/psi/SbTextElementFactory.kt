package com.windea.plugin.idea.sbtext.psi

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.sbtext.*

object SbTextElementFactory {
	@JvmStatic
	fun createDummyFile(project: Project, text: String): SbTextFile {
		return PsiFileFactory.getInstance(project).createFileFromText(SbTextLanguage, text) as SbTextFile
	}
}

