package com.windea.plugin.idea.pdx.localisation.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationPropertyTreeElement(
	private val element: PdxLocalisationProperty
) : PsiTreeElementBase<PdxLocalisationProperty>(element) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return mutableListOf()
	}

	override fun getPresentableText(): String? {
		return element.name
	}
}

