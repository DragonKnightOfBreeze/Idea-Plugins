package com.windea.plugin.idea.pdx.script.editor

import com.intellij.psi.*
import com.intellij.usageView.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptDescriptionProvider: ElementDescriptionProvider {
	override fun getElementDescription(element: PsiElement, location: ElementDescriptionLocation): String? {
		return when(element) {
			is PdxScriptVariable ->{
				if(location == UsageViewTypeLocation.INSTANCE) message("pdx.script.description.variable")
				else element.name
			}
			is PdxScriptProperty ->{
				if(location == UsageViewTypeLocation.INSTANCE) message("pdx.script.description.property")
				else element.name
			}
			else -> null
		}
	}
}
