@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.bbcode.highlighter

import com.intellij.lexer.*
import com.intellij.openapi.editor.colors.*
import com.intellij.openapi.fileTypes.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.bbcode.psi.*
import com.windea.plugin.idea.bbcode.psi.BBCodeTypes.*

class BBCodeSyntaxHighlighter:SyntaxHighlighterBase() {
	companion object{
		private val MARKER_KEYS = arrayOf(BBCodeAttributesKeys.MARKER_KEY)
		private val TAG_NAME_KEYS = arrayOf(BBCodeAttributesKeys.TAG_NAME_KEY)
		private val ATTRIBUTE_NAME_KEYS = arrayOf(BBCodeAttributesKeys.ATTRIBUTE_NAME_KEY)
		private val ATTRIBUTE_VALUE_KEYS = arrayOf(BBCodeAttributesKeys.ATTRIBUTE_VALUE_KEY)
		private val TEXT_KEYS = arrayOf(BBCodeAttributesKeys.TEXT_KEY)
		private val BAD_CHARACTER_KEYS = arrayOf(BBCodeAttributesKeys.BAD_CHARACTER_KEY)
		private val EMPTY_KEYS = TextAttributesKey.EMPTY_ARRAY
	}

	override fun getTokenHighlights(tokenType: IElementType?) = when(tokenType){
		TAG_PREFIX_START, TAG_PREFIX_END, TAG_SUFFIX_START, TAG_SUFFIX_END -> MARKER_KEYS
		TAG_NAME -> TAG_NAME_KEYS
		ATTRIBUTE_NAME -> ATTRIBUTE_NAME_KEYS
		EQUAL_SIGN -> ATTRIBUTE_VALUE_KEYS
		ATTRIBUTE_VALUE -> ATTRIBUTE_VALUE_KEYS
		TEXT_TOKEN -> TEXT_KEYS
		TokenType.BAD_CHARACTER-> BAD_CHARACTER_KEYS
		else -> EMPTY_KEYS
	}

	override fun getHighlightingLexer() = BBCodeLexerAdapter()
}
