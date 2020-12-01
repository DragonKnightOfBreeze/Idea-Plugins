package com.windea.plugin.idea.pdx.script.highlighter

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.windea.plugin.idea.pdx.script.highlighter.*

class PdxScriptSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
	override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = PdxScriptSyntaxHighlighter()
}
