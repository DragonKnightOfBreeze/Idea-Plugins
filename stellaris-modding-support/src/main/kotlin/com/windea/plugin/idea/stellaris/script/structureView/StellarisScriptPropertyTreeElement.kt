package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptPropertyTreeElement(
	private val element: StellarisScriptProperty
) : PsiTreeElementBase<StellarisScriptProperty>(element) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		val block = element.propertyValue?.block

		return when{
			block == null || block.isEmpty -> mutableListOf()
			block.isObject -> block.propertyList.mapTo(mutableListOf()){StellarisScriptPropertyTreeElement(it)}
			block.isArray -> block.itemList.mapTo(mutableListOf()){StellarisScriptItemTreeElement(it)}
			else -> mutableListOf()
		}
	}

	override fun getPresentableText(): String? {
		return element.name
	}
}
