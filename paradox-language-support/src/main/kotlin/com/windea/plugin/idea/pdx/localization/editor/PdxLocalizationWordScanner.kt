package com.windea.plugin.idea.stellaris.localization.editor

import com.intellij.lang.cacheBuilder.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationWordScanner: DefaultWordsScanner(
	StellarisLocalizationLexerAdapter(),
	TokenSet.create(StellarisLocalizationTypes.PROPERTY_KEY_ID),
	TokenSet.create(StellarisLocalizationTypes.COMMENT, StellarisLocalizationTypes.ROOT_COMMENT, StellarisLocalizationTypes.END_OF_LINE_COMMENT),
	TokenSet.create(StellarisLocalizationTypes.STRING_TOKEN)
){
	init {
		setMayHaveFileRefsInLiterals(true)
	}
}
