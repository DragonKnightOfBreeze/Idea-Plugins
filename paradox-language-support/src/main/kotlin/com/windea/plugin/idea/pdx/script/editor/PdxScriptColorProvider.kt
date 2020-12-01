package com.windea.plugin.idea.pdx.script.editor

import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.script.psi.*
import java.awt.*

class PdxScriptColorProvider : ElementColorProvider {
	override fun getColorFrom(element: PsiElement): Color? {
		return when(element){
			is PdxScriptColor -> element.color
			else -> null
		}
	}

	override fun setColorTo(element: PsiElement, color: Color) {
		when(element){
			is PdxScriptColor -> element.setColor(color)
		}
	}
}
