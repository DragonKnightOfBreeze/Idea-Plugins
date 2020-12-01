@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.pdx.localisation.highlighter

import com.intellij.openapi.editor.colors.*
import com.intellij.openapi.fileTypes.*
import com.intellij.psi.TokenType.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.pdx.localisation.psi.*
import com.windea.plugin.idea.pdx.localisation.psi.PdxLocalisationTypes.*

class PdxLocalisationSyntaxHighlighter : SyntaxHighlighterBase() {
	companion object {
		private val COLON_KEYS = arrayOf(PdxLocalisationAttributesKeys.COLON_KEY)
		private val NUMBER_KEYS = arrayOf(PdxLocalisationAttributesKeys.NUMBER_KEY)
		private val LOCALE_KEYS = arrayOf(PdxLocalisationAttributesKeys.LOCALE_KEY)
		private val PROPERTY_KEY_KEYS = arrayOf(PdxLocalisationAttributesKeys.PROPERTY_KEY_KEY)
		private val STRING_KEYS = arrayOf(PdxLocalisationAttributesKeys.STRING_KEY)
		private val COMMENT_KEYS = arrayOf(PdxLocalisationAttributesKeys.COMMENT_KEY)
		private val MARKER_KEYS = arrayOf(PdxLocalisationAttributesKeys.MARKER_KEY)
		private val PARAMETER_KEYS = arrayOf(PdxLocalisationAttributesKeys.PARAMETER_KEY)
		private val PROPERTY_REFERENCE_KEYS = arrayOf(PdxLocalisationAttributesKeys.PROPERTY_REFERENCE_KEY)
		private val CODE_KEYS = arrayOf(PdxLocalisationAttributesKeys.CODE_KEY)
		private val ICON_KEYS = arrayOf(PdxLocalisationAttributesKeys.ICON_KEY)
		private val SERIAL_NUMBER_ID_KEYS = arrayOf(PdxLocalisationAttributesKeys.SERIAL_NUMBER_ID_KEY)
		private val COLOR_ID_KEYS = arrayOf(PdxLocalisationAttributesKeys.COLOR_ID_KEY)
		private val VALID_ESCAPE_KEYS = arrayOf(PdxLocalisationAttributesKeys.VALID_ESCAPE_KEY)
		private val INVALID_ESCAPE_KEYS = arrayOf(PdxLocalisationAttributesKeys.INVALID_ESCAPE_KEY)
		private val BAD_CHARACTER_KEYS = arrayOf(PdxLocalisationAttributesKeys.BAD_CHARACTER_KEY)
		private val EMPTY_KEYS = TextAttributesKey.EMPTY_ARRAY
	}

	override fun getTokenHighlights(tokenType: IElementType?) = when(tokenType) {
		COLON -> COLON_KEYS
		LOCALE_ID -> LOCALE_KEYS
		PROPERTY_KEY_ID -> PROPERTY_KEY_KEYS
		PROPERTY_REFERENCE_ID -> PROPERTY_REFERENCE_KEYS
		STRING_TOKEN, LEFT_QUOTE, RIGHT_QUOTE -> STRING_KEYS
		PROPERTY_REFERENCE_START, PARAMETER_SEPARATOR, PROPERTY_REFERENCE_END,
		CODE_START, CODE_END, ICON_START, ICON_END,
		SERIAL_NUMBER_START, SERIAL_NUMBER_END, COLORFUL_TEXT_START, COLORFUL_TEXT_END -> MARKER_KEYS
		PROPERTY_REFERENCE_PARAMETER, ICON_PARAMETER -> PARAMETER_KEYS
		NUMBER -> NUMBER_KEYS
		CODE_TEXT_TOKEN -> CODE_KEYS
		ICON_ID -> ICON_KEYS
		SERIAL_NUMBER_ID -> SERIAL_NUMBER_ID_KEYS
		COLOR_CODE -> COLOR_ID_KEYS
		COMMENT, END_OF_LINE_COMMENT, ROOT_COMMENT -> COMMENT_KEYS
		VALID_ESCAPE_TOKEN -> VALID_ESCAPE_KEYS
		INVALID_ESCAPE_TOKEN -> INVALID_ESCAPE_KEYS
		BAD_CHARACTER -> BAD_CHARACTER_KEYS
		else -> EMPTY_KEYS
	}

	override fun getHighlightingLexer() = PdxLocalisationLexerAdapter()
}

