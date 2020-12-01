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
		       //|| settingsComponent.useSteamDirectoryCheckBox.isSelected != settings.useSteamDirectory
		       //|| settingsComponent.steamDirectoryTextField.text != settings.steamDirectory
		       || settingsComponent.stellarisDirectoryTextField.text != settings.paradoxDirectory
		       //|| settingsComponent.paradoxModsDirectoryTextField.text != settings.paradoxModsDirectory
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
		val settingsComponent = settingsComponent!!
		val settings = ParadoxSettingsState.getInstance()
		
		//cleanupRootDirectoryCache(settings)
		
		settings.resolveInternalReferences = settingsComponent.resolveInternalReferencesCheckBox.isSelected
		settings.resolveExternalReferences = settingsComponent.resolveExternalReferencesCheckBox.isSelected
		//settings.useSteamDirectory = settingsComponent.useSteamDirectoryCheckBox.isSelected
		//settings.steamDirectory = settingsComponent.steamDirectoryTextField.text
		settings.paradoxDirectory = settingsComponent.stellarisDirectoryTextField.text
		//settings.paradoxModsDirectory = settingsComponent.paradoxModsDirectoryTextField.text
		
		//addToRootDirectoryCache(settings)
	}
	
	override fun reset() {
		val settingsComponent = settingsComponent!!
		val settings = ParadoxSettingsState.getInstance()
		settingsComponent.resolveInternalReferencesCheckBox.isSelected = settings.resolveInternalReferences
		settingsComponent.resolveExternalReferencesCheckBox.isSelected = settings.resolveExternalReferences
		//settingsComponent.useSteamDirectoryCheckBox.isSelected = settings.useSteamDirectory
		//settingsComponent.steamDirectoryTextField.text = settings.steamDirectory
		//settingsComponent.steamDirectoryTextField.isEnabled = settings.useSteamDirectory
		settingsComponent.stellarisDirectoryTextField.text = settings.paradoxDirectory
		//settingsComponent.paradoxDirectoryTextField.isEnabled = !settings.useSteamDirectory
		//settingsComponent.paradoxModsDirectoryTextField.text = settings.paradoxModsDirectory
		//settingsComponent.paradoxModsDirectoryTextField.isEnabled = !settings.useSteamDirectory
	}
	
	//TODO 配置变更时更改游戏或模组目录的缓存 - 当前不实现
	
	//private fun cleanupRootDirectoryCache(settings: ParadoxSettingsState) {
	//	//rootDirectoryCache.remove(settings.paradoxPath)
	//}
	//
	//private fun addToRootDirectoryCache(settings: ParadoxSettingsState) {
	//	//val file = VirtualFileManager.getInstance().refreshAndFindFileByNioPath(Path.of(settings.paradoxPath))
	//	//if(file != null && file.isRootDirectory()) rootDirectoryCache[settings.paradoxPath] = file
	//}
}
