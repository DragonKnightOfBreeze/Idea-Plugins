package com.windea.plugin.idea.paradox.localisation.editor

import com.intellij.psi.*
import com.intellij.usageView.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.localisation.psi.*

class ParadoxLocalisationDescriptionProvider : ElementDescriptionProvider {
	companion object{
		private val propertyDescription = message("paradox.localisation.description.property")
		private val localeDescription = message("paradox.localisation.description.locale")
		private val iconDescription = message("paradox.localisation.description.icon")
		private val commandKeyDescription = message("paradox.localisation.description.commandKey")
		private val serialNumberDescription = message("paradox.localisation.description.serialNumber")
		private val colorfulTextDescription = message("paradox.localisation.description.colorfulText")
	}
	
	override fun getElementDescription(element: PsiElement, location: ElementDescriptionLocation): String? {
		return when(element) {
			is ParadoxLocalisationProperty -> if(location == UsageViewTypeLocation.INSTANCE) propertyDescription else element.name
			is ParadoxLocalisationLocale -> if(location == UsageViewTypeLocation.INSTANCE) localeDescription else element.name
			is ParadoxLocalisationIcon -> if(location == UsageViewTypeLocation.INSTANCE) iconDescription else element.name
			is ParadoxLocalisationCommandKey -> if(location == UsageViewTypeLocation.INSTANCE) commandKeyDescription else element.name
			is ParadoxLocalisationColorfulText -> if(location == UsageViewTypeLocation.INSTANCE) serialNumberDescription else element.name
			is ParadoxLocalisationSerialNumber -> if(location == UsageViewTypeLocation.INSTANCE) colorfulTextDescription else element.name
			else -> null
		}
	}
}
