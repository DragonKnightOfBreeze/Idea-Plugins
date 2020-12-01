package com.windea.plugin.idea.stellaris.localization.editor

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationBraceMatcher : PairedBraceMatcher {
	companion object{
		private val bracePairs = arrayOf(
			BracePair(StellarisLocalizationTypes.CODE_START, StellarisLocalizationTypes.CODE_END, true),
			BracePair(StellarisLocalizationTypes.COLORFUL_TEXT_START, StellarisLocalizationTypes.COLORFUL_TEXT_END, true),
			BracePair(StellarisLocalizationTypes.ICON_START, StellarisLocalizationTypes.ICON_END, true),
			BracePair(StellarisLocalizationTypes.PROPERTY_REFERENCE_START, StellarisLocalizationTypes.PROPERTY_REFERENCE_END, true),
			BracePair(StellarisLocalizationTypes.SERIAL_NUMBER_START, StellarisLocalizationTypes.SERIAL_NUMBER_END, true)
		)
	}

	override fun getPairs(): Array<BracePair> = bracePairs

	override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset

	override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true
}
