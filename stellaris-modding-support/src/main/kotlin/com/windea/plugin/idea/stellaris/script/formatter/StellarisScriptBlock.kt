package com.windea.plugin.idea.stellaris.script.formatter

import com.intellij.formatting.*
import com.intellij.formatting.Indent
import com.intellij.lang.*
import com.intellij.psi.codeStyle.*
import com.intellij.psi.formatter.common.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*
import com.windea.plugin.idea.stellaris.script.settings.*

class StellarisScriptBlock (
	node: ASTNode,
	private val settings:CodeStyleSettings,
	private val shouldIndent:Boolean = false
):AbstractBlock(node, createWrap(), createAlignment(node)){
	companion object {
		private fun createWrap(): Wrap? {
			return null
		}

		//让变量定义总是对齐行首，
		private fun createAlignment(node:ASTNode): Alignment? {
			//DELAY 对齐属性名
			return when{
				node.elementType == VARIABLE_DEFINITION -> Alignment.createAlignment()
				else -> null
			}
		}

		private fun createSpacingBuilder(settings: CodeStyleSettings): SpacingBuilder {
			//变量声明分隔符周围的空格，属性分隔符周围的空格
			val customSettings = settings.getCustomSettings(StellarisScriptCodeStyleSettings::class.java)
			val spacingVariableDefinitionSeparator = customSettings.SPACE_AROUND_VARIABLE_DEFINITION_SEPARATOR.toInt()
			val spacingPropertySeparator = customSettings.SPACE_AROUND_PROPERTY_SEPARATOR.toInt()
			return SpacingBuilder(settings, StellarisLocalizationLanguage)
				.around(VARIABLE_DEFINITION_SEPARATOR).spaces(spacingVariableDefinitionSeparator)
				.around(PROPERTY_SEPARATOR).spaces(spacingPropertySeparator)
		}
	}

	private val spacingBuilder = createSpacingBuilder(settings)

	override fun buildChildren(): List<Block> {
		//如果已经需要缩进，或者是list的子节点，则注意需要缩进
		return  myNode.nodes().map {
			val parentType = it.treeParent.elementType
			when {
				shouldIndent -> StellarisScriptBlock(it, settings, true)
				parentType == OBJECT -> StellarisScriptBlock(it, settings, true)
				parentType == ARRAY -> StellarisScriptBlock(it, settings, true)
				else -> StellarisScriptBlock(it, settings)
			}
		}
	}

	override fun getIndent(): Indent? {
		//list下面的所有属性和非行尾注释都要缩进
		return when{
			!shouldIndent -> Indent.getNoneIndent()
			 myNode.elementType == TEXT -> Indent.getNormalIndent()
			 myNode.elementType == PROPERTY -> Indent.getNormalIndent()
			myNode.elementType == COMMENT -> Indent.getNormalIndent()
			else -> Indent.getNoneIndent()
		}
	}

	override fun getSpacing(child1: Block?, child2: Block): Spacing? {
		return spacingBuilder.getSpacing(this,child1, child2)
	}

	override fun isLeaf(): Boolean {
		return myNode.firstChildNode == null
	}
}
