package com.windea.plugin.idea.paradox.script.highlighter

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.options.colors.*
import com.intellij.openapi.options.colors.ColorDescriptor.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.message
import com.windea.plugin.idea.paradox.script.*

class ParadoxScriptColorSettingsPage : ColorSettingsPage {
	companion object {
		private val separatorName = message("paradox.script.displayName.separator")
		private val bracesName = message("paradox.script.displayName.braces")
		private val variableName = message("paradox.script.displayName.variable")
		private val propertyKeyName = message("paradox.script.displayName.propertyKey")
		private val keywordName = message("paradox.script.displayName.keyword")
		private val colorName = message("paradox.script.displayName.color")
		private val numberName = message("paradox.script.displayName.number")
		private val stringName = message("paradox.script.displayName.string")
		private val codeName = message("paradox.script.displayName.code")
		private val commentName = message("paradox.script.displayName.comment")
		private val validEscapeName = message("paradox.script.displayName.validEscape")
		private val invalidEscapeName = message("paradox.script.displayName.invalidEscape")
		private val badCharacterName = message("paradox.script.displayName.badCharacter")
		
		private val attributesDescriptors = arrayOf(
			AttributesDescriptor(separatorName, ParadoxScriptAttributesKeys.SEPARATOR_KEY),
			AttributesDescriptor(bracesName, ParadoxScriptAttributesKeys.BRACES_KEY),
			AttributesDescriptor(variableName, ParadoxScriptAttributesKeys.VARIABLE_KEY),
			AttributesDescriptor(propertyKeyName, ParadoxScriptAttributesKeys.PROPERTY_KEY_KEY),
			AttributesDescriptor(keywordName, ParadoxScriptAttributesKeys.KEYWORD_KEY),
			AttributesDescriptor(colorName,ParadoxScriptAttributesKeys.COLOR_KEY),
			AttributesDescriptor(numberName,ParadoxScriptAttributesKeys. NUMBER_KEY),
			AttributesDescriptor(stringName, ParadoxScriptAttributesKeys.STRING_KEY),
			AttributesDescriptor(codeName, ParadoxScriptAttributesKeys.CODE_KEY),
			AttributesDescriptor(commentName, ParadoxScriptAttributesKeys.COMMENT_KEY),
			AttributesDescriptor(validEscapeName, ParadoxScriptAttributesKeys.VALID_ESCAPE_KEY),
			AttributesDescriptor(invalidEscapeName, ParadoxScriptAttributesKeys.INVALID_ESCAPE_KEY),
			AttributesDescriptor(badCharacterName, ParadoxScriptAttributesKeys.BAD_CHARACTER_KEY)
		)
	}

	override fun getHighlighter() = SyntaxHighlighterFactory.getSyntaxHighlighter(ParadoxScriptLanguage, null, null)

	override fun getAdditionalHighlightingTagToDescriptorMap() = null

	override fun getIcon() = paradoxScriptFileIcon

	override fun getAttributeDescriptors() = attributesDescriptors

	override fun getColorDescriptors() = EMPTY_ARRAY

	override fun getDisplayName() = paradoxScriptName

	override fun getDemoText() = paradoxScriptSampleText
}
