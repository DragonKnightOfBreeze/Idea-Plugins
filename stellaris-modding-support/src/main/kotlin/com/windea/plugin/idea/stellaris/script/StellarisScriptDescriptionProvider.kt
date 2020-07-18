package com.windea.plugin.idea.stellaris.script

import com.intellij.psi.*
import com.intellij.usageView.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.psi.*

@ExtensionPoint
class StellarisScriptDescriptionProvider: ElementDescriptionProvider {
	override fun getElementDescription(element: PsiElement, location: ElementDescriptionLocation): String? {
		return when(element) {
			is StellarisScriptVariableDefinition ->{
				if(location == UsageViewTypeLocation.INSTANCE) StellarisBundle.message("stellaris.script.description.variable")
				else element.name
			}
			is StellarisScriptVariableReference -> {
				if(location == UsageViewTypeLocation.INSTANCE) StellarisBundle.message("stellaris.script.description.variable")
				else element.name
			}
			is StellarisScriptProperty ->{
				if(location == UsageViewTypeLocation.INSTANCE) StellarisBundle.message("stellaris.script.description.property")
				else element.name
			}
			else -> null
		}
	}
}
