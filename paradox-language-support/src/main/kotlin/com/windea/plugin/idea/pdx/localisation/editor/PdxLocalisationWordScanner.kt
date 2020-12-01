package com.windea.plugin.idea.pdx.localisation.editor

import com.intellij.lang.cacheBuilder.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationWordScanner: DefaultWordsScanner(
	PdxLocalisationLexerAdapter(),
	TokenSet.create(PdxLocalisationTypes.PROPERTY_KEY_ID),
	TokenSet.create(PdxLocalisationTypes.COMMENT, PdxLocalisationTypes.ROOT_COMMENT, PdxLocalisationTypes.END_OF_LINE_COMMENT),
	TokenSet.create(PdxLocalisationTypes.STRING_TOKEN)
){
	init {
		setMayHaveFileRefsInLiterals(true)
	}
}
