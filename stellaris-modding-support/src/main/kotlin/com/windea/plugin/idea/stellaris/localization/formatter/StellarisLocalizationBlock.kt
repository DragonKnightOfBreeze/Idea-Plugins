package com.windea.plugin.idea.stellaris.localization.formatter

import com.intellij.formatting.*
import com.intellij.formatting.Indent
import com.intellij.lang.*
import com.intellij.psi.codeStyle.*
import com.intellij.psi.formatter.common.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*

//调试没有问题就不要随便修改

class StellarisLocalizationBlock(
	node: ASTNode,
	private val settings: CodeStyleSettings,
) : AbstractBlock(node, createWrap(), createAlignment()) {
	companion object {
		private fun createWrap(): Wrap? {
			return null
		}

		private val rootAlignment = Alignment.createAlignment()
		private val alignment =  Alignment.createAlignment()

		private fun createAlignment(): Alignment? {
			return null
		}

		private fun createSpacingBuilder(settings: CodeStyleSettings): SpacingBuilder {
			//数字和属性值之间有一个空格，冒号和属性值之间也有
			return SpacingBuilder(settings, StellarisLocalizationLanguage)
				.between(COLON, PROPERTY_VALUE).spaces(1)
				.between(NUMBER, PROPERTY_VALUE).spaces(1)
		}
	}

	private val spacingBuilder = createSpacingBuilder(settings)

	//收集所有节点
	override fun buildChildren(): List<Block> {
		return myNode.nodes().map { StellarisLocalizationBlock(it, settings) }
	}

	override fun getIndent(): Indent? {
		return when(myNode.elementType) {
			//属性和非头部非行尾注释要缩进
			COMMENT, PROPERTY -> Indent.getNormalIndent()
			else -> Indent.getSpaceIndent(0)
		}
	}

	//顶级快没有spacing
	override fun getSpacing(child1: Block?, child2: Block): Spacing? {
		return spacingBuilder.getSpacing(this, child1, child2)
	}

	//顶级块不是叶子节点
	override fun isLeaf(): Boolean {
		return myNode.firstChildNode == null
	}
}
