package com.windea.plugin.idea.pdx.localisation.editor

import com.intellij.codeInsight.template.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationTemplateContext : TemplateContextType(pdxLocalisationNameSsc, pdxLocalisationName) {
	override fun isInContext(file: PsiFile, offset: Int): Boolean {
		val element = file.findElementAt(offset)
		return element?.parentOfType<PdxLocalisationPropertyValue>(true) != null
	}
}
