package com.windea.plugin.idea.sbtext.highlighter

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*

class SbTextSyntaxHighlighterFactory: SyntaxHighlighterFactory(){
	override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = SbTextSyntaxHighlighter()
}
