package com.windea.plugin.idea.stellaris.script.editor

import com.intellij.codeInsight.editorActions.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptQuoteHandler: SimpleTokenSetQuoteHandler(StellarisScriptTypes.STRING_TOKEN, StellarisScriptTypes.QUOTED_STRING_TOKEN, TokenType.BAD_CHARACTER)
