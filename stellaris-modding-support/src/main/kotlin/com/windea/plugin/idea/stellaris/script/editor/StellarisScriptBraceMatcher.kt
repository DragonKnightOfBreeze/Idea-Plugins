package com.windea.plugin.idea.stellaris.script.editor

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptBraceMatcher : PairedBraceMatcher {
	companion object{
		private val bracePairs = arrayOf(BracePair(StellarisScriptTypes.LEFT_BRACE, StellarisScriptTypes.RIGHT_BRACE, true))
	}

	override fun getPairs(): Array<BracePair> = bracePairs

	override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset

	override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true
}
