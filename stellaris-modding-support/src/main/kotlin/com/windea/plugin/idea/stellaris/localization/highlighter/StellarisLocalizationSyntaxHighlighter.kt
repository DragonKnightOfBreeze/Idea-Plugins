package com.windea.plugin.idea.stellaris.localization.highlighter

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*
import com.intellij.openapi.editor.HighlighterColors.*
import com.intellij.openapi.editor.colors.*
import com.intellij.openapi.editor.colors.TextAttributesKey.*
import com.intellij.openapi.fileTypes.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*

@ExtensionPoint
class StellarisLocalizationSyntaxHighlighter : SyntaxHighlighterBase() {
	companion object {
		val COLON_KEY = createTextAttributesKey(message("stellaris.localization.syntax.colon"), OPERATION_SIGN)
		val NUMBER_KEY = createTextAttributesKey(message("stellaris.localization.syntax.number"), NUMBER)
		val PROPERTY_HEADER_KEY = createTextAttributesKey(message("stellaris.localization.syntax.propertyHeader"), KEYWORD)
		val PROPERTY_KEY_KEY = createTextAttributesKey(message("stellaris.localization.syntax.propertyKey"), KEYWORD)
		val PROPERTY_VALUE_KEY = createTextAttributesKey(message("stellaris.localization.syntax.propertyValue"), STRING)
		val COMMENT_KEY = createTextAttributesKey(message("stellaris.localization.syntax.comment"), LINE_COMMENT)
		val VALID_ESCAPE_KEY = createTextAttributesKey(message("stellaris.localization.syntax.validEscape"), VALID_STRING_ESCAPE)
		val INVALID_ESCAPE_KEY = createTextAttributesKey(message("stellaris.localization.syntax.invalidEscape"), INVALID_STRING_ESCAPE)
		val BAD_CHARACTER_KEY = createTextAttributesKey(message("stellaris.localization.syntax.badCharacter"), BAD_CHARACTER)

		private val COLON_KEYS = arrayOf(COLON_KEY)
		private val NUMBER_KEYS = arrayOf(NUMBER_KEY)
		private val PROPERTY_HEADER_KEYS = arrayOf(PROPERTY_HEADER_KEY)
		private val PROPERTY_KEY_KEYS = arrayOf(PROPERTY_KEY_KEY)
		private val PROPERTY_VALUE_KEYS = arrayOf(PROPERTY_VALUE_KEY)
		private val COMMENT_KEYS = arrayOf(COMMENT_KEY)
		private val VALID_ESCAPE_KEYS = arrayOf(VALID_ESCAPE_KEY)
		private val INVALID_ESCAPE_KEYS = arrayOf(INVALID_ESCAPE_KEY)
		private val BAD_CHARACTER_KEYS = arrayOf(BAD_CHARACTER_KEY)
		private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
	}

	override fun getTokenHighlights(tokenType: IElementType?) = when(tokenType) {
		StellarisLocalizationTypes.HEADER_TOKEN -> PROPERTY_HEADER_KEYS
		StellarisLocalizationTypes.KEY_TOKEN -> PROPERTY_KEY_KEYS
		StellarisLocalizationTypes.VALUE_TOKEN -> PROPERTY_VALUE_KEYS
		StellarisLocalizationTypes.COLON -> COLON_KEYS
		StellarisLocalizationTypes.NUMBER -> NUMBER_KEYS
		StellarisLocalizationTypes.COMMENT -> COMMENT_KEYS
		StellarisLocalizationTypes.ROOT_COMMENT -> COMMENT_KEYS
		StringEscapesTokenTypes.VALID_STRING_ESCAPE_TOKEN -> VALID_ESCAPE_KEYS
		StringEscapesTokenTypes.INVALID_CHARACTER_ESCAPE_TOKEN -> INVALID_ESCAPE_KEYS
		StringEscapesTokenTypes.INVALID_UNICODE_ESCAPE_TOKEN -> INVALID_ESCAPE_KEYS
		TokenType.BAD_CHARACTER -> BAD_CHARACTER_KEYS
		else -> EMPTY_KEYS
	}

	override fun getHighlightingLexer() = StellarisLocalizationHighlighterLexer()
}


