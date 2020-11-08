package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptFileTreeElement(
	private val element: StellarisScriptFile
): PsiTreeElementBase<StellarisScriptFile>(element){
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return PsiTreeUtil.getChildrenOfAnyType(element, StellarisScriptVariable::class.java, StellarisScriptProperty::class.java,StellarisScriptValue::class.java)
			.mapTo(mutableListOf()){
				when(it){
					is StellarisScriptVariable -> StellarisScriptVariableTreeElement(it)
					is StellarisScriptProperty -> StellarisScriptPropertyTreeElement(it)
					is StellarisScriptValue -> StellarisScriptValueTreeElement(it)
					else -> throw InternalError()
				}
			}
	}

	override fun getPresentableText(): String? {
		return element.name
	}
}
