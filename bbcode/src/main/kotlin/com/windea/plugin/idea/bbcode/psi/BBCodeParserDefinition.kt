package com.windea.plugin.idea.bbcode.psi

import com.intellij.lang.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.bbcode.*

class BBCodeParserDefinition: ParserDefinition {
		companion object {
		val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
		val COMMENTS = TokenSet.create()
		val STRINGS = TokenSet.create(BBCodeTypes.TEXT_TOKEN)
		val FILE = IFileElementType(BBCodeLanguage)
	}

	override fun createLexer(project: Project?) = BBCodeLexerAdapter()

	override fun getWhitespaceTokens() = WHITE_SPACES

	override fun getCommentTokens() = COMMENTS

	override fun getStringLiteralElements() = STRINGS

	override fun createParser(project: Project?) = BBCodeParser()

	override fun getFileNodeType() = FILE

	override fun createFile(viewProvider: FileViewProvider) = BBCodeFile(viewProvider)

	override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): ParserDefinition.SpaceRequirements {
		return when{
			left?.elementType == BBCodeTypes.TAG_NAME && right?.elementType == BBCodeTypes.ATTRIBUTE -> ParserDefinition.SpaceRequirements.MUST
			left?.elementType == BBCodeTypes.ATTRIBUTE && right?.elementType == BBCodeTypes.ATTRIBUTE -> ParserDefinition.SpaceRequirements.MUST
			else -> ParserDefinition.SpaceRequirements.MAY
		}
	}

	override fun createElement(node: ASTNode?) = BBCodeTypes.Factory.createElement(node)
}
