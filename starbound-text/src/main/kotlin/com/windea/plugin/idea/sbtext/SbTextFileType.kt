package com.windea.plugin.idea.sbtext

import com.intellij.openapi.fileTypes.*

object SbTextFileType: LanguageFileType(SbTextLanguage){
	override fun getName() = sbTextFileTypeName

	override fun getDescription() = sbTextFileTypeDescription

	override fun getDefaultExtension() = sbTextExtension

	override fun getIcon() = sbTextFileIcon
}
