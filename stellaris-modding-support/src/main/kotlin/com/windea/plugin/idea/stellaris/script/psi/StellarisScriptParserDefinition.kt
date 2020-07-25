package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.lang.*
import com.intellij.lang.ParserDefinition.SpaceRequirements.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.intellij.psi.TokenType.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*
import com.windea.plugin.idea.stellaris.script.*

@ExtensionPoint
class StellarisScriptParserDefinition : ParserDefinition {
	companion object {
		val WHITE_SPACES = TokenSet.create(WHITE_SPACE)
		val COMMENTS = TokenSet.create(COMMENT, END_OF_LINE_COMMENT)
		val STRINGS = TokenSet.create(PROPERTY_VALUE)
		val FILE = IFileElementType(StellarisScriptLanguage)
	}

	override fun createLexer(project: Project?) = StellarisScriptLexerAdapter()

	override fun getWhitespaceTokens() = WHITE_SPACES

	override fun getCommentTokens() = COMMENTS

	override fun getStringLiteralElements() = STRINGS

	override fun createParser(project: Project?) = StellarisScriptParser()

	override fun getFileNodeType() = FILE

	override fun createFile(viewProvider: FileViewProvider) = StellarisScriptFile(viewProvider)

	override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?) = when {
		//文本之间必须有空白
		left?.elementType == STRING && right?.elementType == STRING -> MUST
		//变量之前必须换行
		right?.elementType == VARIABLE_NAME -> MUST_LINE_BREAK
		//属性之前必须换行，除非是"{"
		right?.elementType == PROPERTY_KEY && left?.elementType != LEFT_BRACE -> MUST_LINE_BREAK
		else -> MAY
	}

	override fun createElement(node: ASTNode) = StellarisScriptTypes.Factory.createElement(node)
}
