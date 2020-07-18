package com.windea.plugin.idea.stellaris.script.settings

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.options.colors.*
import com.intellij.openapi.options.colors.ColorDescriptor.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.*
import com.windea.plugin.idea.stellaris.script.highlighter.StellarisScriptSyntaxHighlighter.Companion.BAD_CHARACTER_KEY
import com.windea.plugin.idea.stellaris.script.highlighter.StellarisScriptSyntaxHighlighter.Companion.BRACES_KEY
import com.windea.plugin.idea.stellaris.script.highlighter.StellarisScriptSyntaxHighlighter.Companion.COMMENT_KEY
import com.windea.plugin.idea.stellaris.script.highlighter.StellarisScriptSyntaxHighlighter.Companion.INVALID_ESCAPE_KEY
import com.windea.plugin.idea.stellaris.script.highlighter.StellarisScriptSyntaxHighlighter.Companion.SEPARATOR_KEY
import com.windea.plugin.idea.stellaris.script.highlighter.StellarisScriptSyntaxHighlighter.Companion.KEYWORD_KEY
import com.windea.plugin.idea.stellaris.script.highlighter.StellarisScriptSyntaxHighlighter.Companion.NUMBER_KEY
import com.windea.plugin.idea.stellaris.script.highlighter.StellarisScriptSyntaxHighlighter.Companion.PROPERTY_KEY_KEY
import com.windea.plugin.idea.stellaris.script.highlighter.StellarisScriptSyntaxHighlighter.Companion.STRING_KEY
import com.windea.plugin.idea.stellaris.script.highlighter.StellarisScriptSyntaxHighlighter.Companion.VALID_ESCAPE_KEY
import com.windea.plugin.idea.stellaris.script.highlighter.StellarisScriptSyntaxHighlighter.Companion.VARIABLE_KEY

@ExtensionPoint
class StellarisScriptColorSettingsPage : ColorSettingsPage {
	companion object {
		//Capitalized words
		private val attributesDescriptors = arrayOf(
			AttributesDescriptor(message("stellaris.script.color.separator"), SEPARATOR_KEY),
			AttributesDescriptor(message("stellaris.script.color.braces"), BRACES_KEY),
			AttributesDescriptor(message("stellaris.script.color.variable"), VARIABLE_KEY),
			AttributesDescriptor(message("stellaris.script.color.propertyKey"), PROPERTY_KEY_KEY),
			AttributesDescriptor(message("stellaris.script.color.keyword"), KEYWORD_KEY),
			AttributesDescriptor(message("stellaris.script.color.number"), STRING_KEY),
			AttributesDescriptor(message("stellaris.script.color.string"), NUMBER_KEY),
			AttributesDescriptor(message("stellaris.script.color.comment"), COMMENT_KEY),
			AttributesDescriptor(message("stellaris.script.color.validEscape"), VALID_ESCAPE_KEY),
			AttributesDescriptor(message("stellaris.script.color.invalidEscape"), INVALID_ESCAPE_KEY),
			AttributesDescriptor(message("stellaris.script.color.badCharacter"), BAD_CHARACTER_KEY)
		)
	}

	override fun getHighlighter() = SyntaxHighlighterFactory.getSyntaxHighlighter(StellarisScriptLanguage, null, null)

	override fun getAdditionalHighlightingTagToDescriptorMap() = null

	override fun getIcon() = stellarisScriptIcon

	override fun getAttributeDescriptors() = attributesDescriptors

	override fun getColorDescriptors() = EMPTY_ARRAY

	override fun getDisplayName() = stellarisScriptName

	override fun getDemoText() = stellarisScriptDummyText
}
