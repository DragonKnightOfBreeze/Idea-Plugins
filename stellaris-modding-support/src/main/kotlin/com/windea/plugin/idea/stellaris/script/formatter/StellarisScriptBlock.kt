package com.windea.plugin.idea.stellaris.script.formatter

import com.intellij.formatting.*
import com.intellij.formatting.Indent
import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.TokenType.*
import com.intellij.psi.codeStyle.*
import com.intellij.psi.formatter.common.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

//调试没有问题就不要随便修改

class StellarisScriptBlock(
	node: ASTNode,
	private val settings: CodeStyleSettings
) : AbstractBlock(node, createWrap(), createAlignment(node)) {
	companion object {
		private fun createWrap(): Wrap? {
			return null
		}

		private fun createAlignment(node: ASTNode): Alignment? {
			return when(node.elementType) {
				COMMENT -> Alignment.createAlignment()
				VARIABLE_DEFINITION -> Alignment.createAlignment()
				PROPERTY -> Alignment.createAlignment()
				else -> null
			}
		}

		private fun createSpacingBuilder(settings: CodeStyleSettings): SpacingBuilder {
			//变量声明分隔符周围的空格，属性分隔符周围的空格
			val customSettings = settings.getCustomSettings(StellarisScriptCodeStyleSettings::class.java)
			val spaceWithinBraces = customSettings.SPACE_WITHIN_BRACES
			val spaceVariableDefinitionSeparator = customSettings.SPACE_AROUND_VARIABLE_DEFINITION_SEPARATOR
			val spacePropertySeparator = customSettings.SPACE_AROUND_PROPERTY_SEPARATOR
			return SpacingBuilder(settings, StellarisLocalizationLanguage)
				.between(LEFT_BRACE, RIGHT_BRACE).spaces(0)
				.after(LEFT_BRACE).spaceIf(spaceWithinBraces)
				.before(RIGHT_BRACE).spaceIf(spaceWithinBraces)
				.around(VARIABLE_DEFINITION_SEPARATOR).spaces(spaceVariableDefinitionSeparator.toInt())
				.around(PROPERTY_SEPARATOR).spaces(spacePropertySeparator.toInt())
		}
	}

	private val spacingBuilder = createSpacingBuilder(settings)

	override fun buildChildren(): List<Block> {
		return myNode.nodes().map { StellarisScriptBlock(it, settings) }
	}

	override fun getIndent(): Indent? {
		return when{
			//block中的直接子节点需要缩进
			myNode.treeParent?.elementType == BLOCK -> Indent.getNormalIndent()
			else -> Indent.getNoneIndent()
		}
	}

	override fun getSpacing(child1: Block?, child2: Block): Spacing? {
		return spacingBuilder.getSpacing(this, child1, child2)
	}

	override fun isLeaf(): Boolean {
		return myNode.firstChildNode == null
	}
}
