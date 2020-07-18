package com.windea.plugin.idea.stellaris.localization.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationPropertyTreeElement(
	private val psiElement: StellarisLocalizationProperty?
) : PsiTreeElementBase<StellarisLocalizationProperty>(psiElement) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return mutableListOf()
	}

	override fun getPresentableText(): String? {
		return psiElement?.name
	}
}

