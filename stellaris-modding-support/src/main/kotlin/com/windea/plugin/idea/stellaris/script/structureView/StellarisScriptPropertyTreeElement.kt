package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptPropertyTreeElement(
	private val psiElement: StellarisScriptProperty?
) : PsiTreeElementBase<StellarisScriptProperty>(psiElement) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		val list =  psiElement?.propertyValue?.list
		//认为只要存在元素为text，则所有元素都是text
		return when{
			list == null -> mutableListOf()
			list.textList.isEmpty() -> list.propertyList.mapTo(mutableListOf()) { StellarisScriptPropertyTreeElement(it) }
			else -> list.textList.mapTo(mutableListOf()) { StellarisScriptTextTreeElement(it) }
		}
	}

	override fun getPresentableText(): String? {
		return psiElement?.name
	}
}
