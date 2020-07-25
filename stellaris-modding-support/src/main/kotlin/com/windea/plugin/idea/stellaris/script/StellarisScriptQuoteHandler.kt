package com.windea.plugin.idea.stellaris.script

import com.intellij.codeInsight.editorActions.*
import com.intellij.psi.TokenType.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

class StellarisScriptQuoteHandler:SimpleTokenSetQuoteHandler(UNQUOTED_STRING_TOKEN, STRING_TOKEN,BAD_CHARACTER)
