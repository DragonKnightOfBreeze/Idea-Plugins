package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptPropertyTreeElement(
	private val element: StellarisScriptProperty
) : PsiTreeElementBase<StellarisScriptProperty>(element) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		val value = element.propertyValue?.value?:return mutableListOf()
		return when{
			value !is StellarisScriptBlock -> mutableListOf()
			value.isArray -> value.valueList.mapTo(mutableListOf()){StellarisScriptValueTreeElement(it)}
			value.isObject -> value.propertyList.mapTo(mutableListOf()){StellarisScriptPropertyTreeElement(it)}
			else -> mutableListOf()
		}
	}

	override fun getPresentableText(): String? {
		return element.name
	}
}
