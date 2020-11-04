package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptItemTreeElement(
	private val element: StellarisScriptItem
): PsiTreeElementBase<StellarisScriptItem>(element){
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		val block = element.block
		return when{
			block == null -> mutableListOf()
			block.isObject -> block.propertyList.mapTo(mutableListOf()){StellarisScriptPropertyTreeElement(it)}
			block.isArray -> block.itemList.mapTo(mutableListOf()){StellarisScriptItemTreeElement(it)}
			else -> mutableListOf()
		}
	}

	override fun getPresentableText(): String? {
		return if(element.block != null) blockFolder else element.text.truncate(60)
	}
}

