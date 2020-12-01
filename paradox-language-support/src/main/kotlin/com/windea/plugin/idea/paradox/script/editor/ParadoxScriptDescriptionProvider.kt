package com.windea.plugin.idea.paradox.script.editor

import com.intellij.psi.*
import com.intellij.usageView.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.script.psi.*

class ParadoxScriptDescriptionProvider: ElementDescriptionProvider {
	override fun getElementDescription(element: PsiElement, location: ElementDescriptionLocation): String? {
		return when(element) {
			is ParadoxScriptVariable ->{
				if(location == UsageViewTypeLocation.INSTANCE) message("paradox.script.description.variable")
				else element.name
			}
			is ParadoxScriptProperty ->{
				if(location == UsageViewTypeLocation.INSTANCE) message("paradox.script.description.property")
				else element.name
			}
			else -> null
		}
	}
}
