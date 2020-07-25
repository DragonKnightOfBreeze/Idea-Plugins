@file:Suppress("UNCHECKED_CAST", "UnstableApiUsage", "UnstableApiUsage")

package com.windea.plugin.idea.stellaris.localization.reference

import com.intellij.model.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationPropertyPsiReference(
	element: StellarisLocalizationPropertyReference,
	rangeInElement: TextRange?
) : PsiReferenceBase<StellarisLocalizationPropertyReference>(element, rangeInElement), PsiPolyVariantReference {
	private val name = rangeInElement?.substring(element.text)

	//不便于使用缓存：可能存在于当前文件中、当前项目中，甚至是外部目录

	override fun resolve(): PsiElement? {
		if(name == null) return null
		return findLocalizationPropertyInProject(name, element.project)
	}

	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		if(name == null) return ResolveResult.EMPTY_ARRAY
		return findLocalizationPropertiesInProject(name, element.project).mapArray {
			PsiElementResolveResult(it)
		}
	}

	//注意要传入elementName而非element
	override fun getVariants(): Array<out Any> {
		return findAllLocalizationPropertiesInProject(element.project).mapArray {
			createLookupElement(it.name!!, icon = it.getIcon(0), typeText = it.containingFile.name)
		}
	}
}

