package com.windea.plugin.idea.sbtext

import com.intellij.lang.*
import com.intellij.lang.folding.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.sbtext.psi.*
import com.windea.plugin.idea.sbtext.psi.SbTextTypes.*

//折叠颜色标记

class SbTextFoldingBuilder : FoldingBuilder, DumbAware {
	//这里的node/root居然默认是FILE……
	override fun buildFoldRegions(node: ASTNode, document: Document): Array<FoldingDescriptor> {
		val result = mutableListOf<FoldingDescriptor>()
		val elements = node.psi.collectDescendantsOfType<SbTextColorfulText>()
		for(element in elements) {
			val foldingGroup = FoldingGroup.newGroup("COLORFUL_TEXT")
			element.colorMarker.let { result += FoldingDescriptor(it.node, it.textRange,foldingGroup) }
			element.colorResetMarker?.let { result += FoldingDescriptor(it.node, it.textRange,foldingGroup) }
		}
		return result.toTypedArray()
	}

	override fun getPlaceholderText(node: ASTNode): String? {
		return "<>"
	}

	override fun isCollapsedByDefault(node: ASTNode): Boolean {
		return false
	}
}
