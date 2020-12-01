package com.windea.plugin.idea.pdx.localisation.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.util.treeView.smartTree.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationStructureViewModel(
	editor: Editor?,
	file: PsiFile
) : TextEditorBasedStructureViewModel(editor, file), StructureViewModel.ElementInfoProvider {
	//指定根节点，一般为psiFile
	override fun getRoot() = PdxLocalisationFileTreeElement(psiFile as PdxLocalisationFile)

	//指定在结构视图中的元素
	override fun getSuitableClasses() = defaultSuitableClasses

	//指定可用的排序器，可自定义
	override fun getSorters() = defaultSorters

	override fun isAlwaysShowsPlus(element: StructureViewTreeElement) = element.value is PdxLocalisationFile

	override fun isAlwaysLeaf(element: StructureViewTreeElement) = false

	companion object {
		private val defaultSuitableClasses = arrayOf(
			PdxLocalisationFile::class.java,
			PdxLocalisationProperty::class.java
		)
		private val defaultSorters = arrayOf(Sorter.ALPHA_SORTER)
	}
}

