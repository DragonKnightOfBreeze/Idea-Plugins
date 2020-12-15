package com.windea.plugin.idea.paradox.settings

import com.intellij.openapi.options.*
import com.windea.plugin.idea.paradox.message
import javax.swing.*

class ParadoxSettingsConfigurable: SearchableConfigurable {
	private var component: ParadoxSettingsComponent? = null
	
	override fun getId() = "settings.language.paradox"

	override fun getDisplayName() = message("paradox.settings")
	
	override fun createComponent(): JComponent {
		val component = ParadoxSettingsComponent()
		this.component = component
		return component.panel
	}

	override fun disposeUIResources() {
		component = null
	}
	
	override fun isModified(): Boolean {
		val settings = ParadoxSettingsState.getInstance()
		val settingsComponent = component!!
		return settingsComponent.resolveReferencesCheckBox.isSelected != settings.resolveReferences
		       || settingsComponent.validateScriptsCheckBox.isSelected != settings.validateScripts
	}
	
	override fun apply() {
		val settings = ParadoxSettingsState.getInstance()
		val settingsComponent = component ?: return
		settings.resolveReferences = settingsComponent.resolveReferencesCheckBox.isSelected
		settings.validateScripts = settingsComponent.validateScriptsCheckBox.isSelected
	}
	
	override fun reset() {
		val settings = ParadoxSettingsState.getInstance()
		val settingsComponent = component ?: return
		settingsComponent.resolveReferencesCheckBox.isSelected = settings.resolveReferences
		settingsComponent.validateScriptsCheckBox.isSelected = settings.validateScripts
	}
}
