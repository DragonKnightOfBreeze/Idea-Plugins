package com.windea.plugin.idea.stellaris.script.reference

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.settings.*

class StellarisScriptStringPsiReference1(
	element: StellarisScriptString,
	rangeInElement: TextRange
) : PsiReferenceBase<StellarisScriptString>(element, rangeInElement), PsiPolyVariantReference {
	//去除包围的引号
	private val name = element.text.unquote()
	
	var resolveAsLocalizationProperty = true
	
	override fun resolve(): StellarisScriptProperty? {
		if(StellarisSettingsState.getInstance().resolveInternalReferences) {
			return findScriptProperty(name, element.project)
		}
		return null
	}
	
	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		if(StellarisSettingsState.getInstance().resolveInternalReferences) {
			return findScriptProperties(name, element.project).mapArray {
				PsiElementResolveResult(it)
			}
		}
		return ResolveResult.EMPTY_ARRAY
	}
}

