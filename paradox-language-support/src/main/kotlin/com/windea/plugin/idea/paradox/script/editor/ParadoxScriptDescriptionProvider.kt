package com.windea.plugin.idea.paradox.script.editor

import com.intellij.psi.*
import com.intellij.usageView.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.script.psi.*

class ParadoxScriptDescriptionProvider : ElementDescriptionProvider {
	companion object {
		private val variableDescription = message("paradox.script.description.variable")
		private val propertyDescription = message("paradox.script.description.property")
	}
	
	override fun getElementDescription(element: PsiElement, location: ElementDescriptionLocation): String? {
		return when(element) {
			is ParadoxScriptVariable -> if(location == UsageViewTypeLocation.INSTANCE) variableDescription else element.name
			is ParadoxScriptProperty -> if(location == UsageViewTypeLocation.INSTANCE) propertyDescription else element.name
			else -> null
		}
	}
}
