package com.windea.plugin.idea.stellaris.script.settings

import com.intellij.application.options.*
import com.intellij.psi.codeStyle.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.*

@ExtensionPoint
class StellarisScriptCodeStyleSettingsProvider : CodeStyleSettingsProvider() {
	override fun createCustomSettings(settings: CodeStyleSettings) = StellarisScriptCodeStyleSettings(settings)

	override fun getConfigurableDisplayName() = stellarisScriptName

	override fun createConfigurable(settings: CodeStyleSettings, modelSettings: CodeStyleSettings): CodeStyleConfigurable {
		return object : CodeStyleAbstractConfigurable(settings, modelSettings, configurableDisplayName) {
			override fun createPanel(settings: CodeStyleSettings): CodeStyleAbstractPanel {
				return object : TabbedLanguageCodeStylePanel(StellarisScriptLanguage, currentSettings, settings) {
					override fun initTabs(settings: CodeStyleSettings?) {
						addIndentOptionsTab(settings)
						addSpacesTab(settings)
						//addWrappingAndBracesTab(settings)
					}
				}
			}
		}
	}
}
