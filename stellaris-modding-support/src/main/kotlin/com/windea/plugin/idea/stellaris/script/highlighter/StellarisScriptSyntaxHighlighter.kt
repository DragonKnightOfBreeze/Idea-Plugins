package com.windea.plugin.idea.stellaris.script.highlighter

import com.intellij.lexer.*
import com.intellij.openapi.editor.colors.*
import com.intellij.openapi.fileTypes.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.psi.*

@ExtensionPoint
class StellarisScriptSyntaxHighlighter : SyntaxHighlighterBase() {
	companion object {
		private val SEPARATOR_KEYS = arrayOf(StellarisScriptAttributesKeys.SEPARATOR_KEY)
		private val BRACE_KEYS = arrayOf(StellarisScriptAttributesKeys.BRACES_KEY)
		private val VARIABLE_KEYS = arrayOf(StellarisScriptAttributesKeys.VARIABLE_KEY)
		private val PROPERTY_KEY_KEYS = arrayOf(StellarisScriptAttributesKeys.PROPERTY_KEY_KEY)
		private val KEYWORD_KEYS = arrayOf(StellarisScriptAttributesKeys.KEYWORD_KEY)
		private val NUMBER_KEYS = arrayOf(StellarisScriptAttributesKeys.NUMBER_KEY)
		private val STRING_KEYS = arrayOf(StellarisScriptAttributesKeys.STRING_KEY)
		private val COMMENT_KEYS = arrayOf(StellarisScriptAttributesKeys.COMMENT_KEY)
		private val VALID_ESCAPE_KEYS = arrayOf(StellarisScriptAttributesKeys.VALID_ESCAPE_KEY)
		private val INVALID_ESCAPE_KEYS = arrayOf(StellarisScriptAttributesKeys.INVALID_ESCAPE_KEY)
		private val BAD_CHARACTER_KEYS = arrayOf(StellarisScriptAttributesKeys.BAD_CHARACTER_KEY)
		private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
	}

	override fun getTokenHighlights(tokenType: IElementType?) = when(tokenType) {
		StellarisScriptTypes.VARIABLE_DEFINITION_SEPARATOR -> SEPARATOR_KEYS
		StellarisScriptTypes.PROPERTY_SEPARATOR -> SEPARATOR_KEYS
		StellarisScriptTypes.LEFT_BRACE -> BRACE_KEYS
		StellarisScriptTypes.RIGHT_BRACE -> BRACE_KEYS
		StellarisScriptTypes.VARIABLE_NAME_TOKEN -> VARIABLE_KEYS
		StellarisScriptTypes.KEY_TOKEN -> PROPERTY_KEY_KEYS
		StellarisScriptTypes.VARIABLE_REFERENCE_TOKEN -> VARIABLE_KEYS
		StellarisScriptTypes.BOOLEAN -> KEYWORD_KEYS
		StellarisScriptTypes.NUMBER -> NUMBER_KEYS
		StellarisScriptTypes.STRING -> STRING_KEYS
		StellarisScriptTypes.UNQUOTED_STRING -> STRING_KEYS
		StellarisScriptTypes.COMMENT -> COMMENT_KEYS
		StellarisScriptTypes.END_OF_LINE_COMMENT -> COMMENT_KEYS
		StringEscapesTokenTypes.VALID_STRING_ESCAPE_TOKEN -> VALID_ESCAPE_KEYS
		StringEscapesTokenTypes.INVALID_CHARACTER_ESCAPE_TOKEN -> INVALID_ESCAPE_KEYS
		StringEscapesTokenTypes.INVALID_UNICODE_ESCAPE_TOKEN -> INVALID_ESCAPE_KEYS
		TokenType.BAD_CHARACTER -> BAD_CHARACTER_KEYS
		else -> EMPTY_KEYS
	}

	//NOTE 不要使用封装后的Lexer，因为会报错，而且没必要
	//override fun getHighlightingLexer() = StellarisScriptHighlighterLexer()

	override fun getHighlightingLexer() = StellarisScriptLexerAdapter()
}
