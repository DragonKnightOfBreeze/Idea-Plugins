package com.windea.plugin.idea.stellaris.script.formatter

import com.intellij.formatting.*
import com.intellij.formatting.Indent
import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.codeStyle.*
import com.intellij.psi.formatter.common.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

//调试没有问题就不要随便修改

class StellarisScriptBlock(
	node: ASTNode,
	private val settings: CodeStyleSettings,
) : AbstractBlock(node, createWrap(), createAlignment()) {
	companion object {
		private fun createWrap(): Wrap? {
			return null
		}

		private fun createAlignment(): Alignment? {
			return null
		}

		private fun createSpacingBuilder(settings: CodeStyleSettings): SpacingBuilder {
			//变量声明分隔符周围的空格，属性分隔符周围的空格
			val customSettings = settings.getCustomSettings(StellarisScriptCodeStyleSettings::class.java)
			val spaceWithinBraces = customSettings.SPACE_WITHIN_BRACES
			val spaceVariableDefinitionSeparator = customSettings.SPACE_AROUND_VARIABLE_SEPARATOR
			val spacePropertySeparator = customSettings.SPACE_AROUND_PROPERTY_SEPARATOR
			return SpacingBuilder(settings, StellarisLocalizationLanguage)
				.between(LEFT_BRACE, RIGHT_BRACE).spaces(0)
				.after(LEFT_BRACE).spaceIf(spaceWithinBraces)
				.before(RIGHT_BRACE).spaceIf(spaceWithinBraces)
				.around(VARIABLE_SEPARATOR).spaces(spaceVariableDefinitionSeparator.toInt())
				.around(PROPERTY_SEPARATOR).spaces(spacePropertySeparator.toInt())
		}
	}

	private val spacingBuilder = createSpacingBuilder(settings)

	override fun buildChildren(): List<Block> {
		return myNode.nodes().map { StellarisScriptBlock(it, settings) }
	}

	override fun getIndent(): Indent? {
		//配置缩进
		//block中的属性、值、注释需要缩进
		if(myNode.treeParent?.elementType != BLOCK) return Indent.getNoneIndent()
		return when(myNode.elementType) {
			COMMENT, PROPERTY, VALUE, BOOLEAN, NUMBER, STRING, COLOR, CODE -> Indent.getNormalIndent()
			else -> Indent.getNoneIndent()
		}
	}

	override fun getChildIndent(): Indent? {
		//配置换行时的自动缩进
		//在file和rootBlock中不要缩进，在block中要缩进
		return when{
			myNode.psi is PsiFile -> Indent.getNoneIndent()
			myNode.elementType == ROOT_BLOCK -> Indent.getNoneIndent()
			myNode.elementType == BLOCK -> Indent.getNormalIndent()
			else -> null
		}
	}

	override fun getSpacing(child1: Block?, child2: Block): Spacing? {
		return spacingBuilder.getSpacing(this, child1, child2)
	}

	override fun isLeaf(): Boolean {
		return myNode.firstChildNode == null
	}
}
