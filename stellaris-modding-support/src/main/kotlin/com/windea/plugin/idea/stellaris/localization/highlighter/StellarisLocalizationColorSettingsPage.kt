@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.stellaris.localization.highlighter

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.options.colors.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.localization.*

class StellarisLocalizationColorSettingsPage : ColorSettingsPage {
	companion object {
		//Capitalized words
		private val attributesDescriptors = arrayOf(
			AttributesDescriptor(message("stellaris.localization.color.colon"), StellarisLocalizationAttributesKeys.COLON_KEY),
			AttributesDescriptor(message("stellaris.localization.color.number"), StellarisLocalizationAttributesKeys.NUMBER_KEY),
			AttributesDescriptor(message("stellaris.localization.color.Locale"), StellarisLocalizationAttributesKeys.LOCALE_KEY),
			AttributesDescriptor(message("stellaris.localization.color.propertyKey"), StellarisLocalizationAttributesKeys.PROPERTY_KEY_KEY),
			AttributesDescriptor(message("stellaris.localization.color.string"), StellarisLocalizationAttributesKeys.STRING_KEY),
			AttributesDescriptor(message("stellaris.localization.color.comment"), StellarisLocalizationAttributesKeys.COMMENT_KEY),
			AttributesDescriptor(message("stellaris.localization.color.marker"), StellarisLocalizationAttributesKeys.MARKER_KEY),
			AttributesDescriptor(message("stellaris.localization.color.parameter"), StellarisLocalizationAttributesKeys.PARAMETER_KEY),
			AttributesDescriptor(message("stellaris.localization.color.propertyReference"), StellarisLocalizationAttributesKeys.PROPERTY_REFERENCE_KEY),
			AttributesDescriptor(message("stellaris.localization.color.code"), StellarisLocalizationAttributesKeys.CODE_KEY),
			AttributesDescriptor(message("stellaris.localization.color.icon"), StellarisLocalizationAttributesKeys.ICON_KEY),
			AttributesDescriptor(message("stellaris.localization.color.serialNumberId"), StellarisLocalizationAttributesKeys.SERIAL_NUMBER_ID_KEY),
			AttributesDescriptor(message("stellaris.localization.color.colorId"), StellarisLocalizationAttributesKeys.COLOR_ID_KEY),
			AttributesDescriptor(message("stellaris.localization.color.validEscape"), StellarisLocalizationAttributesKeys.VALID_ESCAPE_KEY),
			AttributesDescriptor(message("stellaris.localization.color.invalidEscape"), StellarisLocalizationAttributesKeys.INVALID_ESCAPE_KEY),
			AttributesDescriptor(message("stellaris.localization.color.badCharacter"), StellarisLocalizationAttributesKeys.BAD_CHARACTER_KEY)
		)
	}

	override fun getHighlighter() = SyntaxHighlighterFactory.getSyntaxHighlighter(StellarisLocalizationLanguage, null, null)

	override fun getAdditionalHighlightingTagToDescriptorMap() = null

	override fun getIcon() = stellarisLocalizationFileIcon

	override fun getAttributeDescriptors() = attributesDescriptors

	override fun getColorDescriptors() = ColorDescriptor.EMPTY_ARRAY

	override fun getDisplayName() = stellarisLocalizationName

	override fun getDemoText() = stellarisLocalizationDummyText
}

