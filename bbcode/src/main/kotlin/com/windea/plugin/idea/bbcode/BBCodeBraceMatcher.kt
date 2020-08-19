package com.windea.plugin.idea.bbcode

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.bbcode.psi.*

class BBCodeBraceMatcher: PairedBraceMatcher {
	companion object{
		private val bracePairs = arrayOf(
			BracePair(BBCodeTypes.TAG_PREFIX_START, BBCodeTypes.TAG_SUFFIX_END, true)
		)
	}

	override fun getPairs() = bracePairs

	override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int) = openingBraceOffset

	override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?) = true
}

