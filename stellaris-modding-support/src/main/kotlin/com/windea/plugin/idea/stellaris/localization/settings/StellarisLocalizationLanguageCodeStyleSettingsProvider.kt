package com.windea.plugin.idea.stellaris.localization.settings

import com.intellij.application.options.*
import com.intellij.psi.codeStyle.*
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.*

//代码风格：
//INDENT_SETTINGS
//* INDENT_SIZE
//* KEEP_INDENTS_ON_EMPTY_LINES
//WRAPPING_AND_BRACES_SETTINGS
//* ALIGN_PROPERTY_VALUES

@ExtensionPoint
class StellarisLocalizationLanguageCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {
	override fun getLanguage() = StellarisLocalizationLanguage

	override fun createCustomSettings(settings: CodeStyleSettings): CustomCodeStyleSettings? {
		return StellarisLocalizationCodeStyleSettings(settings)
	}

	//需要重载这个方法以显示indentOptions设置页面
	override fun getIndentOptionsEditor(): IndentOptionsEditor? {
		return IndentOptionsEditor(this)
	}

	override fun customizeDefaults(commonSettings: CommonCodeStyleSettings, indentOptions: CommonCodeStyleSettings.IndentOptions) {
		indentOptions.INDENT_SIZE = 1
		indentOptions.KEEP_INDENTS_ON_EMPTY_LINES = true
	}

	override fun customizeSettings(consumer: CodeStyleSettingsCustomizable, settingsType: SettingsType) {
		when(settingsType) {
			SettingsType.INDENT_SETTINGS -> {
				consumer.showStandardOptions(
					IndentOption.INDENT_SIZE.name,
					IndentOption.KEEP_INDENTS_ON_EMPTY_LINES.name
				)
			}
			//DELAY 不清楚是否允许这种语法
			//SettingsType.WRAPPING_AND_BRACES_SETTINGS -> {
			//	consumer.showCustomOption(
			//		StellarisLocalizationCodeStyleSettings::class.java,
			//		Option.ALIGN_PROPERTY_VALUES.name,
			//		message("stellaris.localization.codeStyle.alignPropertyValues"),
			//		null
			//	)
			//}
			else -> {}
		}
	}

	override fun getCodeSample(settingsType: LanguageCodeStyleSettingsProvider.SettingsType): String? {
		return stellarisLocalizationDummyText
	}

	class IndentOptionsEditor(
		provider: LanguageCodeStyleSettingsProvider
	) : SmartIndentOptionsEditor(provider)
}
