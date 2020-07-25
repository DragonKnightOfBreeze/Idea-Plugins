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

	override fun resolve(): PsiElement? {
		findLocalizationPropertyInProject(name,element.project)?.let{ return it }
		findScriptPropertyInProject(name,element.project)?.let{return it}
		return null
	}

	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		//假定是localization property，然后再假定是script property
		return findLocalizationPropertiesInProject(name, element.project)?.ifEmpty {
			findScriptPropertiesInProject(name, element.project)
		}?.mapArray { PsiElementResolveResult(it) }.orEmpty()
	}
}
