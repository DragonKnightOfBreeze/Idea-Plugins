package com.windea.plugin.idea.stellaris.localization.reference

import com.intellij.codeInsight.lookup.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*

class StellarisLocalizationPropertyPsiReference(
	element: PsiElement,
	rangeInElement: TextRange
) : PsiReferenceBase<PsiElement>(element, rangeInElement), PsiPolyVariantReference {
	private val name = rangeInElement.substring(element.text)

	override fun resolve(): PsiElement? {
		return multiResolve(false).firstOrNull()?.element
	}

	override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
		return findLocalizationProperties(element.project, name).mapArray { PsiElementResolveResult(it) }
	}

	//用于代码补全，不要随便实现，以免Editor发生卡顿！！！
	//override fun getVariants(): Array<Any> {
	//	return findAllLocalizationProperties(element.project).filterNot { it.name.isNullOrEmpty() }.mapArray {
	//		createLookupElement(it,icon=it.getIcon(0),typeText = it.containingFile.name)
	//	}
	//}
}

