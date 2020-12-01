package com.windea.plugin.idea.paradox.localisation.editor

import com.intellij.lang.cacheBuilder.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.paradox.localisation.psi.*

class ParadoxLocalisationWordScanner: DefaultWordsScanner(
	ParadoxLocalisationLexerAdapter(),
	TokenSet.create(ParadoxLocalisationTypes.PROPERTY_KEY_ID),
	TokenSet.create(ParadoxLocalisationTypes.COMMENT, ParadoxLocalisationTypes.ROOT_COMMENT, ParadoxLocalisationTypes.END_OF_LINE_COMMENT),
	TokenSet.create(ParadoxLocalisationTypes.STRING_TOKEN)
){
	init {
		setMayHaveFileRefsInLiterals(true)
	}
}
