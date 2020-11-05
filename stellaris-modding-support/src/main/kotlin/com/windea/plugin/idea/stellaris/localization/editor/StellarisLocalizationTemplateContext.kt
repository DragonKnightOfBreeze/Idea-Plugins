package com.windea.plugin.idea.stellaris.localization.editor

import com.intellij.codeInsight.template.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationTemplateContext : TemplateContextType(stellarisLocalizationNameSsc, stellarisLocalizationName) {
	override fun isInContext(file: PsiFile, offset: Int): Boolean {
		val element = file.findElementAt(offset)
		return element?.parentOfType<StellarisLocalizationPropertyValue>(true) != null
	}
}
