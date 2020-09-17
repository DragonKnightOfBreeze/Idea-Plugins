package com.windea.plugin.idea.sbtext.highlighter

import com.intellij.openapi.editor.colors.*
import com.intellij.openapi.fileTypes.*
import com.intellij.psi.TokenType.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.sbtext.psi.*
import com.windea.plugin.idea.sbtext.psi.SbTextTypes.*

class SbTextSyntaxHighlighter: SyntaxHighlighterBase(){
	companion object{
		private val MARKER_KEYS = arrayOf(SbTextAttributesKeys.MARKER_KEY)
		private val COLOR_CODE_KEYS = arrayOf(SbTextAttributesKeys.COLOR_CODE_KEY)
		private val TEXT_KEYS = arrayOf(SbTextAttributesKeys.TEXT_KEY)
		private val VALID_ESCAPE_KEYS = arrayOf(SbTextAttributesKeys.VALID_ESCAPE_KEY)
		private val INVALID_ESCAPE_KEYS = arrayOf(SbTextAttributesKeys.INVALID_ESCAPE_KEY)
		private val BAD_CHARACTER_KEYS = arrayOf(SbTextAttributesKeys.BAD_CHARACTER_KEY)
		private val EMPTY_KEYS = TextAttributesKey.EMPTY_ARRAY
	}

	override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
		return when(tokenType){
			COLOR_MARKER_START, COLOR_MARKER_END, COLOR_RESET_MARKER_TOKEN -> MARKER_KEYS
			COLOR_CODE -> COLOR_CODE_KEYS
			TEXT_TOKEN -> TEXT_KEYS
			VALID_ESCAPE_TOKEN -> VALID_ESCAPE_KEYS
			INVALID_ESCAPE_TOKEN -> INVALID_ESCAPE_KEYS
			BAD_CHARACTER -> BAD_CHARACTER_KEYS
			else -> EMPTY_KEYS
		}
	}

	override fun getHighlightingLexer() = SbTextLexerAdapter()
}

