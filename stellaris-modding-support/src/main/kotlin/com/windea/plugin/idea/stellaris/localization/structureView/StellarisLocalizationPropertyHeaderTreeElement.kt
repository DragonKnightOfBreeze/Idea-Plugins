package com.windea.plugin.idea.stellaris.localization.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationPropertyHeaderTreeElement(
	private val psiElement: StellarisLocalizationPropertyHeader?
) : PsiTreeElementBase<StellarisLocalizationPropertyHeader>(psiElement) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return mutableListOf()
	}

	override fun getPresentableText(): String? {
		return psiElement?.name
	}
}
