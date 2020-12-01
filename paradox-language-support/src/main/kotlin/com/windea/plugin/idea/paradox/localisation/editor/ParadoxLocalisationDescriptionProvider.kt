package com.windea.plugin.idea.paradox.localisation.editor

import com.intellij.psi.*
import com.intellij.usageView.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.localisation.psi.*

class ParadoxLocalisationDescriptionProvider : ElementDescriptionProvider {
	override fun getElementDescription(element: PsiElement, location: ElementDescriptionLocation): String? {
		return when(element) {
			is ParadoxLocalisationLocale -> {
				if(location == UsageViewTypeLocation.INSTANCE) message("paradox.localisation.description.Locale")
				else element.name
			}
			is ParadoxLocalisationProperty -> {
				if(location == UsageViewTypeLocation.INSTANCE) message("paradox.localisation.description.property")
				else element.name
			}
			is ParadoxLocalisationIcon -> {
				if(location == UsageViewTypeLocation.INSTANCE) message("paradox.localisation.description.icon")
				else element.name
			}
			is ParadoxLocalisationColorfulText -> {
				if(location == UsageViewTypeLocation.INSTANCE) message("paradox.localisation.description.color")
				else element.name
			}
			is ParadoxLocalisationSerialNumber -> {
				if(location == UsageViewTypeLocation.INSTANCE) message("paradox.localisation.description.serialNumber")
				else element.name
			}
			else -> null
		}
	}
}
