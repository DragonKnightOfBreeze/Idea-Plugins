package com.windea.plugin.idea.pdx.localisation.editor

import com.intellij.lang.*
import com.intellij.lang.folding.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationFoldingBuilder : FoldingBuilder, DumbAware {
	override fun getPlaceholderText(node: ASTNode): String? {
		return when(node.elementType){
			PdxLocalisationTypes.ROOT_COMMENT -> "#..."
			PdxLocalisationTypes.COMMENT -> "#..."
			else -> "<folded block>" //不应该出现这种情况
		}
	}

	override fun isCollapsedByDefault(node: ASTNode): Boolean {
		return false
	}

	override fun buildFoldRegions(node: ASTNode, document: Document): Array<FoldingDescriptor> {
		val descriptors = mutableListOf<FoldingDescriptor>()
		collectDescriptorsRecursively(node,document,descriptors)
		return descriptors.toTypedArray()
	}

	private fun collectDescriptorsRecursively(node: ASTNode, document: Document, descriptors: MutableList<FoldingDescriptor>) {
		var startOffset: Int
		var endOffset: Int
		if(node.elementType == PdxLocalisationTypes.COMMENT || node.elementType == PdxLocalisationTypes.ROOT_COMMENT) {
			val commentRange = expandCommentsRange(node.psi)
			startOffset = (commentRange.getFirst() as PsiElement).textRange.startOffset
			 endOffset = (commentRange.getSecond() as PsiElement).textRange.endOffset
			if(document.getLineNumber(startOffset) != document.getLineNumber(endOffset)) {
				descriptors.add(FoldingDescriptor(node, TextRange(startOffset, endOffset)))
			}
		}

		val children = node.getChildren(null as TokenSet?)
		startOffset = children.size
		endOffset = 0
		while(endOffset < startOffset) {
			val child = children[endOffset]
			collectDescriptorsRecursively(child, document, descriptors)
			++endOffset
		}
	}

	private fun expandCommentsRange(element: PsiElement): Couple<PsiElement> {
		return Couple.of(findFurthestSiblingOfSameType(element, false), findFurthestSiblingOfSameType(element, true))
	}
}
