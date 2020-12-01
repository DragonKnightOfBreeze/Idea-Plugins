package com.windea.plugin.idea.pdx.script.editor

import com.intellij.codeInsight.editorActions.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptQuoteHandler: SimpleTokenSetQuoteHandler(PdxScriptTypes.STRING_TOKEN, PdxScriptTypes.QUOTED_STRING_TOKEN, TokenType.BAD_CHARACTER)
