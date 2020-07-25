package com.windea.plugin.idea.stellaris.script.reference

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*

class StellarisScriptStringLiteralPsiReference(
	element: PsiElement,
	rangeInElement: TextRange
) : PsiReferenceBase<PsiElement>(element, rangeInElement), PsiPolyVariantReference {
	//去除包围的引号
	private val name = rangeInElement.substring(element.text).unquote()

	override fun resolve(): PsiElement? {
		return multiResolve(false).firstOrNull()?.element
	}

	override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
		//假定是localization property，然后再假定是script property
		return findLocalizationPropertiesInProject(name, element.project).ifEmpty {
			findScriptPropertiesInProject(name, element.project)
		}.mapArray { PsiElementResolveResult(it) }
	}
}
