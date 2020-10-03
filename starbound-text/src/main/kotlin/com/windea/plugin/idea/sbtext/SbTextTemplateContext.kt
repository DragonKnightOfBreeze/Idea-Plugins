package com.windea.plugin.idea.sbtext

import com.intellij.codeInsight.template.*
import com.intellij.psi.*
import com.windea.plugin.idea.sbtext.psi.*

class SbTextTemplateContext : TemplateContextType(sbTextNameSsc,sbTextName){
	override fun isInContext(file: PsiFile, offset: Int): Boolean {
		val element = file.findElementAt(offset)?:return false
		val parent = element.parent?:return false
		return parent is PsiFile || parent is SbTextString
	}
}
