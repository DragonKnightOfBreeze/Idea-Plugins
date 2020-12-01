package com.windea.plugin.idea.pdx.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptFileTreeElement(
	private val element: PdxScriptFile
) : PsiTreeElementBase<PdxScriptFile>(element) {
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		val rootBlock = element.rootBlock ?: return mutableListOf()
		return PsiTreeUtil.getChildrenOfAnyType(
			rootBlock,
			PdxScriptVariable::class.java,
			PdxScriptProperty::class.java,
			PdxScriptValue::class.java
		).mapTo(mutableListOf()) {
			when(it) {
				is PdxScriptVariable -> PdxScriptVariableTreeElement(it)
				is PdxScriptProperty -> PdxScriptPropertyTreeElement(it)
				is PdxScriptValue -> PdxScriptValueTreeElement(it)
				else -> throw InternalError()
			}
		}
	}
	
	override fun getPresentableText(): String? {
		return element.name
	}
}
