package com.windea.plugin.idea.stellaris.script.formatter


import com.intellij.application.options.*
import com.intellij.psi.codeStyle.*
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.message
import com.windea.plugin.idea.stellaris.script.*
import com.windea.plugin.idea.stellaris.script.formatter.StellarisScriptCodeStyleSettings.*

//代码风格：
//INDENT_SETTINGS
//* INDENT_SIZE
//* CONTINUATION_INDENT_SIZE
//* KEEP_INDENTS_ON_EMPTY_LINES
//* USE_TAB_CHARACTER
//SPACING_SETTINGS
//* SPACE_WITHIN_BRACES
//* SPACE_AROUND_VARIABLE_SEPARATOR
//* SPACE_AROUND_PROPERTY_SEPARATOR

class StellarisScriptLanguageCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {
	override fun getLanguage() = StellarisScriptLanguage

	override fun createCustomSettings(settings: CodeStyleSettings): CustomCodeStyleSettings? {
		return StellarisScriptCodeStyleSettings(settings)
	}

	//需要重载这个方法以显示indentOptions设置页面
	override fun getIndentOptionsEditor(): IndentOptionsEditor? {
		return IndentOptionsEditor(this)
	}

	override fun customizeDefaults(commonSettings: CommonCodeStyleSettings, indentOptions: CommonCodeStyleSettings.IndentOptions) {
		indentOptions.INDENT_SIZE = 4
		indentOptions.CONTINUATION_INDENT_SIZE = 4
		indentOptions.KEEP_INDENTS_ON_EMPTY_LINES = true
		indentOptions.USE_TAB_CHARACTER = true
	}

	override fun customizeSettings(consumer: CodeStyleSettingsCustomizable, settingsType: SettingsType) {
		when(settingsType) {
			SettingsType.INDENT_SETTINGS -> {
				consumer.showStandardOptions(
					IndentOption.INDENT_SIZE.name,
					IndentOption.CONTINUATION_INDENT_SIZE.name,
					IndentOption.KEEP_INDENTS_ON_EMPTY_LINES.name,
					IndentOption.USE_TAB_CHARACTER.name
				)
			}
			SettingsType.SPACING_SETTINGS -> {
				consumer.showCustomOption(
					StellarisScriptCodeStyleSettings::class.java,
					Option.SPACE_WITHIN_BRACES.name,
					message("stellaris.script.codeStyle.spaceWithinBraces"),
					SPACES_WITHIN
				)
				consumer.showCustomOption(
					StellarisScriptCodeStyleSettings::class.java,
					Option.SPACE_AROUND_VARIABLE_SEPARATOR.name,
					message("stellaris.script.codeStyle.spaceAroundVariableDefinitionSeparator"),
					SPACES_AROUND_OPERATORS
				)
				consumer.showCustomOption(
					StellarisScriptCodeStyleSettings::class.java,
					Option.SPACE_AROUND_PROPERTY_SEPARATOR.name,
					message("stellaris.script.codeStyle.spaceAroundPropertySeparator"),
					SPACES_AROUND_OPERATORS
				)
			}
			else -> {}
		}
	}

	override fun getCodeSample(settingsType: SettingsType): String? {
		return stellarisScriptDummyText
	}

	class IndentOptionsEditor(
		provider: LanguageCodeStyleSettingsProvider
	) : SmartIndentOptionsEditor(provider)
}
