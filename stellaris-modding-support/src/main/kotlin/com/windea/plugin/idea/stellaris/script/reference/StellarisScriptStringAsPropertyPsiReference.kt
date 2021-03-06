package com.windea.plugin.idea.stellaris.script.reference

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.settings.*

class StellarisScriptStringAsPropertyPsiReference(
	element: StellarisScriptString,
	rangeInElement: TextRange
) : PsiReferenceBase<StellarisScriptString>(element, rangeInElement), PsiPolyVariantReference {
	//去除包围的引号
	private val name = element.text.unquote()
	private val project = element.project
	private val state get() = StellarisSettingsState.getInstance()
	
	//不会随之重命名，因为不能保证引用关系正确
	override fun handleElementRename(newElementName: String): PsiElement {
		return element
	}
	
	override fun resolve(): PsiElement? {
		if(state.resolveReferences) {
			return findScriptProperty(name, project)
			       ?: findLocalizationProperty(name, project, inferredStellarisLocale)
			       ?: findLocalizationProperty(name, project)
		}
		return null
	}
	
	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		if(StellarisSettingsState.getInstance().resolveReferences) {
			return findScriptProperties(name, project).ifEmpty {
				findLocalizationProperties(name, project)
			}.mapArray { PsiElementResolveResult(it) }
		}
		return ResolveResult.EMPTY_ARRAY
	}
}

