package com.windea.plugin.idea.pdx.localisation

import com.intellij.openapi.fileTypes.*
import com.windea.plugin.idea.pdx.*

object PdxLocalisationFileType : LanguageFileType(PdxLocalisationLanguage) {
	override fun getName() = pdxLocalisationFileTypeName

	override fun getDescription() = pdxLocalisationFileTypeDescription

	override fun getDefaultExtension() = pdxLocalisationExtension

	override fun getIcon() = pdxLocalisationFileIcon
}

