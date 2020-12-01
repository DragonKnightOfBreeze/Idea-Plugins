package com.windea.plugin.idea.stellaris.localization.structureView

import com.intellij.ide.structureView.*
import com.intellij.ide.util.treeView.smartTree.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationStructureViewModel(
	editor: Editor?,
	file: PsiFile
) : TextEditorBasedStructureViewModel(editor, file), StructureViewModel.ElementInfoProvider {
	//指定根节点，一般为psiFile
	override fun getRoot() = StellarisLocalizationFileTreeElement(psiFile as StellarisLocalizationFile)

	//指定在结构视图中的元素
	override fun getSuitableClasses() = defaultSuitableClasses

	//指定可用的排序器，可自定义
	override fun getSorters() = defaultSorters

	override fun isAlwaysShowsPlus(element: StructureViewTreeElement) = element.value is StellarisLocalizationFile

	override fun isAlwaysLeaf(element: StructureViewTreeElement) = false

	companion object {
		private val defaultSuitableClasses = arrayOf(
			StellarisLocalizationFile::class.java,
			StellarisLocalizationProperty::class.java
		)
		private val defaultSorters = arrayOf(Sorter.ALPHA_SORTER)
	}
}

