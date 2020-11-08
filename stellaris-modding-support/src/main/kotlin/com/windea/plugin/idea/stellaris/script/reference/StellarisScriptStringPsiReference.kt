package com.windea.plugin.idea.stellaris.script.reference

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.settings.*

class StellarisScriptStringPsiReference(
	element: PsiElement,
	rangeInElement: TextRange
) : PsiReferenceBase<PsiElement>(element, rangeInElement), PsiPolyVariantReference {
	//去除包围的引号
	private val name = element.text.unquote()

	var resolveAsLocalizationProperty = true

	override fun resolve(): PsiElement? {
		if(StellarisSettingsState.getInstance().resolveInternalReferences) {
			//假定是script property，然后再假定是localization property
			val scriptProperty = findScriptProperty(name, element.project)
			if(scriptProperty != null) return scriptProperty

			val localizationProperty = findLocalizationProperty(name, element.project)
			if(localizationProperty != null) return localizationProperty
		}
		return null
	}

	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		//假定是script property，然后再假定是localization property
		//resolveAsLocalizationProperty = false
		if(StellarisSettingsState.getInstance().resolveInternalReferences) {
			val scriptProperties = findScriptProperties(name, element.project).mapArray { PsiElementResolveResult(it) }
			if(scriptProperties.isNotEmpty()) {
				resolveAsLocalizationProperty = false
				return scriptProperties
			}

			val localizationProeprties = findLocalizationProperties(name, element.project).mapArray { PsiElementResolveResult(it) }
			if(localizationProeprties.isNotEmpty()) {
				return localizationProeprties
			}
		}

		return ResolveResult.EMPTY_ARRAY
	}
}
