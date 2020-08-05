package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptFileTreeElement(
	private val element: StellarisScriptFile
): PsiTreeElementBase<StellarisScriptFile>(element){
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return PsiTreeUtil.getChildrenOfAnyType(element, StellarisScriptVariableDefinition::class.java, StellarisScriptProperty::class.java,StellarisScriptItem::class.java)
			.mapTo(mutableListOf()){
				when(it){
					is StellarisScriptVariableDefinition -> StellarisScriptVariableTreeElement(it)
					is StellarisScriptProperty -> StellarisScriptPropertyTreeElement(it)
					is StellarisScriptItem -> StellarisScriptItemTreeElement(it)
					else -> throw InternalError()
				}
			}
	}

	override fun getPresentableText(): String? {
		return element.name
	}
}
