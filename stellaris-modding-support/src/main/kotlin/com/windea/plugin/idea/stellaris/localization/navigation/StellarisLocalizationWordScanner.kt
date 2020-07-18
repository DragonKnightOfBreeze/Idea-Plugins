package com.windea.plugin.idea.stellaris.localization.navigation

import com.intellij.lang.cacheBuilder.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*

class StellarisLocalizationWordScanner: DefaultWordsScanner(
	StellarisLocalizationLexerAdapter(),
	TokenSet.create(PROPERTY_KEY),
	TokenSet.create(COMMENT, ROOT_COMMENT),
	TokenSet.create(PROPERTY_VALUE)
){
	init {
		setMayHaveFileRefsInLiterals(true)
	}
}

