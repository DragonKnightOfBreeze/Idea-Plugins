package com.windea.plugin.idea.stellaris.localization.reference

import com.intellij.model.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.domain.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationLocalePsiReference(
	element: StellarisLocalizationLocale,
	rangeInElement: TextRange?
) : PsiReferenceBase<StellarisLocalizationLocale>(element, rangeInElement) {
	//companion object {
	//	val lookupElements = StellarisLocale.values().mapArray { locale ->
	//		createLookupElement(locale.key, tailText = locale.description)
	//	}
	//}

	//不解析时间区域的引用：没有太大的意义，并且性能可能极低

	override fun resolve(): PsiElement? {
		return null
	}

	//override fun getVariants(): Array<out Any> {
	//	return lookupElements.andPrint("getVariants")
	//}
}
