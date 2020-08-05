package com.windea.plugin.idea.stellaris.script

import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.psi.*
import java.awt.*

@ExtensionPoint
class StellarisScriptColorProvider :ElementColorProvider{
	override fun getColorFrom(element: PsiElement): Color? {
		return when(element){
			is StellarisScriptColor -> element.color
			else -> null
		}
	}

	override fun setColorTo(element: PsiElement, color: Color) {
		when(element){
			is StellarisScriptColor -> element.setColor(color)
		}
	}
}
