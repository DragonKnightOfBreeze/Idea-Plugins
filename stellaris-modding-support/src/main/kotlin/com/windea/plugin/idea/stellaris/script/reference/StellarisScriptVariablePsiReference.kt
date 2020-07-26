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
		return findScriptVariableDefinitionInFile(name,element.containingFile)
	}

	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		return findScriptVariableDefinitions(name, element.project,element.resolveScope).mapArray {
			PsiElementResolveResult(it)
		}
	}

	//用于代码补全
	//TODO 认为只有在特定的作用域内才提示变量
	override fun getVariants(): Array<out Any> {
		return findAllScriptVariableDefinitions(element.project,element.resolveScope).mapArray {
			LookupElementBuilder.create(it.name!!).withIcon(it.getIcon(0)).withTypeText(it.containingFile.name)
				.withPsiElement(it)
		}
	}
}
