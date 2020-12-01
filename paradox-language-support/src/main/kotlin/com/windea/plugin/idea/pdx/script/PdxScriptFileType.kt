package com.windea.plugin.idea.stellaris.script

import com.intellij.openapi.fileTypes.*
import com.windea.plugin.idea.stellaris.*

object StellarisScriptFileType : LanguageFileType(StellarisScriptLanguage) {
	override fun getName() = stellarisScriptFileTypeName

	override fun getDescription() = stellarisScriptFileTypeDescription

	override fun getDefaultExtension() = stellarisScriptExtension

	override fun getIcon() = stellarisScriptFileIcon
}
