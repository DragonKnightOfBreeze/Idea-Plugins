package com.windea.plugin.idea.stellaris.localization

import com.intellij.psi.*
import com.intellij.usageView.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationDescriptionProvider : ElementDescriptionProvider {
	override fun getElementDescription(element: PsiElement, location: ElementDescriptionLocation): String? {
		return when(element) {
			is StellarisLocalizationLocale -> {
				if(location == UsageViewTypeLocation.INSTANCE) message("stellaris.localization.description.Locale")
				else element.name
			}
			is StellarisLocalizationProperty -> {
				if(location == UsageViewTypeLocation.INSTANCE) message("stellaris.localization.description.property")
				else element.name
			}
			is StellarisLocalizationIcon -> {
				if(location == UsageViewTypeLocation.INSTANCE) message("stellaris.localization.description.icon")
				else element.name
			}
			is StellarisLocalizationColorfulText -> {
				if(location == UsageViewTypeLocation.INSTANCE)  message("stellaris.localization.description.color")
				else element.name
			}
			is StellarisLocalizationSerialNumber -> {
				if(location == UsageViewTypeLocation.INSTANCE)  message("stellaris.localization.description.serialNumber")
				else element.name
			}
			else -> null
		}
	}
}
