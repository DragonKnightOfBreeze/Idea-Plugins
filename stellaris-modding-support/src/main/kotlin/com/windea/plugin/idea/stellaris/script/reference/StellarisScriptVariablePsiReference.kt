package com.windea.plugin.idea.stellaris.script.reference

import com.intellij.codeInsight.lookup.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*

class StellarisScriptVariablePsiReference(
	element: PsiElement,
	rangeInElement: TextRange
) : PsiReferenceBase<PsiElement>(element, rangeInElement), PsiPolyVariantReference {
	private val name = rangeInElement.substring(element.text)

	override fun resolve(): PsiElement? {
		val file = element.containingFile?:return null
		return findScriptVariableInFile(name,file) ?: findScriptProperty(name,element.project)
	}

	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		//首先尝试从当前文档中查找引用
		resolve()?.let{  return arrayOf(PsiElementResolveResult(it)) }
		//然后从全局范围中查找引用
		return findScriptVariables(name, element.project).mapArray { PsiElementResolveResult(it) }
	}

	override fun getVariants(): Array<out Any> {
		return findScriptVariables(element.project).mapArray {
			LookupElementBuilder.create(it.name!!).withIcon(it.getIcon(0)).withTypeText(it.containingFile.name).withPsiElement(it)
		}
	}
}
