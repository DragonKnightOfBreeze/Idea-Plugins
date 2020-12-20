@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.paradox.localisation.highlighter

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.options.colors.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.message
import com.windea.plugin.idea.paradox.localisation.*

class ParadoxLocalisationColorSettingsPage : ColorSettingsPage {
	companion object {
		private val separatorName = message("paradox.localisation.displayName.separator")
		private val numberName = message("paradox.localisation.displayName.number")
		private val localeName = message("paradox.localisation.displayName.locale")
		private val propertyKeyName = message("paradox.localisation.displayName.propertyKey")
		private val stringName = message("paradox.localisation.displayName.string")
		private val commentName = message("paradox.localisation.displayName.comment")
		private val markerName = message("paradox.localisation.displayName.marker")
		private val parameterName = message("paradox.localisation.displayName.parameter")
		private val propertyReferenceName = message("paradox.localisation.displayName.propertyReference")
		private val commandKeyName = message("paradox.localisation.displayName.codeKey")
		private val iconName = message("paradox.localisation.displayName.icon")
		private val serialNumberName = message("paradox.localisation.displayName.serialNumber")
		private val colorName = message("paradox.localisation.displayName.color")
		private val validEscapeName = message("paradox.localisation.displayName.validEscape")
		private val invalidEscapeName = message("paradox.localisation.displayName.invalidEscape")
		private val badCharacterName = message("paradox.localisation.displayName.badCharacter")
		
		private val attributesDescriptors = arrayOf(
			AttributesDescriptor(separatorName, ParadoxLocalisationAttributesKeys.SEPARATOR_KEY),
			AttributesDescriptor(numberName, ParadoxLocalisationAttributesKeys.NUMBER_KEY),
			AttributesDescriptor(localeName, ParadoxLocalisationAttributesKeys.LOCALE_KEY),
			AttributesDescriptor(propertyKeyName, ParadoxLocalisationAttributesKeys.PROPERTY_KEY_KEY),
			AttributesDescriptor(stringName, ParadoxLocalisationAttributesKeys.STRING_KEY),
			AttributesDescriptor(commentName, ParadoxLocalisationAttributesKeys.COMMENT_KEY),
			AttributesDescriptor(markerName, ParadoxLocalisationAttributesKeys.MARKER_KEY),
			AttributesDescriptor(parameterName, ParadoxLocalisationAttributesKeys.PARAMETER_KEY),
			AttributesDescriptor(propertyReferenceName, ParadoxLocalisationAttributesKeys.PROPERTY_REFERENCE_KEY),
			AttributesDescriptor(commandKeyName, ParadoxLocalisationAttributesKeys.COMMAND_KEY_KEY),
			AttributesDescriptor(iconName, ParadoxLocalisationAttributesKeys.ICON_KEY),
			AttributesDescriptor(serialNumberName, ParadoxLocalisationAttributesKeys.SERIAL_NUMBER_KEY),
			AttributesDescriptor(colorName, ParadoxLocalisationAttributesKeys.COLOR_KEY),
			AttributesDescriptor(validEscapeName, ParadoxLocalisationAttributesKeys.VALID_ESCAPE_KEY),
			AttributesDescriptor(invalidEscapeName, ParadoxLocalisationAttributesKeys.INVALID_ESCAPE_KEY),
			AttributesDescriptor(badCharacterName, ParadoxLocalisationAttributesKeys.BAD_CHARACTER_KEY)
		)
	}

	override fun getHighlighter() = SyntaxHighlighterFactory.getSyntaxHighlighter(ParadoxLocalisationLanguage, null, null)

	override fun getAdditionalHighlightingTagToDescriptorMap() = null

	override fun getIcon() = paradoxLocalisationFileIcon

	override fun getAttributeDescriptors() = attributesDescriptors

	override fun getColorDescriptors() = ColorDescriptor.EMPTY_ARRAY

	override fun getDisplayName() = paradoxLocalisationName

	override fun getDemoText() = paradoxLocalisationSampleText
}

