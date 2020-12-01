package com.windea.plugin.idea.pdx.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptPropertyTreeElement(
	private val element: PdxScriptProperty
) : PsiTreeElementBase<PdxScriptProperty>(element) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		val value = element.propertyValue?.value?:return mutableListOf()
		return when{
			value !is PdxScriptBlock -> mutableListOf()
			value.isArray -> value.valueList.mapTo(mutableListOf()){PdxScriptValueTreeElement(it)}
			value.isObject -> value.propertyList.mapTo(mutableListOf()){PdxScriptPropertyTreeElement(it)}
			else -> mutableListOf()
		}
	}

	override fun getPresentableText(): String? {
		return element.name
	}
}
