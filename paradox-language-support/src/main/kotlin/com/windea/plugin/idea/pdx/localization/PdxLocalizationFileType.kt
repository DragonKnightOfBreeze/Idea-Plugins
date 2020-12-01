package com.windea.plugin.idea.stellaris.localization

import com.intellij.openapi.fileTypes.*
import com.windea.plugin.idea.stellaris.*

object StellarisLocalizationFileType : LanguageFileType(StellarisLocalizationLanguage) {
	override fun getName() = stellarisLocalizationFileTypeName

	override fun getDescription() = stellarisLocalizationFileTypeDescription

	override fun getDefaultExtension() = stellarisLocalizationExtension

	override fun getIcon() = stellarisLocalizationFileIcon
}

