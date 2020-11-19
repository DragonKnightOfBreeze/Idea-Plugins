package com.windea.plugin.idea.stellaris.script.reference

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*

class StellarisScriptPropertyPsiReference(
	element: PsiElement,
	rangeInElement: TextRange
) : PsiReferenceBase<PsiElement>(element, rangeInElement), PsiPolyVariantReference {
	//去除包围的引号
	private val name = element.text.unquote()

	//不解析引用
	
	override fun resolve(): PsiElement? {
		return null
	}

	override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
		return ResolveResult.EMPTY_ARRAY
	}
}
