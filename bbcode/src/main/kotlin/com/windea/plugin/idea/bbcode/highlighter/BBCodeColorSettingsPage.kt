@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.bbcode.highlighter

import com.intellij.openapi.editor.colors.*
import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.options.colors.*
import com.intellij.openapi.util.*
import com.windea.plugin.idea.bbcode.*
import com.windea.plugin.idea.bbcode.BBCodeBundle.message
import org.bouncycastle.operator.bc.*
import javax.swing.*

class BBCodeColorSettingsPage :ColorSettingsPage{
	companion object{
		//Capitalized words
		private val attributesDescriptors = arrayOf(
			AttributesDescriptor(message("bbcode.color.marker"),BBCodeAttributesKeys.MARKER_KEY),
			AttributesDescriptor(message("bbcode.color.tagName"),BBCodeAttributesKeys.TAG_NAME_KEY),
			AttributesDescriptor(message("bbcode.color.attributeName"),BBCodeAttributesKeys.ATTRIBUTE_NAME_KEY),
			AttributesDescriptor(message("bbcode.color.attributeValue"),BBCodeAttributesKeys.ATTRIBUTE_VALUE_KEY),
			AttributesDescriptor(message("bbcode.color.text"),BBCodeAttributesKeys.TEXT_KEY),
			AttributesDescriptor(message("bbcode.color.badCharacter"),BBCodeAttributesKeys.BAD_CHARACTER_KEY)
		)
	}

	override fun getHighlighter() = SyntaxHighlighterFactory.getSyntaxHighlighter(BBCodeLanguage,null,null)

	override fun getAdditionalHighlightingTagToDescriptorMap() = null

	override fun getIcon() = bbcodeFileIcon

	override fun getAttributeDescriptors() = attributesDescriptors

	override fun getColorDescriptors() = ColorDescriptor.EMPTY_ARRAY

	override fun getDisplayName() = bbcodeName

	override fun getDemoText() = bbcodeDummyText
}
