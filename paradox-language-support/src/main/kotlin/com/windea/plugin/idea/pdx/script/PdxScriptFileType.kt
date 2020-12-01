package com.windea.plugin.idea.pdx.script

import com.intellij.openapi.fileTypes.*
import com.windea.plugin.idea.pdx.*

object PdxScriptFileType : LanguageFileType(PdxScriptLanguage) {
	override fun getName() = pdxScriptFileTypeName

	override fun getDescription() = pdxScriptFileTypeDescription

	override fun getDefaultExtension() = pdxScriptExtension

	override fun getIcon() = pdxScriptFileIcon
}
