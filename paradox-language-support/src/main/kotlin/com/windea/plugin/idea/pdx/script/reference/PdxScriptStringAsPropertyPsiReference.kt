package com.windea.plugin.idea.pdx.script.reference

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.script.psi.*
import com.windea.plugin.idea.pdx.settings.*

class PdxScriptStringAsPropertyPsiReference(
	element: PdxScriptString,
	rangeInElement: TextRange
) : PsiReferenceBase<PdxScriptString>(element, rangeInElement), PsiPolyVariantReference {
	//去除包围的引号
	private val name = element.text.unquote()
	private val project = element.project
	private val state = PdxSettingsState.getInstance()
	
	//不会随之重命名，因为不能保证引用关系正确
	override fun handleElementRename(newElementName: String): PsiElement {
		return element
	}
	
	override fun resolve(): PsiElement? {
		if(state.resolveInternalReferences) {
			return findScriptProperty(name, project)
			       ?: findLocalisationProperty(name, project, inferredPdxLocale)
			       ?: findLocalisationProperty(name, project)
		}
		return null
	}
	
	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		if(PdxSettingsState.getInstance().resolveInternalReferences) {
			return findScriptProperties(name, project).ifEmpty {
				findLocalisationProperties(name, project)
			}.mapArray { PsiElementResolveResult(it) }
		}
		return ResolveResult.EMPTY_ARRAY
	}
}

