package com.windea.plugin.idea.stellaris.localization.formatter

import com.intellij.formatting.*
import com.intellij.formatting.Indent
import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.codeStyle.*
import com.intellij.psi.formatter.common.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*

//调试没有问题就不要随便修改

class StellarisLocalizationBlock(
	node: ASTNode,
	private val settings: CodeStyleSettings
) : AbstractBlock(node, createWrap(), createAlignment(node)) {
	companion object {
		//wrap和alignment可为null，没事随便不要赋值！！！
		//其中alignment默认是和父节点的左边的那个元素向左对齐

		private fun createWrap(): Wrap? {
			return null
		}

		//让语言区域之后每一行都基于缩进对齐
		private fun createAlignment(node: ASTNode): Alignment? {
			//DELAY 不清楚是否允许这种语法
			//val customSettings = settings.getCustomSettings(StellarisLocalizationCodeStyleSettings::class.java)
			//return when{
			//	//可以对齐分隔符，然后前面的属性键向左对齐
			//	node.elementType == COLON && customSettings.ALIGN_PROPERTY_VALUES -> Alignment.createAlignment(true, Anchor.LEFT)
			//	else -> Alignment.createAlignment()
			//}
			//return null
			return when(node.elementType) {
				ROOT_COMMENT, COMMENT -> Alignment.createAlignment()
				LOCALE, LOCALE_ID -> Alignment.createAlignment()
				else -> null
			}
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
		val node = node.elementTypeOrPrevNotWhiteSpace
		val elementType = node?.elementType
		return when {
			//语言区域之后要缩进
			elementType == LOCALE -> Indent.getNormalIndent()
			//属性之后要缩进
			elementType == PROPERTY -> Indent.getNormalIndent()
			//非头部、非行尾注释之后要缩进
			elementType == COMMENT -> Indent.getNormalIndent()
			else -> Indent.getNoneIndent()
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
