package com.windea.plugin.idea.pdx.localisation.formatter

import com.intellij.application.options.*
import com.intellij.psi.codeStyle.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.*
import com.windea.plugin.idea.pdx.localisation.formatter.*

class PdxLocalisationCodeStyleSettingsProvider : CodeStyleSettingsProvider() {
	override fun createCustomSettings(settings: CodeStyleSettings) = PdxLocalisationCodeStyleSettings(settings)

	override fun getConfigurableDisplayName() = pdxLocalisationName

	override fun createConfigurable(settings: CodeStyleSettings, modelSettings: CodeStyleSettings): CodeStyleConfigurable {
		return object : CodeStyleAbstractConfigurable(settings, modelSettings, configurableDisplayName) {
			override fun createPanel(settings: CodeStyleSettings): CodeStyleAbstractPanel {
				return object : TabbedLanguageCodeStylePanel(PdxLocalisationLanguage, currentSettings, settings) {
					override fun initTabs(settings: CodeStyleSettings?) {
						addIndentOptionsTab(settings)
						//DELAY 不清楚是否允许这种语法
						//addWrappingAndBracesTab(settings)
					}
				}
			}
		}
	}
}

