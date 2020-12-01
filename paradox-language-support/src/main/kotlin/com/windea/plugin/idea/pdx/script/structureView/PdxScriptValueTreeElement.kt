package com.windea.plugin.idea.pdx.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptValueTreeElement(
	private val element: PdxScriptValue
): PsiTreeElementBase<PdxScriptValue>(element){
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return when{
			element !is PdxScriptBlock -> mutableListOf()
			element.isArray -> element.valueList.mapTo(mutableListOf()){PdxScriptValueTreeElement(it)}
			element.isObject -> element.propertyList.mapTo(mutableListOf()){PdxScriptPropertyTreeElement(it)}
			else -> mutableListOf()
		}
	}

	override fun getPresentableText(): String? {
		return when{
			element is PdxScriptBlock -> blockFolder
			else -> element.text.truncate(20) //不去除包围的双引号
		}
	}
}

