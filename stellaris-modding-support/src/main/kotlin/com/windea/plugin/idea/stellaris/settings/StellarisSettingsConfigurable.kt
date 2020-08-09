package com.windea.plugin.idea.stellaris.settings

import com.intellij.openapi.options.*
import com.intellij.openapi.project.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import javax.swing.*

class StellarisSettingsConfigurable: SearchableConfigurable {
	private var settingsComponent: StellarisSettingsComponent? = null

	override fun getId() = "settings.language.stellaris"

	override fun getDisplayName() = message("stellaris.settings")

	override fun isModified(): Boolean {
		val settingsComponent = settingsComponent!!
		val settings = StellarisSettingsState.getInstance()
		return settingsComponent.resolveExternalReferencesCheckBox.isSelected != settings.resolveExternalReferences
		       || settingsComponent.useSteamDirectoryCheckBox.isSelected != settings.useSteamDirectory
		       || settingsComponent.steamDirectoryTextField.text != settings.steamDirectory
		       || settingsComponent.stellarisDirectoryTextField.text != settings.stellarisDirectory
		       || settingsComponent.stellarisModsDirectoryTextField.text != settings.stellarisModsDirectory
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
		val settings = StellarisSettingsState.getInstance()
		settings.resolveExternalReferences = settingsComponent.resolveExternalReferencesCheckBox.isSelected
		settings.useSteamDirectory = settingsComponent.useSteamDirectoryCheckBox.isSelected
		settings.steamDirectory = settingsComponent.steamDirectoryTextField.text
		settings.stellarisDirectory = settingsComponent.stellarisDirectoryTextField.text
		settings.stellarisModsDirectory = settingsComponent.stellarisModsDirectoryTextField.text
	}

	override fun reset() {
		val settingsComponent = settingsComponent!!
		val settings = StellarisSettingsState.getInstance()
		settingsComponent.resolveExternalReferencesCheckBox.isSelected = settings.resolveExternalReferences
		settingsComponent.useSteamDirectoryCheckBox.isSelected = settings.useSteamDirectory
		settingsComponent.steamDirectoryTextField.text = settings.steamDirectory
		settingsComponent.steamDirectoryTextField.isEnabled = settings.useSteamDirectory
		settingsComponent.stellarisDirectoryTextField.text = settings.stellarisDirectory
		settingsComponent.stellarisDirectoryTextField.isEnabled = !settings.useSteamDirectory
		settingsComponent.stellarisModsDirectoryTextField.text = settings.stellarisModsDirectory
		settingsComponent.stellarisModsDirectoryTextField.isEnabled = !settings.useSteamDirectory
	}
}
