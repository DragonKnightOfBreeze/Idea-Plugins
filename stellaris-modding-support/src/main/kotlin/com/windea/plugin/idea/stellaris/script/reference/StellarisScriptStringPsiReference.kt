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
		findLocalizationProperty(name, element.project, element.resolveScope)?.let { return it }
		findScriptProperty(name, element.project,element.resolveScope)?.let { return it }
		return null
	}

	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		//假定是localization property，然后再假定是script property，前者可以有多个
		findLocalizationProperties(name, element.project, element.resolveScope).mapArray {
			PsiElementResolveResult(it)
		}.let { if(it.isNotEmpty()) return it }
		findScriptProperty(name, element.project, element.resolveScope)?.toSingletonArray().also {
			resolveAsLocalizationProperty = false
		}
		return ResolveResult.EMPTY_ARRAY
	}
}
