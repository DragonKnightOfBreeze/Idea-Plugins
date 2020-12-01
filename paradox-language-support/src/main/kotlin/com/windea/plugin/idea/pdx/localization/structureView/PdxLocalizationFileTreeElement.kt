@file:Suppress("HasPlatformType", "UNCHECKED_CAST")

package com.windea.plugin.idea.stellaris.localization.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationFileTreeElement(
	private val element: StellarisLocalizationFile
) : PsiTreeElementBase<StellarisLocalizationFile>(element) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return element.properties.mapTo(mutableListOf()) { StellarisLocalizationPropertyTreeElement(it) }
	}

	override fun getPresentableText(): String? {
		return element.name
	}
}

