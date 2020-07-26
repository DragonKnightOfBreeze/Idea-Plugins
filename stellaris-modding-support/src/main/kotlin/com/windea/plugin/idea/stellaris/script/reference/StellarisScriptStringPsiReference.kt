package com.windea.plugin.idea.stellaris.script.reference

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*

class StellarisScriptStringPsiReference(
	element: PsiElement,
	rangeInElement: TextRange
) : PsiReferenceBase<PsiElement>(element, rangeInElement), PsiPolyVariantReference {
	//去除包围的引号
	private val name = element.text.unquote()

	var resolveAsLocalizationProperty = true

	override fun resolve(): PsiElement? {
		findScriptProperty(name, element.project)?.let { return it }
		findLocalizationProperty(name, element.project)?.let { return it }
		return null
	}

	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		//假定是script property，然后再假定是localization property，后者可以对应多个
		findScriptProperty(name, element.project)?.let {
			resolveAsLocalizationProperty = false
			return arrayOf(PsiElementResolveResult(it))
		}
		findLocalizationProperties(name, element.project).mapArray {
			PsiElementResolveResult(it)
		}.let { if(it.isNotEmpty()) return it }
		return ResolveResult.EMPTY_ARRAY
	}
}
