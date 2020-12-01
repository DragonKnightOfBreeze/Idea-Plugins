package com.windea.plugin.idea.paradox.script.highlighter

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.options.colors.*
import com.intellij.openapi.options.colors.ColorDescriptor.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.message
import com.windea.plugin.idea.paradox.script.*

class ParadoxScriptColorSettingsPage : ColorSettingsPage {
	companion object {
		//Capitalized Words
		private val attributesDescriptors = arrayOf(
			AttributesDescriptor(message("paradox.script.color.separator"), ParadoxScriptAttributesKeys.SEPARATOR_KEY),
			AttributesDescriptor(message("paradox.script.color.braces"), ParadoxScriptAttributesKeys.BRACES_KEY),
			AttributesDescriptor(message("paradox.script.color.variable"), ParadoxScriptAttributesKeys.VARIABLE_KEY),
			AttributesDescriptor(message("paradox.script.color.propertyKey"), ParadoxScriptAttributesKeys.PROPERTY_KEY_KEY),
			AttributesDescriptor(message("paradox.script.color.keyword"), ParadoxScriptAttributesKeys.KEYWORD_KEY),
			AttributesDescriptor(message("paradox.script.color.color"),ParadoxScriptAttributesKeys.COLOR_KEY),
			AttributesDescriptor(message("paradox.script.color.number"),ParadoxScriptAttributesKeys. NUMBER_KEY),
			AttributesDescriptor(message("paradox.script.color.string"), ParadoxScriptAttributesKeys.STRING_KEY),
			AttributesDescriptor(message("paradox.script.color.code"), ParadoxScriptAttributesKeys.CODE_KEY),
			AttributesDescriptor(message("paradox.script.color.comment"), ParadoxScriptAttributesKeys.COMMENT_KEY),
			AttributesDescriptor(message("paradox.script.color.validEscape"), ParadoxScriptAttributesKeys.VALID_ESCAPE_KEY),
			AttributesDescriptor(message("paradox.script.color.invalidEscape"), ParadoxScriptAttributesKeys.INVALID_ESCAPE_KEY),
			AttributesDescriptor(message("paradox.script.color.badCharacter"), ParadoxScriptAttributesKeys.BAD_CHARACTER_KEY)
		)
	}

	override fun getHighlighter() = SyntaxHighlighterFactory.getSyntaxHighlighter(ParadoxScriptLanguage, null, null)

	override fun getAdditionalHighlightingTagToDescriptorMap() = null

	override fun getIcon() = paradoxScriptFileIcon

	override fun getAttributeDescriptors() = attributesDescriptors

	override fun getColorDescriptors() = EMPTY_ARRAY

	override fun getDisplayName() = paradoxScriptName

	override fun getDemoText() = paradoxScriptDummyText
}
