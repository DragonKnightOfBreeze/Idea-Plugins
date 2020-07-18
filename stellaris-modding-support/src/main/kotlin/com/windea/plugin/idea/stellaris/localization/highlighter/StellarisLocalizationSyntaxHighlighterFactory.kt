package com.windea.plugin.idea.stellaris.localization.highlighter

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.windea.plugin.idea.stellaris.annotations.*

@ExtensionPoint
class StellarisLocalizationSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
	override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = StellarisLocalizationSyntaxHighlighter()
}

