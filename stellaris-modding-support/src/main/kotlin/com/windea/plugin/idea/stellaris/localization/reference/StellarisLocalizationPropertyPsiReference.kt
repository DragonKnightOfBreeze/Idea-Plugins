package com.windea.plugin.idea.stellaris.localization.reference

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.intellij.util.*
import com.intellij.util.CommonProcessors.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import org.intellij.jflex.psi.*

class StellarisLocalizationPropertyPsiReference(
	element: StellarisLocalizationPropertyReference,
	rangeInElement: TextRange?
) : PsiReferenceBase<StellarisLocalizationPropertyReference>(element, rangeInElement), PsiPolyVariantReference {
	private val name = element.text

	override fun resolve(): PsiElement? {
		if(name == null) return null
		return multiResolve(false).firstOrNull()?.element.andPrint("resolve")
	}

	override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
		if(name == null) return ResolveResult.EMPTY_ARRAY
		return findLocalizationProperties(element.project, name).mapArray { PsiElementResolveResult(it) }.andPrint("multiResolve")
	}

	override fun getVariants(): Array<out Any> {
		println("getVariants")
		return findAllLocalizationProperties(element.project).filterNot { it.name.isNullOrEmpty() }.mapArray {
			createLookupElement(it, icon = it.getIcon(0), typeText = it.containingFile.name)
		}.andPrint("getVariants")
	}

	override fun handleElementRename(newElementName: String): PsiElement {
		return element.setName(newElementName)
	}
}

