@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.stellaris.localization.settings

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.options.colors.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.localization.highlighter.StellarisLocalizationSyntaxHighlighter.Companion.BAD_CHARACTER_KEY
import com.windea.plugin.idea.stellaris.localization.highlighter.StellarisLocalizationSyntaxHighlighter.Companion.COLON_KEY
import com.windea.plugin.idea.stellaris.localization.highlighter.StellarisLocalizationSyntaxHighlighter.Companion.COMMENT_KEY
import com.windea.plugin.idea.stellaris.localization.highlighter.StellarisLocalizationSyntaxHighlighter.Companion.INVALID_ESCAPE_KEY
import com.windea.plugin.idea.stellaris.localization.highlighter.StellarisLocalizationSyntaxHighlighter.Companion.NUMBER_KEY
import com.windea.plugin.idea.stellaris.localization.highlighter.StellarisLocalizationSyntaxHighlighter.Companion.PROPERTY_HEADER_KEY
import com.windea.plugin.idea.stellaris.localization.highlighter.StellarisLocalizationSyntaxHighlighter.Companion.PROPERTY_KEY_KEY
import com.windea.plugin.idea.stellaris.localization.highlighter.StellarisLocalizationSyntaxHighlighter.Companion.PROPERTY_VALUE_KEY
import com.windea.plugin.idea.stellaris.localization.highlighter.StellarisLocalizationSyntaxHighlighter.Companion.VALID_ESCAPE_KEY

@ExtensionPoint
class StellarisLocalizationColorSettingsPage : ColorSettingsPage {
	companion object {
		//Capitalized words
		private val attributesDescriptors = arrayOf(
			AttributesDescriptor(message("stellaris.localization.color.colon"), COLON_KEY),
			AttributesDescriptor(message("stellaris.localization.color.number"), NUMBER_KEY),
			AttributesDescriptor(message("stellaris.localization.color.propertyHeader"), PROPERTY_HEADER_KEY),
			AttributesDescriptor(message("stellaris.localization.color.propertyKey"), PROPERTY_KEY_KEY),
			AttributesDescriptor(message("stellaris.localization.color.propertyValue"), PROPERTY_VALUE_KEY),
			AttributesDescriptor(message("stellaris.localization.color.comment"), COMMENT_KEY),
			AttributesDescriptor(message("stellaris.localization.color.validEscape"), VALID_ESCAPE_KEY),
			AttributesDescriptor(message("stellaris.localization.color.invalidEscape"), INVALID_ESCAPE_KEY),
			AttributesDescriptor(message("stellaris.localization.color.badCharacter"), BAD_CHARACTER_KEY)
		)
	}

	override fun getHighlighter() = SyntaxHighlighterFactory.getSyntaxHighlighter(StellarisLocalizationLanguage, null, null)

	override fun getAdditionalHighlightingTagToDescriptorMap() = null

	override fun getIcon() = stellarisLocalizationIcon

	override fun getAttributeDescriptors() = attributesDescriptors

	override fun getColorDescriptors() = ColorDescriptor.EMPTY_ARRAY

	override fun getDisplayName() = stellarisLocalizationName

	override fun getDemoText() = stellarisLocalizationDummyText
}

