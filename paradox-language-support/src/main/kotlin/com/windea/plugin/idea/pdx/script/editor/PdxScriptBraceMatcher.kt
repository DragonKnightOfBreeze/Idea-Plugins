package com.windea.plugin.idea.pdx.script.editor

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptBraceMatcher : PairedBraceMatcher {
	companion object{
		private val bracePairs = arrayOf(
			BracePair(PdxScriptTypes.LEFT_BRACE, PdxScriptTypes.RIGHT_BRACE, true),
			BracePair(PdxScriptTypes.CODE_START, PdxScriptTypes.CODE_END, true)
		)
	}

	override fun getPairs(): Array<BracePair> = bracePairs

	override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset

	override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true
}
