package com.windea.plugin.idea.pdx.localisation.editor

import com.intellij.codeInsight.editorActions.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationQuoteHandler : SimpleTokenSetQuoteHandler(PdxLocalisationTypes.RICH_TEXT, TokenType.BAD_CHARACTER)
