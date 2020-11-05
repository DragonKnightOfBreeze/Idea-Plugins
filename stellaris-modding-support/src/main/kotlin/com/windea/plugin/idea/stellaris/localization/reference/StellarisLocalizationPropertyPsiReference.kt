@file:Suppress("UNCHECKED_CAST", "UnstableApiUsage", "UnstableApiUsage")

package com.windea.plugin.idea.stellaris.localization.reference

import com.intellij.codeInsight.lookup.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationPropertyPsiReference(
	element: StellarisLocalizationPropertyReference,
	rangeInElement: TextRange
) : PsiReferenceBase<StellarisLocalizationPropertyReference>(element, rangeInElement), PsiPolyVariantReference {
	private val name = rangeInElement.substring(element.text)

	override fun resolve(): PsiElement? {
		println(element.resolveScope)
		return findLocalizationProperty(name, element.project)
	}

	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		println(element.resolveScope)
		return findLocalizationProperties(name, element.project).mapArray {
			PsiElementResolveResult(it)
		}
	}

	//注意要传入elementName而非element
	override fun getVariants(): Array<out Any> {
		println(element.resolveScope)
		return findLocalizationProperties(element.project).mapArray {
			LookupElementBuilder.create(it).withIcon(it.getIcon(0)).withTypeText(it.containingFile.name).withPsiElement(it)
		}
	}
}

