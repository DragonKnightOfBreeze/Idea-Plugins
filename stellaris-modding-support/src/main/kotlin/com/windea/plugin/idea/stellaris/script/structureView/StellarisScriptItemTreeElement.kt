package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptItemTreeElement(
	private val element: StellarisScriptItem
): PsiTreeElementBase<StellarisScriptItem>(element){
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		val value = element.value
		return when{
			value !is StellarisScriptBlock -> mutableListOf()
			value.isObject -> value.propertyList.mapTo(mutableListOf()){StellarisScriptPropertyTreeElement(it)}
			value.isArray -> value.itemList.mapTo(mutableListOf()){StellarisScriptItemTreeElement(it)}
			else -> mutableListOf()
		}
	}

	override fun getPresentableText(): String? {
		val value = element.value
		return when{
			value is StellarisScriptBlock -> blockFolder
			else -> element.text.truncate(60)
		}
	}
}

