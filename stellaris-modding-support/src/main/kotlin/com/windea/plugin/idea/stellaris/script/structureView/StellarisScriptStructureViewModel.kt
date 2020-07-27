package com.windea.plugin.idea.stellaris.script.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.util.treeView.smartTree.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptStructureViewModel(
	editor: Editor?,
	psiFile: PsiFile
) : TextEditorBasedStructureViewModel(editor, psiFile), StructureViewModel.ElementInfoProvider {
	//指定根节点，一般为psiFile
	override fun getRoot() = StellarisScriptFileTreeElement(psiFile as StellarisScriptFile)

	//指定在结构视图中的元素
	override fun getSuitableClasses() = defaultSuitableClasses

	//指定可用的排序器，可自定义
	override fun getSorters() = defaultSorters

	override fun isAlwaysShowsPlus(element: StructureViewTreeElement) = element.value is StellarisScriptFile

	override fun isAlwaysLeaf(element: StructureViewTreeElement) = false

	companion object {
		private val defaultSuitableClasses = arrayOf(
			StellarisScriptFile::class.java,
			StellarisScriptVariableDefinition::class.java,
			StellarisScriptProperty::class.java,
			StellarisScriptPropertyValue::class.java,
			StellarisScriptItem::class.java
		)
		private val defaultSorters = arrayOf(Sorter.ALPHA_SORTER)
	}
}
