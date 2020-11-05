package com.windea.plugin.idea.stellaris.localization.editor

import com.intellij.codeInsight.editorActions.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationQuoteHandler : SimpleTokenSetQuoteHandler(StellarisLocalizationTypes.RICH_TEXT, TokenType.BAD_CHARACTER)
