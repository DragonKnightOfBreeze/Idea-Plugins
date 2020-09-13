package com.windea.plugin.idea.bbcode.highlighter

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*

class BBCodeSyntaxHighlighterFactory :SyntaxHighlighterFactory(){
	override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = BBCodeSyntaxHighlighter()
}
