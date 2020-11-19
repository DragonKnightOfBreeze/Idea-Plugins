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
	private val state = StellarisSettingsState.getInstance()
	
	override fun resolve(): PsiElement? {
		if(state.resolveInternalReferences) {
			return findScriptProperty(name, element.project) ?: findLocalizationProperty(name, element.project)
		}
		return null
	}
	
	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		if(StellarisSettingsState.getInstance().resolveInternalReferences) {
			return findScriptProperties(name, element.project).ifEmpty {
				//尝试根据用户的语言区域过滤查询结果
				findLocalizationProperties(name, project).let { properties ->
					properties.filter { it.stellarisLocale == inferedStellarisLocale }.ifEmpty { properties }
				}
			}.mapArray { PsiElementResolveResult(it) }
		}
		return ResolveResult.EMPTY_ARRAY
	}
}

