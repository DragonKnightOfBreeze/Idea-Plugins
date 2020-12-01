@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.pdx.localisation.highlighter

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.options.colors.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.message
import com.windea.plugin.idea.pdx.localisation.*

class PdxLocalisationColorSettingsPage : ColorSettingsPage {
	companion object {
		//Capitalized words
		private val attributesDescriptors = arrayOf(
			AttributesDescriptor(message("pdx.localisation.color.colon"), PdxLocalisationAttributesKeys.COLON_KEY),
			AttributesDescriptor(message("pdx.localisation.color.number"), PdxLocalisationAttributesKeys.NUMBER_KEY),
			AttributesDescriptor(message("pdx.localisation.color.Locale"), PdxLocalisationAttributesKeys.LOCALE_KEY),
			AttributesDescriptor(message("pdx.localisation.color.propertyKey"), PdxLocalisationAttributesKeys.PROPERTY_KEY_KEY),
			AttributesDescriptor(message("pdx.localisation.color.string"), PdxLocalisationAttributesKeys.STRING_KEY),
			AttributesDescriptor(message("pdx.localisation.color.comment"), PdxLocalisationAttributesKeys.COMMENT_KEY),
			AttributesDescriptor(message("pdx.localisation.color.marker"), PdxLocalisationAttributesKeys.MARKER_KEY),
			AttributesDescriptor(message("pdx.localisation.color.parameter"), PdxLocalisationAttributesKeys.PARAMETER_KEY),
			AttributesDescriptor(message("pdx.localisation.color.propertyReference"), PdxLocalisationAttributesKeys.PROPERTY_REFERENCE_KEY),
			AttributesDescriptor(message("pdx.localisation.color.code"), PdxLocalisationAttributesKeys.CODE_KEY),
			AttributesDescriptor(message("pdx.localisation.color.icon"), PdxLocalisationAttributesKeys.ICON_KEY),
			AttributesDescriptor(message("pdx.localisation.color.serialNumberId"), PdxLocalisationAttributesKeys.SERIAL_NUMBER_ID_KEY),
			AttributesDescriptor(message("pdx.localisation.color.colorId"), PdxLocalisationAttributesKeys.COLOR_ID_KEY),
			AttributesDescriptor(message("pdx.localisation.color.validEscape"), PdxLocalisationAttributesKeys.VALID_ESCAPE_KEY),
			AttributesDescriptor(message("pdx.localisation.color.invalidEscape"), PdxLocalisationAttributesKeys.INVALID_ESCAPE_KEY),
			AttributesDescriptor(message("pdx.localisation.color.badCharacter"), PdxLocalisationAttributesKeys.BAD_CHARACTER_KEY)
		)
	}

	override fun getHighlighter() = SyntaxHighlighterFactory.getSyntaxHighlighter(PdxLocalisationLanguage, null, null)

	override fun getAdditionalHighlightingTagToDescriptorMap() = null

	override fun getIcon() = pdxLocalisationFileIcon

	override fun getAttributeDescriptors() = attributesDescriptors

	override fun getColorDescriptors() = ColorDescriptor.EMPTY_ARRAY

	override fun getDisplayName() = pdxLocalisationName

	override fun getDemoText() = pdxLocalisationDummyText
}

