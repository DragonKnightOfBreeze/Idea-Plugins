package com.windea.plugin.idea.pdx.script.highlighter

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.options.colors.*
import com.intellij.openapi.options.colors.ColorDescriptor.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.message
import com.windea.plugin.idea.pdx.script.*

class PdxScriptColorSettingsPage : ColorSettingsPage {
	companion object {
		//Capitalized Words
		private val attributesDescriptors = arrayOf(
			AttributesDescriptor(message("pdx.script.color.separator"), PdxScriptAttributesKeys.SEPARATOR_KEY),
			AttributesDescriptor(message("pdx.script.color.braces"), PdxScriptAttributesKeys.BRACES_KEY),
			AttributesDescriptor(message("pdx.script.color.variable"), PdxScriptAttributesKeys.VARIABLE_KEY),
			AttributesDescriptor(message("pdx.script.color.propertyKey"), PdxScriptAttributesKeys.PROPERTY_KEY_KEY),
			AttributesDescriptor(message("pdx.script.color.keyword"), PdxScriptAttributesKeys.KEYWORD_KEY),
			AttributesDescriptor(message("pdx.script.color.color"),PdxScriptAttributesKeys.COLOR_KEY),
			AttributesDescriptor(message("pdx.script.color.number"),PdxScriptAttributesKeys. NUMBER_KEY),
			AttributesDescriptor(message("pdx.script.color.string"), PdxScriptAttributesKeys.STRING_KEY),
			AttributesDescriptor(message("pdx.script.color.code"), PdxScriptAttributesKeys.CODE_KEY),
			AttributesDescriptor(message("pdx.script.color.comment"), PdxScriptAttributesKeys.COMMENT_KEY),
			AttributesDescriptor(message("pdx.script.color.validEscape"), PdxScriptAttributesKeys.VALID_ESCAPE_KEY),
			AttributesDescriptor(message("pdx.script.color.invalidEscape"), PdxScriptAttributesKeys.INVALID_ESCAPE_KEY),
			AttributesDescriptor(message("pdx.script.color.badCharacter"), PdxScriptAttributesKeys.BAD_CHARACTER_KEY)
		)
	}

	override fun getHighlighter() = SyntaxHighlighterFactory.getSyntaxHighlighter(PdxScriptLanguage, null, null)

	override fun getAdditionalHighlightingTagToDescriptorMap() = null

	override fun getIcon() = pdxScriptFileIcon

	override fun getAttributeDescriptors() = attributesDescriptors

	override fun getColorDescriptors() = EMPTY_ARRAY

	override fun getDisplayName() = pdxScriptName

	override fun getDemoText() = pdxScriptDummyText
}
