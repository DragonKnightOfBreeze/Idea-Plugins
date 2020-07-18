@file:Suppress("HasPlatformType", "UNCHECKED_CAST")

package com.windea.plugin.idea.stellaris.localization.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationFileTreeElement(
	private val psiElement: StellarisLocalizationFile?
) : PsiTreeElementBase<StellarisLocalizationFile>(psiElement) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		val propertyHeader = PsiTreeUtil.getChildOfType(psiElement,StellarisLocalizationPropertyHeader::class.java)
			?.let { StellarisLocalizationPropertyHeaderTreeElement(it) }
		val result =  PsiTreeUtil.getChildrenOfTypeAsList(psiElement, StellarisLocalizationProperty::class.java)
			.mapTo(mutableListOf<StructureViewTreeElement>()) { StellarisLocalizationPropertyTreeElement(it) }
		if(propertyHeader != null) result.add(0, propertyHeader)
		return result
	}

	override fun getPresentableText(): String? {
		////显示语言区域作为后缀
		//val locale = PsiTreeUtil.getChildOfType(psiElement,StellarisLocalizationPropertyHeader::class.java)?.name
		//val localeSnippet = if(locale != null) " <$locale>" else ""
		//return "${psiElement?.name}$localeSnippet"
		return psiElement?.name
	}
}

