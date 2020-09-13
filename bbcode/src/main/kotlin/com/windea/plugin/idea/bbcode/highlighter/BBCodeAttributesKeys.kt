@file:Suppress("DEPRECATION")

package com.windea.plugin.idea.bbcode.highlighter

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*
import com.intellij.openapi.editor.HighlighterColors.*
import com.intellij.openapi.editor.colors.TextAttributesKey.*
import com.windea.plugin.idea.bbcode.BBCodeBundle.message
import java.awt.*

object BBCodeAttributesKeys {
	private val BBCODE_ATTRIBUTE = MARKUP_ATTRIBUTE.defaultAttributes.clone().apply{ foregroundColor = Color(0xBABABA)}
	private val BBCODE_KEYWORD = KEYWORD.defaultAttributes.clone().apply { foregroundColor = Color(0xE8BF6A) }

	@JvmField val MARKER_KEY = createTextAttributesKey(message("bbcode.syntax.marker"), BBCODE_KEYWORD)
	@JvmField val TAG_NAME_KEY = createTextAttributesKey(message("bbcode.syntax.tagName"), BBCODE_KEYWORD)
	@JvmField val ATTRIBUTE_NAME_KEY = createTextAttributesKey(message("bbcode.syntax.attributeName"),BBCODE_ATTRIBUTE)
	@JvmField val ATTRIBUTE_VALUE_KEY = createTextAttributesKey(message("bbcode.syntax.attributeValue"), STRING)
	@JvmField val TEXT_KEY = createTextAttributesKey(message("bbcode.syntax.text"), TEXT)
	@JvmField val BAD_CHARACTER_KEY = createTextAttributesKey(message("bbcode.syntax.badCharacter"), BAD_CHARACTER)
}
