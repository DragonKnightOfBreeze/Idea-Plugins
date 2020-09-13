@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.stellaris.script.highlighter

import com.intellij.openapi.editor.colors.*
import com.intellij.openapi.fileTypes.*
import com.intellij.psi.*
import com.intellij.psi.StringEscapesTokenTypes.*
import com.intellij.psi.TokenType.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

class StellarisScriptSyntaxHighlighter : SyntaxHighlighterBase() {
	companion object {
		private val SEPARATOR_KEYS = arrayOf(StellarisScriptAttributesKeys.SEPARATOR_KEY)
		private val BRACE_KEYS = arrayOf(StellarisScriptAttributesKeys.BRACES_KEY)
		private val VARIABLE_KEYS = arrayOf(StellarisScriptAttributesKeys.VARIABLE_KEY)
		private val PROPERTY_KEY_KEYS = arrayOf(StellarisScriptAttributesKeys.PROPERTY_KEY_KEY)
		private val KEYWORD_KEYS = arrayOf(StellarisScriptAttributesKeys.KEYWORD_KEY)
		private val COLOR_KEYS = arrayOf(StellarisScriptAttributesKeys.COLOR_KEY)
		private val NUMBER_KEYS = arrayOf(StellarisScriptAttributesKeys.NUMBER_KEY)
		private val STRING_KEYS = arrayOf(StellarisScriptAttributesKeys.STRING_KEY)
		private val COMMENT_KEYS = arrayOf(StellarisScriptAttributesKeys.COMMENT_KEY)
		private val VALID_ESCAPE_KEYS = arrayOf(StellarisScriptAttributesKeys.VALID_ESCAPE_KEY)
		private val INVALID_ESCAPE_KEYS = arrayOf(StellarisScriptAttributesKeys.INVALID_ESCAPE_KEY)
		private val BAD_CHARACTER_KEYS = arrayOf(StellarisScriptAttributesKeys.BAD_CHARACTER_KEY)
		private val EMPTY_KEYS = TextAttributesKey.EMPTY_ARRAY
	}

	override fun getTokenHighlights(tokenType: IElementType?) = when(tokenType) {
		EQUAL_SIGN, LE_SIGN, LT_SIGN,GE_SIGN,GT_SIGN -> SEPARATOR_KEYS
		LEFT_BRACE,RIGHT_BRACE -> BRACE_KEYS
		VARIABLE_NAME_ID -> VARIABLE_KEYS
		PROPERTY_KEY_ID -> PROPERTY_KEY_KEYS
		VARIABLE_REFERENCE_ID -> VARIABLE_KEYS
		BOOLEAN_TOKEN -> KEYWORD_KEYS
		COLOR_TOKEN -> COLOR_KEYS
		NUMBER_TOKEN -> NUMBER_KEYS
		STRING_TOKEN -> STRING_KEYS
		UNQUOTED_STRING_TOKEN -> STRING_KEYS
		COMMENT -> COMMENT_KEYS
		END_OF_LINE_COMMENT -> COMMENT_KEYS
		VALID_STRING_ESCAPE_TOKEN -> VALID_ESCAPE_KEYS
		INVALID_CHARACTER_ESCAPE_TOKEN, INVALID_UNICODE_ESCAPE_TOKEN -> INVALID_ESCAPE_KEYS
		BAD_CHARACTER -> BAD_CHARACTER_KEYS
		else -> EMPTY_KEYS
	}

	override fun getHighlightingLexer() = StellarisScriptLexerAdapter()
}
