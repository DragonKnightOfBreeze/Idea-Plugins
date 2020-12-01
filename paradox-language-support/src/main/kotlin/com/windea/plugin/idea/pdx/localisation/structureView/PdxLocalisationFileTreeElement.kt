@file:Suppress("HasPlatformType", "UNCHECKED_CAST")

package com.windea.plugin.idea.pdx.localisation.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationFileTreeElement(
	private val element: PdxLocalisationFile
) : PsiTreeElementBase<PdxLocalisationFile>(element) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return element.properties.mapTo(mutableListOf()) { PdxLocalisationPropertyTreeElement(it) }
	}

	override fun getPresentableText(): String? {
		return element.name
	}
}

