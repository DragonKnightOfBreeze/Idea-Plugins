package com.windea.plugin.idea.stellaris.script.editor

import com.intellij.lang.cacheBuilder.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptWordScanner: DefaultWordsScanner(
	StellarisScriptLexerAdapter(),
	TokenSet.create(StellarisScriptTypes.VARIABLE_NAME_ID, StellarisScriptTypes.VARIABLE_REFERENCE_ID, StellarisScriptTypes.PROPERTY_KEY_ID),
	TokenSet.create(StellarisScriptTypes.COMMENT, StellarisScriptTypes.END_OF_LINE_COMMENT),
	TokenSet.create(StellarisScriptTypes.QUOTED_STRING_TOKEN, StellarisScriptTypes.STRING_TOKEN)
){
	init {
		setMayHaveFileRefsInLiterals(true)
	}
}
