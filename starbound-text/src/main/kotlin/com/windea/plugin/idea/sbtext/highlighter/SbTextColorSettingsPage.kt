@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.sbtext.highlighter

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.options.colors.*
import com.windea.plugin.idea.sbtext.*
import com.windea.plugin.idea.sbtext.SbTextBundle.message

class SbTextColorSettingsPage : ColorSettingsPage{
	companion object{
		//Capitalized words
		private val attributesDescriptors = arrayOf(
			AttributesDescriptor(message("sbText.color.marker"),SbTextAttributesKeys.MARKER_KEY),
			AttributesDescriptor(message("sbText.color.colorCode"),SbTextAttributesKeys.COLOR_CODE_KEY),
			AttributesDescriptor(message("sbText.color.text"),SbTextAttributesKeys.TEXT_KEY),
			AttributesDescriptor(message("sbText.color.validEscape"),SbTextAttributesKeys.VALID_ESCAPE_KEY),
			AttributesDescriptor(message("sbText.color.invalidEscape"),SbTextAttributesKeys.INVALID_ESCAPE_KEY),
			AttributesDescriptor(message("sbText.color.badCharacter"),SbTextAttributesKeys.BAD_CHARACTER_KEY)
		)
	}

	override fun getHighlighter() = SyntaxHighlighterFactory.getSyntaxHighlighter(SbTextLanguage,null,null)

	override fun getAdditionalHighlightingTagToDescriptorMap() = null

	override fun getIcon() = sbTextFileIcon

	override fun getAttributeDescriptors() = attributesDescriptors

	override fun getColorDescriptors() = ColorDescriptor.EMPTY_ARRAY

	override fun getDisplayName() = sbTextName

	override fun getDemoText() = sbTextDummyText
}

