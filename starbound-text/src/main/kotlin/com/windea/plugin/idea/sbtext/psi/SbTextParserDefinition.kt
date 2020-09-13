@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.sbtext.psi

import com.intellij.lang.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.sbtext.*

class SbTextParserDefinition: ParserDefinition {
	companion object {
		val WHITE_SPACES = TokenSet.EMPTY
		val COMMENTS = TokenSet.EMPTY
		val STRINGS = TokenSet.create(SbTextTypes.TEXT_TOKEN)
		val FILE = IFileElementType(SbTextLanguage)
	}

	override fun createLexer(project: Project?) = SbTextLexerAdapter()

	override fun getWhitespaceTokens() = WHITE_SPACES

	override fun getCommentTokens() = COMMENTS

	override fun getStringLiteralElements() = STRINGS

	override fun createParser(project: Project?) = SbTextParser()

	override fun getFileNodeType() = FILE

	override fun createFile(viewProvider: FileViewProvider) = SbTextFile(viewProvider)

	override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): ParserDefinition.SpaceRequirements {
		return ParserDefinition.SpaceRequirements.MUST_NOT
	}

	override fun createElement(node: ASTNode?) = SbTextTypes.Factory.createElement(node)
}

