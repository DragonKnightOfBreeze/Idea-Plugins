package com.windea.plugin.idea.bbcode

import com.intellij.openapi.fileTypes.*

object BBCodeFileType : LanguageFileType(BBCodeLanguage) {
	override fun getName() = bbcodeFileTypeName

	override fun getDescription() = bbcodeFileTypeDescription

	override fun getDefaultExtension() = bbcodeExtension

	override fun getIcon() = bbcodeFileIcon
}
