package com.windea.plugin.idea.stellaris.localization.highlighter

import com.intellij.lexer.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*

/**
 * 语法高亮的词法器，用于特别高亮转义字符。
 */
class StellarisLocalizationHighlighterLexer : LayeredLexer(StellarisLocalizationLexerAdapter()) {
	init {
		registerSelfStoppingLayer(
			StringLiteralLexer('"', VALUE_TOKEN, false, "[£§$%"),
			arrayOf(VALUE_TOKEN),
			arrayOf(RIGHT_QUOTE, ICON_START, CODE_START, COLORFUL_TEXT_START, PROPERTY_REFERENCE_START, SERIAL_NUMBER_START)
		)
	}
}

