package com.windea.plugin.idea.paradox.settings

import com.intellij.openapi.options.*
import com.windea.plugin.idea.paradox.message
import javax.swing.*

class ParadoxSettingsConfigurable: SearchableConfigurable {
	private var settingsComponent: ParadoxSettingsComponent? = null

	override fun getId() = "settings.language.paradox"

	override fun getDisplayName() = message("paradox.settings")

	override fun isModified(): Boolean {
		val settingsComponent = settingsComponent!!
		val settings = ParadoxSettingsState.getInstance()
		return settingsComponent.resolveExternalReferencesCheckBox.isSelected != settings.resolveExternalReferences
		       || settingsComponent.resolveInternalReferencesCheckBox.isSelected != settings.resolveInternalReferences
	}

	override fun createComponent(): JComponent? {
		val component = ParadoxSettingsComponent()
		settingsComponent = component
		return component.panel
	}

	override fun disposeUIResources() {
		settingsComponent = null
	}
	
	override fun apply() {
		val settingsComponent = settingsComponent ?: return
		val settings = ParadoxSettingsState.getInstance()
		
		settings.resolveInternalReferences = settingsComponent.resolveInternalReferencesCheckBox.isSelected
		settings.resolveExternalReferences = settingsComponent.resolveExternalReferencesCheckBox.isSelected
	}
	
	override fun reset() {
		val settingsComponent = settingsComponent ?: return
		val settings = ParadoxSettingsState.getInstance()
		settingsComponent.resolveInternalReferencesCheckBox.isSelected = settings.resolveInternalReferences
		settingsComponent.resolveExternalReferencesCheckBox.isSelected = settings.resolveExternalReferences
	}
}
