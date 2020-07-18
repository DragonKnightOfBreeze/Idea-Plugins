package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationPropertyReferenceMarkerMatcher : PairedBraceMatcher {
	companion object{
		private val bracePairs = arrayOf(BracePair(StellarisLocalizationTypes.PROPERTY_REFERENCE_START, StellarisLocalizationTypes.PROPERTY_REFERENCE_END, true))
	}

	override fun getPairs(): Array<BracePair> = bracePairs

	override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset

	override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true
}
