package com.windea.plugin.idea.stellaris.script.reference

import com.intellij.codeInsight.lookup.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*

class StellarisScriptVariablePsiReference(
	element: PsiElement,
	rangeInElement: TextRange
) : PsiReferenceBase<PsiElement>(element, rangeInElement), PsiPolyVariantReference {
	private val key = rangeInElement.substring(element.text)

	override fun resolve(): PsiElement? {
		val element = multiResolve(false).firstOrNull()?.element
		return element
	}

	override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
		val mapArray: Array<ResolveResult> = findScriptVariableDefinitions(element.project, key).mapArray {
			PsiElementResolveResult(it)
		}
		return mapArray
	}

	//用于代码补全，不要随便实现，以免Editor发生卡顿！！！
	//override fun getVariants(): Array<Any> {
	//	val mapArray: Array<Any> = findAllScriptVariableDefinitions(element.project).filterNot { it.name.isNullOrEmpty() }.mapArray {
	//		createLookupElement(it,icon = it.getIcon(0),typeText = it.containingFile.name)
	//	}
	//	return mapArray
	//}
}
