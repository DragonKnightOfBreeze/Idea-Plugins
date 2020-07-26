package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptStringTreeElement(
	private val psiElement: StellarisScriptString?
): PsiTreeElementBase<StellarisScriptString>(psiElement){
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return mutableListOf()
	}

	override fun getPresentableText(): String? {
		return psiElement?.value
	}
}

