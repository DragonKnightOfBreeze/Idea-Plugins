package com.windea.plugin.idea.stellaris.localization

import com.intellij.codeInsight.template.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*

//模版上下文：
//本地化文件的属性的值

@ExtensionPoint
class StellarisLocalizationTemplateContext : TemplateContextType(stellarisLocalizationNameSsc, stellarisLocalizationName) {
	override fun isInContext(file: PsiFile, offset: Int): Boolean {
		val element = file.findElementAt(offset)
		return element.elementType == StellarisLocalizationTypes.RICH_TEXT
	}
}

