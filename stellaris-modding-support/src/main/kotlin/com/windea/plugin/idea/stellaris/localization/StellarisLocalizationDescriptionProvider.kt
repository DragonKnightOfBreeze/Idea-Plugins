package com.windea.plugin.idea.stellaris.localization

import com.intellij.psi.*
import com.intellij.usageView.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*

@ExtensionPoint
class StellarisLocalizationDescriptionProvider : ElementDescriptionProvider {
	override fun getElementDescription(element: PsiElement, location: ElementDescriptionLocation): String? {
		return when(element) {
			is StellarisLocalizationPropertyHeader ->{
				if(location == UsageViewTypeLocation.INSTANCE) message("stellaris.localization.description.propertyHeader")
				else element.name
			}
			is StellarisLocalizationProperty -> {
				if(location == UsageViewTypeLocation.INSTANCE)  message("stellaris.localization.description.property")
				else element.name
			}
			else -> null
		}
	}
}
