package com.windea.plugin.idea.stellaris.script.highlighter

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*
import com.intellij.openapi.editor.HighlighterColors.*
import com.intellij.openapi.editor.colors.*
import com.intellij.openapi.editor.colors.TextAttributesKey.*
import com.intellij.openapi.fileTypes.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.psi.*

@ExtensionPoint
class StellarisScriptSyntaxHighlighter : SyntaxHighlighterBase() {
	companion object {
		val SEPARATOR_KEY = createTextAttributesKey(message("stellaris.script.syntax.separator"), OPERATION_SIGN)
		val BRACES_KEY = createTextAttributesKey(message("stellaris.script.syntax.braces"), BRACES)
		val VARIABLE_KEY = createTextAttributesKey(message("stellaris.script.syntax.variable"), INSTANCE_FIELD)
		val PROPERTY_KEY_KEY = createTextAttributesKey(message("stellaris.script.syntax.propertyKey"), KEYWORD)
		val KEYWORD_KEY = createTextAttributesKey(message("stellaris.script.syntax.keyword"), KEYWORD)
		val NUMBER_KEY = createTextAttributesKey(message("stellaris.script.syntax.number"), NUMBER)
		val STRING_KEY = createTextAttributesKey(message("stellaris.script.syntax.string"), STRING)
		val COMMENT_KEY = createTextAttributesKey(message("stellaris.script.syntax.comment"), LINE_COMMENT)
		val VALID_ESCAPE_KEY = createTextAttributesKey(message("stellaris.script.syntax.validEscape"), VALID_STRING_ESCAPE)
		val INVALID_ESCAPE_KEY = createTextAttributesKey(message("stellaris.script.syntax.invalidEscape"), INVALID_STRING_ESCAPE)
		val BAD_CHARACTER_KEY = createTextAttributesKey(message("stellaris.script.syntax.badCharacter"), BAD_CHARACTER)

		private val SEPARATOR_KEYS = arrayOf(SEPARATOR_KEY)
		private val BRACE_KEYS = arrayOf(BRACES_KEY)
		private val VARIABLE_KEYS = arrayOf(VARIABLE_KEY)
		private val PROPERTY_KEY_KEYS = arrayOf(PROPERTY_KEY_KEY)
		private val KEYWORD_KEYS = arrayOf(KEYWORD_KEY)
		private val NUMBER_KEYS = arrayOf(NUMBER_KEY)
		private val STRING_KEYS = arrayOf(STRING_KEY)
		private val COMMENT_KEYS = arrayOf(COMMENT_KEY)
		private val VALID_ESCAPE_KEYS = arrayOf(VALID_ESCAPE_KEY)
		private val INVALID_ESCAPE_KEYS = arrayOf(INVALID_ESCAPE_KEY)
		private val BAD_CHARACTER_KEYS = arrayOf(BAD_CHARACTER_KEY)
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

	override fun getHighlightingLexer() = StellarisScriptHighlighterLexer()
}
