package com.windea.plugin.idea.stellaris.localization

import com.intellij.codeInsight.editorActions.*
import com.intellij.psi.*
import com.intellij.psi.TokenType.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*

class StellarisLocalizationQuoteHandler :SimpleTokenSetQuoteHandler(RICH_TEXT, BAD_CHARACTER)
