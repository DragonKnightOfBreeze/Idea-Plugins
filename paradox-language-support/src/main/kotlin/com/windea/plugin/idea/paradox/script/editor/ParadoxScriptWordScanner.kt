package com.windea.plugin.idea.paradox.script.editor

import com.intellij.lang.cacheBuilder.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.paradox.script.psi.*

class ParadoxScriptWordScanner: DefaultWordsScanner(
	ParadoxScriptLexerAdapter(),
	TokenSet.create(ParadoxScriptTypes.VARIABLE_NAME_ID, ParadoxScriptTypes.VARIABLE_REFERENCE_ID, ParadoxScriptTypes.PROPERTY_KEY_ID),
	TokenSet.create(ParadoxScriptTypes.COMMENT, ParadoxScriptTypes.END_OF_LINE_COMMENT),
	TokenSet.create(ParadoxScriptTypes.QUOTED_STRING_TOKEN, ParadoxScriptTypes.STRING_TOKEN)
){
	init {
		setMayHaveFileRefsInLiterals(true)
	}
}
