package com.windea.plugin.idea.pdx.settings

import com.intellij.openapi.options.*
import com.windea.plugin.idea.pdx.message
import javax.swing.*

class PdxSettingsConfigurable: SearchableConfigurable {
	private var settingsComponent: PdxSettingsComponent? = null

	override fun getId() = "settings.language.pdx"

	override fun getDisplayName() = message("pdx.settings")

	override fun isModified(): Boolean {
		val settingsComponent = settingsComponent!!
		val settings = PdxSettingsState.getInstance()
		return settingsComponent.resolveExternalReferencesCheckBox.isSelected != settings.resolveExternalReferences
		       || settingsComponent.resolveInternalReferencesCheckBox.isSelected != settings.resolveInternalReferences
		       //|| settingsComponent.useSteamDirectoryCheckBox.isSelected != settings.useSteamDirectory
		       //|| settingsComponent.steamDirectoryTextField.text != settings.steamDirectory
		       || settingsComponent.pdxDirectoryTextField.text != settings.pdxDirectory
		       //|| settingsComponent.pdxModsDirectoryTextField.text != settings.pdxModsDirectory
	}

	override fun createComponent(): JComponent? {
		val component = PdxSettingsComponent()
		settingsComponent = component
		return component.panel
	}

	override fun disposeUIResources() {
		settingsComponent = null
	}
	
	override fun apply() {
		val settingsComponent = settingsComponent!!
		val settings = PdxSettingsState.getInstance()
		
		//cleanupRootDirectoryCache(settings)
		
		settings.resolveInternalReferences = settingsComponent.resolveInternalReferencesCheckBox.isSelected
		settings.resolveExternalReferences = settingsComponent.resolveExternalReferencesCheckBox.isSelected
		//settings.useSteamDirectory = settingsComponent.useSteamDirectoryCheckBox.isSelected
		//settings.steamDirectory = settingsComponent.steamDirectoryTextField.text
		settings.pdxDirectory = settingsComponent.pdxDirectoryTextField.text
		//settings.pdxModsDirectory = settingsComponent.pdxModsDirectoryTextField.text
		
		//addToRootDirectoryCache(settings)
	}
	
	override fun reset() {
		val settingsComponent = settingsComponent!!
		val settings = PdxSettingsState.getInstance()
		settingsComponent.resolveInternalReferencesCheckBox.isSelected = settings.resolveInternalReferences
		settingsComponent.resolveExternalReferencesCheckBox.isSelected = settings.resolveExternalReferences
		//settingsComponent.useSteamDirectoryCheckBox.isSelected = settings.useSteamDirectory
		//settingsComponent.steamDirectoryTextField.text = settings.steamDirectory
		//settingsComponent.steamDirectoryTextField.isEnabled = settings.useSteamDirectory
		settingsComponent.pdxDirectoryTextField.text = settings.pdxDirectory
		//settingsComponent.pdxDirectoryTextField.isEnabled = !settings.useSteamDirectory
		//settingsComponent.pdxModsDirectoryTextField.text = settings.pdxModsDirectory
		//settingsComponent.pdxModsDirectoryTextField.isEnabled = !settings.useSteamDirectory
	}
	
	//TODO 配置变更时更改游戏或模组目录的缓存 - 当前不实现
	
	//private fun cleanupRootDirectoryCache(settings: PdxSettingsState) {
	//	//rootDirectoryCache.remove(settings.pdxPath)
	//}
	//
	//private fun addToRootDirectoryCache(settings: PdxSettingsState) {
	//	//val file = VirtualFileManager.getInstance().refreshAndFindFileByNioPath(Path.of(settings.pdxPath))
	//	//if(file != null && file.isRootDirectory()) rootDirectoryCache[settings.pdxPath] = file
	//}
}
