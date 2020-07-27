package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptFileTreeElement(
	private val psiElement: StellarisScriptFile?
): PsiTreeElementBase<StellarisScriptFile>(psiElement){
	override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
		return PsiTreeUtil.getChildrenOfAnyType(psiElement, StellarisScriptVariableDefinition::class.java, StellarisScriptProperty::class.java,StellarisScriptItem::class.java)
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
		//显示所在目录作为后缀
		//val type = psiElement?.containingDirectory?.name
		//val typeSnippet = if(type != null) " <$type>" else ""
		//return "${psiElement?.name}$typeSnippet"
		return psiElement?.name
	}
}
