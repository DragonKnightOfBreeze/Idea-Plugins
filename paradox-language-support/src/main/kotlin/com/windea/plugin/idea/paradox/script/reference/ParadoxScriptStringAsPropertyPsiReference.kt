package com.windea.plugin.idea.paradox.script.reference

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.script.psi.*
import com.windea.plugin.idea.paradox.settings.*

class ParadoxScriptStringAsPropertyPsiReference(
	element: ParadoxScriptString,
	rangeInElement: TextRange
) : PsiReferenceBase<ParadoxScriptString>(element, rangeInElement), PsiPolyVariantReference {
	//去除包围的引号
	private val name = element.text.unquote()
	private val project = element.project
	private val state = ParadoxSettingsState.getInstance()
	private val scope = element.resolveScope
	
	//不会随之重命名，因为不能保证引用关系正确
	override fun handleElementRename(newElementName: String): PsiElement {
		return element
	}
	
	override fun resolve(): PsiElement? {
		if(state.resolveReferences) {
			return findScriptProperty(name, project, scope)
			       ?: findLocalisationProperty(name, project, inferredParadoxLocale, scope)
			       ?: findLocalisationProperty(name, project,null,scope)
		}
		return null
	}
	
	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		if(ParadoxSettingsState.getInstance().resolveReferences) {
			return findScriptProperties(name, project,scope).ifEmpty {
				findLocalisationProperties(name, project,null,scope)
			}.mapArray { PsiElementResolveResult(it) }
		}
		return ResolveResult.EMPTY_ARRAY
	}
}

