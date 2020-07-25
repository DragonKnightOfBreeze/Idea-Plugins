package com.windea.plugin.idea.stellaris.script.reference

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*

class StellarisScriptPropertyPsiReference(
	element: PsiElement,
	rangeInElement: TextRange
) : PsiReferenceBase<PsiElement>(element, rangeInElement), PsiPolyVariantReference {
	//去除包围的引号
	private val name = rangeInElement.substring(element.text).unquote()

	override fun resolve(): PsiElement? {
		return findScriptPropertyInProject(name,element.project)
	}

	override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
		return findAllScriptProperties(element.project).mapArray {
			PsiElementResolveResult(it)
		}
	}
}
