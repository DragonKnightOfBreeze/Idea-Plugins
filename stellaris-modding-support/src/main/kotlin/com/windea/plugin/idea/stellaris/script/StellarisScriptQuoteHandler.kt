package com.windea.plugin.idea.stellaris.script

import com.intellij.codeInsight.editorActions.*
import com.intellij.psi.TokenType.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

class StellarisScriptQuoteHandler:SimpleTokenSetQuoteHandler(STRING_TOKEN, QUOTED_STRING_TOKEN,BAD_CHARACTER)
