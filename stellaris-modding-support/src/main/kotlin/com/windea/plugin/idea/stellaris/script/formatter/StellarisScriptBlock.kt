package com.windea.plugin.idea.stellaris.script.formatter

import com.intellij.formatting.*
import com.intellij.formatting.Indent
import com.intellij.lang.*
import com.intellij.psi.codeStyle.*
import com.intellij.psi.formatter.common.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

class StellarisScriptBlock(
	node: ASTNode,
	private val settings: CodeStyleSettings,
	private val shouldIndent: Boolean = false
) : AbstractBlock(node, createWrap(), createAlignment(node)) {
	companion object {
		private fun createWrap(): Wrap? {
			return null
		}

		//让变量定义总是对齐行首，
		private fun createAlignment(node: ASTNode): Alignment? {
			//DELAY 对齐属性名
			return when(node.elementType) {
				COMMENT -> Alignment.createAlignment()
				VARIABLE_DEFINITION, VARIABLE_NAME, VARIABLE_NAME_ID -> Alignment.createAlignment()
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
				.between(LEFT_BRACE,RIGHT_BRACE).spaces(0)
				.after(LEFT_BRACE).spaceIf(spaceWithinBraces)
				.before(RIGHT_BRACE).spaceIf(spaceWithinBraces)
				.around(VARIABLE_DEFINITION_SEPARATOR).spaces(spaceVariableDefinitionSeparator.toInt())
				.around(PROPERTY_SEPARATOR).spaces(spacePropertySeparator.toInt())
		}
	}

	private val spacingBuilder = createSpacingBuilder(settings)

	override fun buildChildren(): List<Block> {
		//如果已经需要缩进，或者是list的子节点，则注意需要缩进
		return myNode.nodesNotWhiteSpace().map {
			when {
				shouldIndent -> StellarisScriptBlock(it, settings, true)
				it.treeParent.elementType == BLOCK -> StellarisScriptBlock(it, settings, true)
				//it.treeParent.elementType == OBJECT -> StellarisScriptBlock(it, settings, true)
				//it.treeParent.elementType == ARRAY -> StellarisScriptBlock(it, settings, true)
				else -> StellarisScriptBlock(it, settings)
			}
		}
	}

	override fun getIndent(): Indent? {
		if(!shouldIndent) return Indent.getNoneIndent()
		//list下面的所有属性和非行尾注释都要缩进
		return when(myNode.elementType) {
			ITEM -> Indent.getNormalIndent()
			PROPERTY -> Indent.getNormalIndent()
			COMMENT -> Indent.getNormalIndent()
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
