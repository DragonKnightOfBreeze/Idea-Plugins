package com.windea.plugin.idea.pdx.script.editor

import com.intellij.lang.cacheBuilder.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptWordScanner: DefaultWordsScanner(
	PdxScriptLexerAdapter(),
	TokenSet.create(PdxScriptTypes.VARIABLE_NAME_ID, PdxScriptTypes.VARIABLE_REFERENCE_ID, PdxScriptTypes.PROPERTY_KEY_ID),
	TokenSet.create(PdxScriptTypes.COMMENT, PdxScriptTypes.END_OF_LINE_COMMENT),
	TokenSet.create(PdxScriptTypes.QUOTED_STRING_TOKEN, PdxScriptTypes.STRING_TOKEN)
){
	init {
		setMayHaveFileRefsInLiterals(true)
	}
}
