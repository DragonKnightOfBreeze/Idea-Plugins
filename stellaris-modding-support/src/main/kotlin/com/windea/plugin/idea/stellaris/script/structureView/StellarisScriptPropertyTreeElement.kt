package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptPropertyTreeElement(
	private val element: StellarisScriptProperty?
) : PsiTreeElementBase<StellarisScriptProperty>(element) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		val result = mutableListOf<StructureViewTreeElement>()
		val block = element?.propertyValue?.block ?: return result

		val propertyList = block.propertyList
		if(propertyList.isNotEmpty()) propertyList.mapTo(result) { StellarisScriptPropertyTreeElement(it) }

		val itemList = block.itemList
		if(itemList.isNotEmpty()) itemList.mapTo(result) { StellarisScriptItemTreeElement(it) }

		return result

		//val array = element?.propertyValue?.array
		//val `object` = element?.propertyValue?.`object`
		//return when{
		//	array != null -> array.stringList.mapTo(mutableListOf()) { StellarisScriptItemTreeElement(it) }
		//	object` != null ->  `object`.propertyList.mapTo(mutableListOf()) { StellarisScriptPropertyTreeElement(it) }
		//	else -> mutableListOf()
		//}
	}

	override fun getPresentableText(): String? {
		return element?.name
	}
}
