package com.windea.plugin.idea.sbtext.psi

import com.intellij.lexer.*

class SbTextLexerAdapter: FlexAdapter(SbTextLexer(null))
