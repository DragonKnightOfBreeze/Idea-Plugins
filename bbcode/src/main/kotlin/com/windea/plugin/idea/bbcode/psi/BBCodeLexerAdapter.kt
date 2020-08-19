package com.windea.plugin.idea.bbcode.psi

import com.intellij.lexer.*

class BBCodeLexerAdapter: FlexAdapter(BBCodeLexer(null))

