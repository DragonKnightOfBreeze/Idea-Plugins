package com.windea.plugin.idea.stellaris.localization.navigation

import com.intellij.lang.cacheBuilder.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*

class StellarisLocalizationWordScanner: DefaultWordsScanner(
	StellarisLocalizationLexerAdapter(),
	TokenSet.create(KEY_TOKEN),
	TokenSet.create(COMMENT, ROOT_COMMENT,END_OF_LINE_COMMENT),
	TokenSet.create(VALUE_TOKEN)
){
	init {
		setMayHaveFileRefsInLiterals(true)
	}
}

