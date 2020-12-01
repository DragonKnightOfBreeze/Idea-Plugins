package com.windea.plugin.idea.pdx.localisation.editor

import com.intellij.psi.*
import com.intellij.usageView.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationDescriptionProvider : ElementDescriptionProvider {
	override fun getElementDescription(element: PsiElement, location: ElementDescriptionLocation): String? {
		return when(element) {
			is PdxLocalisationLocale -> {
				if(location == UsageViewTypeLocation.INSTANCE) message("pdx.localisation.description.Locale")
				else element.name
			}
			is PdxLocalisationProperty -> {
				if(location == UsageViewTypeLocation.INSTANCE) message("pdx.localisation.description.property")
				else element.name
			}
			is PdxLocalisationIcon -> {
				if(location == UsageViewTypeLocation.INSTANCE) message("pdx.localisation.description.icon")
				else element.name
			}
			is PdxLocalisationColorfulText -> {
				if(location == UsageViewTypeLocation.INSTANCE) message("pdx.localisation.description.color")
				else element.name
			}
			is PdxLocalisationSerialNumber -> {
				if(location == UsageViewTypeLocation.INSTANCE) message("pdx.localisation.description.serialNumber")
				else element.name
			}
			else -> null
		}
	}
}
