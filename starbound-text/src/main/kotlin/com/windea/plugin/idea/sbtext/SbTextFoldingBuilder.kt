package com.windea.plugin.idea.sbtext

import com.intellij.lang.*
import com.intellij.lang.folding.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.windea.plugin.idea.sbtext.psi.*
import com.windea.plugin.idea.sbtext.psi.SbTextTypes.*

class SbTextFoldingBuilder: FoldingBuilder, DumbAware {
	override fun buildFoldRegions(node: ASTNode, document: Document): Array<FoldingDescriptor> {
		return when(node.elementType){
			COLOR_MARKER -> arrayOf(FoldingDescriptor(node,node.textRange))
			COLOR_RESET_MARKER ->arrayOf(FoldingDescriptor(node,node.textRange))
			else -> FoldingDescriptor.EMPTY
		}
	}

	override fun getPlaceholderText(node: ASTNode): String? {
		return when(node.elementType){
			COLOR_MARKER -> "<>" //暂且作为占位符
			COLOR_RESET_MARKER -> "<>" //暂且作为占位符
			else -> "<folded block>"
		}
	}

	override fun isCollapsedByDefault(node: ASTNode): Boolean {
		return when(node.elementType){
			COLOR_MARKER -> true
			COLOR_RESET_MARKER -> true
			else -> false
		}
	}

}
