@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.paradox.localisation.highlighter

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.options.colors.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.message
import com.windea.plugin.idea.paradox.localisation.*

class ParadoxLocalisationColorSettingsPage : ColorSettingsPage {
	companion object {
		//Capitalized words
		private val attributesDescriptors = arrayOf(
			AttributesDescriptor(message("paradox.localisation.color.colon"), ParadoxLocalisationAttributesKeys.COLON_KEY),
			AttributesDescriptor(message("paradox.localisation.color.number"), ParadoxLocalisationAttributesKeys.NUMBER_KEY),
			AttributesDescriptor(message("paradox.localisation.color.Locale"), ParadoxLocalisationAttributesKeys.LOCALE_KEY),
			AttributesDescriptor(message("paradox.localisation.color.propertyKey"), ParadoxLocalisationAttributesKeys.PROPERTY_KEY_KEY),
			AttributesDescriptor(message("paradox.localisation.color.string"), ParadoxLocalisationAttributesKeys.STRING_KEY),
			AttributesDescriptor(message("paradox.localisation.color.comment"), ParadoxLocalisationAttributesKeys.COMMENT_KEY),
			AttributesDescriptor(message("paradox.localisation.color.marker"), ParadoxLocalisationAttributesKeys.MARKER_KEY),
			AttributesDescriptor(message("paradox.localisation.color.parameter"), ParadoxLocalisationAttributesKeys.PARAMETER_KEY),
			AttributesDescriptor(message("paradox.localisation.color.propertyReference"), ParadoxLocalisationAttributesKeys.PROPERTY_REFERENCE_KEY),
			AttributesDescriptor(message("paradox.localisation.color.code"), ParadoxLocalisationAttributesKeys.CODE_KEY),
			AttributesDescriptor(message("paradox.localisation.color.icon"), ParadoxLocalisationAttributesKeys.ICON_KEY),
			AttributesDescriptor(message("paradox.localisation.color.serialNumberId"), ParadoxLocalisationAttributesKeys.SERIAL_NUMBER_ID_KEY),
			AttributesDescriptor(message("paradox.localisation.color.colorId"), ParadoxLocalisationAttributesKeys.COLOR_ID_KEY),
			AttributesDescriptor(message("paradox.localisation.color.validEscape"), ParadoxLocalisationAttributesKeys.VALID_ESCAPE_KEY),
			AttributesDescriptor(message("paradox.localisation.color.invalidEscape"), ParadoxLocalisationAttributesKeys.INVALID_ESCAPE_KEY),
			AttributesDescriptor(message("paradox.localisation.color.badCharacter"), ParadoxLocalisationAttributesKeys.BAD_CHARACTER_KEY)
		)
	}

	override fun getHighlighter() = SyntaxHighlighterFactory.getSyntaxHighlighter(ParadoxLocalisationLanguage, null, null)

	override fun getAdditionalHighlightingTagToDescriptorMap() = null

	override fun getIcon() = paradoxLocalisationFileIcon

	override fun getAttributeDescriptors() = attributesDescriptors

	override fun getColorDescriptors() = ColorDescriptor.EMPTY_ARRAY

	override fun getDisplayName() = paradoxLocalisationName

	override fun getDemoText() = paradoxLocalisationDummyText
}

