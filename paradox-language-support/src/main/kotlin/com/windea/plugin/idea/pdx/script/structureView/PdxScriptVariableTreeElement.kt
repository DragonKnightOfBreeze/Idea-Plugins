package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptVariableTreeElement(
	private val psiElement: StellarisScriptVariable?
): PsiTreeElementBase<StellarisScriptVariable>(psiElement){
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return mutableListOf()
	}

	override fun getPresentableText(): String? {
		return psiElement?.name
	}
}
