package com.windea.plugin.idea.stellaris.script.navigation

import com.intellij.lang.cacheBuilder.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

class StellarisScriptWordScanner: DefaultWordsScanner(
	StellarisScriptLexerAdapter(),
	TokenSet.create(KEY_TOKEN, VARIABLE_NAME_TOKEN, PROPERTY_KEY, VARIABLE_REFERENCE),
	TokenSet.create(COMMENT, END_OF_LINE_COMMENT),
	TokenSet.create(STRING, UNQUOTED_STRING)
){
	init {
		setMayHaveFileRefsInLiterals(true)
	}
}

