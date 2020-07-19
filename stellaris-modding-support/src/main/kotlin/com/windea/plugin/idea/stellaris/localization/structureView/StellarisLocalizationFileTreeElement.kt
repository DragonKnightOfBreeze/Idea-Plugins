@file:Suppress("HasPlatformType", "UNCHECKED_CAST")

package com.windea.plugin.idea.stellaris.localization.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationFileTreeElement(
	private val element: StellarisLocalizationFile?
) : PsiTreeElementBase<StellarisLocalizationFile>(element) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return element?.properties.orEmpty().mapTo(mutableListOf()) { StellarisLocalizationPropertyTreeElement(it) }
	}

	override fun getPresentableText(): String? {
		//显示语言区域作为后缀
		val localeSuffix = element?.propertyHeader?.name?.let{ " <$it>" }.orEmpty()
		return "${element?.name}$localeSuffix"
		//return psiElement?.name
	}
}

