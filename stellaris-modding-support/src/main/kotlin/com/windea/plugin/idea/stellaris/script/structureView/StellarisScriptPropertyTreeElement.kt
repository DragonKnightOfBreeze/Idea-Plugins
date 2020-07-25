package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptPropertyTreeElement(
	private val element: StellarisScriptProperty?
) : PsiTreeElementBase<StellarisScriptProperty>(element) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		val array = element?.propertyValue?.array
		val `object` = element?.propertyValue?.`object`
		return when{
			array != null -> array.stringList.mapTo(mutableListOf()) { StellarisScriptStringTreeElement(it) }
			`object` != null ->  `object`.propertyList.mapTo(mutableListOf()) { StellarisScriptPropertyTreeElement(it) }
			else -> mutableListOf()
		}
	}

	override fun getPresentableText(): String? {
		return element?.name
	}
}
