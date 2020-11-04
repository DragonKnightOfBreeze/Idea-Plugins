package com.windea.plugin.idea.stellaris.script.navigation

import com.intellij.lang.cacheBuilder.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

class StellarisScriptWordScanner: DefaultWordsScanner(
	StellarisScriptLexerAdapter(),
	TokenSet.create(VARIABLE_NAME_ID,VARIABLE_REFERENCE_ID,PROPERTY_KEY_ID),
	TokenSet.create(COMMENT, END_OF_LINE_COMMENT),
	TokenSet.create(QUOTED_STRING_TOKEN, STRING_TOKEN)
){
	init {
		setMayHaveFileRefsInLiterals(true)
	}
}

