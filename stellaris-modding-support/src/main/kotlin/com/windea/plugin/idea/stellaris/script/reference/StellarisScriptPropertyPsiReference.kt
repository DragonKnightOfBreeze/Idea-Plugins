package com.windea.plugin.idea.stellaris.script.reference

import com.intellij.codeInsight.lookup.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*

class StellarisScriptPropertyPsiReference(
	element: PsiElement,
	rangeInElement: TextRange
) : PsiReferenceBase<PsiElement>(element, rangeInElement), PsiPolyVariantReference {
	//去除包围的引号
	private val key = rangeInElement.substring(element.text).unquote()

	override fun resolve(): PsiElement? {
		return multiResolve(false).firstOrNull()?.element
	}

	override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
		return findScriptProperties(element.project, key).mapArray { PsiElementResolveResult(it) }
	}
}
