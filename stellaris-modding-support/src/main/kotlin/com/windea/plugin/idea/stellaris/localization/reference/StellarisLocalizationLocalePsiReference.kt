package com.windea.plugin.idea.stellaris.localization.reference

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationLocalePsiReference(
	element: StellarisLocalizationLocale,
	rangeInElement: TextRange
) : PsiReferenceBase<StellarisLocalizationLocale>(element, rangeInElement) {
	override fun resolve(): PsiElement? {
		return null
	}
}

