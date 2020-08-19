package com.windea.plugin.idea.stellaris.script.highlighter

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.windea.plugin.idea.stellaris.script.highlighter.*

class StellarisScriptSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
	override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = StellarisScriptSyntaxHighlighter()
}
