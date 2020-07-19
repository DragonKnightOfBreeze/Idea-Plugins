package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*

class StellarisLocalizationBraceMatcher : PairedBraceMatcher {
	companion object{
		private val bracePairs = arrayOf(
			BracePair(LEFT_QUOTE, RIGHT_QUOTE, true),
			BracePair(CODE_START, CODE_END, true),
			BracePair(COLORFUL_TEXT_START, COLORFUL_TEXT_END, true),
			BracePair(ICON_START, ICON_END, true),
			BracePair(PROPERTY_REFERENCE_START, PROPERTY_REFERENCE_END, true),
			BracePair(SERIAL_NUMBER_START, SERIAL_NUMBER_END, true)
		)
	}

	override fun getPairs(): Array<BracePair> = bracePairs

	override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset

	override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true
}

