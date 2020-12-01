package com.windea.plugin.idea.pdx.localisation.editor

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationBraceMatcher : PairedBraceMatcher {
	companion object{
		private val bracePairs = arrayOf(
			BracePair(PdxLocalisationTypes.CODE_START, PdxLocalisationTypes.CODE_END, true),
			BracePair(PdxLocalisationTypes.COLORFUL_TEXT_START, PdxLocalisationTypes.COLORFUL_TEXT_END, true),
			BracePair(PdxLocalisationTypes.ICON_START, PdxLocalisationTypes.ICON_END, true),
			BracePair(PdxLocalisationTypes.PROPERTY_REFERENCE_START, PdxLocalisationTypes.PROPERTY_REFERENCE_END, true),
			BracePair(PdxLocalisationTypes.SERIAL_NUMBER_START, PdxLocalisationTypes.SERIAL_NUMBER_END, true)
		)
	}

	override fun getPairs(): Array<BracePair> = bracePairs

	override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset

	override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true
}
