package com.windea.plugin.idea.stellaris.settings

import com.intellij.openapi.options.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import javax.swing.*

class StellarisSettingsConfigurable : Configurable {
	private var settingsComponent: StellarisSettingsComponent? = null

	override fun getDisplayName() = message("stellaris.settings")

	override fun isModified(): Boolean {
		val settingsComponent = settingsComponent!!
		val settings = StellarisSettingsState.instance
		return settingsComponent.useSteamPathCheckBox.isSelected != settings.useSteamPath
		       || settingsComponent.steamPathTextField.text != settings.steamPath
		       || settingsComponent.stellarisPathTextField.text != settings.stellarisPath
		       || settingsComponent.stellarisModsPathTextField.text != settings.stellarisModsPath
	}

	override fun createComponent(): JComponent? {
		val component = StellarisSettingsComponent()
		settingsComponent = component
		return component.panel
	}

	override fun disposeUIResources() {
		settingsComponent = null
	}

	override fun apply() {
		val settingsComponent = settingsComponent!!
		val settings = StellarisSettingsState.instance
		settings.useSteamPath = settingsComponent.useSteamPathCheckBox.isSelected
		settings.steamPath = settingsComponent.steamPathTextField.text
		settings.stellarisPath = settingsComponent.stellarisPathTextField.text
		settings.stellarisModsPath = settingsComponent.stellarisModsPathTextField.text
	}

	override fun reset() {
		val settingsComponent = settingsComponent!!
		val settings = StellarisSettingsState.instance
		settingsComponent.useSteamPathCheckBox.isSelected = settings.useSteamPath
		settingsComponent.steamPathTextField.text = settings.steamPath
		settingsComponent.steamPathTextField.isEnabled = settings.useSteamPath
		settingsComponent.stellarisPathTextField.text = settings.stellarisPath
		settingsComponent.stellarisPathTextField.isEnabled = !settings.useSteamPath
		settingsComponent.stellarisModsPathTextField.text = settings.stellarisModsPath
		settingsComponent.stellarisModsPathTextField.isEnabled = !settings.useSteamPath
	}
}
