package com.windea.plugin.idea.stellaris.script.highlighter

import com.intellij.lexer.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

/**
 * 语法高亮的词法器，用于特别高亮转义字符。
 */
class StellarisScriptHighlighterLexer : LayeredLexer(StellarisScriptLexerAdapter()) {
	init {
		//认为必须在有引号括起的字符串里，才能使用转义字符
		registerSelfStoppingLayer(
			StringLiteralLexer('"', STRING_TOKEN, true, null),
			arrayOf(STRING_TOKEN),
			arrayOf()
		)
	}
}
