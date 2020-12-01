package com.windea.plugin.idea.pdx.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptVariableTreeElement(
	private val psiElement: PdxScriptVariable?
): PsiTreeElementBase<PdxScriptVariable>(psiElement){
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return mutableListOf()
	}

	override fun getPresentableText(): String? {
		return psiElement?.name
	}
}
