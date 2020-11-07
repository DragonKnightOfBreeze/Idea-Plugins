package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptValueTreeElement(
	private val element: StellarisScriptValue
): PsiTreeElementBase<StellarisScriptValue>(element){
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return when{
			element !is StellarisScriptBlock -> mutableListOf()
			element.isArray -> element.valueList.mapTo(mutableListOf()){StellarisScriptValueTreeElement(it)}
			element.isObject -> element.propertyList.mapTo(mutableListOf()){StellarisScriptPropertyTreeElement(it)}
			else -> mutableListOf()
		}
	}

	override fun getPresentableText(): String? {
		return when{
			element is StellarisScriptBlock -> blockFolder
			else -> element.text.truncate(60)
		}
	}
}

