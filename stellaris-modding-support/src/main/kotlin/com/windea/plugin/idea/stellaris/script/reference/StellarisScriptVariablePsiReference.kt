package com.windea.plugin.idea.stellaris.script.reference

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

	override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
		return findScriptVariableDefinitions(element.project, name).mapArray {
			PsiElementResolveResult(it)
		}
	}

	//用于代码补全
	override fun getVariants(): Array<Any> {
		return findAllScriptVariableDefinitions(element.project).mapArray {
			createLookupElement(it.name!!,icon = it.getIcon(0),typeText = it.containingFile.name)
		}
	}
}
