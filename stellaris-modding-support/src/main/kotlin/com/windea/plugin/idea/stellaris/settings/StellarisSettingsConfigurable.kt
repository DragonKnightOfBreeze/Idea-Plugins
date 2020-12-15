package com.windea.plugin.idea.stellaris.settings

import com.intellij.openapi.options.*
import com.windea.plugin.idea.stellaris.message
import javax.swing.*

class StellarisSettingsConfigurable: SearchableConfigurable {
	private var settingsComponent: StellarisSettingsComponent? = null

	override fun getId() = "settings.language.stellaris"

	override fun getDisplayName() = message("stellaris.settings")

	override fun isModified(): Boolean {
		val settingsComponent = settingsComponent!!
		val settings = StellarisSettingsState.getInstance()
		return settingsComponent.resolveExternalReferencesCheckBox.isSelected != settings.resolveExternalReferences
		       || settingsComponent.resolveReferencesCheckBox.isSelected != settings.resolveReferences
		       || settingsComponent.validateScriptFilesCheckBox.isSelected != settings.validateScriptFiles
		       //|| settingsComponent.useSteamDirectoryCheckBox.isSelected != settings.useSteamDirectory
		       //|| settingsComponent.steamDirectoryTextField.text != settings.steamDirectory
		       || settingsComponent.stellarisDirectoryTextField.text != settings.stellarisDirectory
		       //|| settingsComponent.stellarisModsDirectoryTextField.text != settings.stellarisModsDirectory
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
		val settingsComponent = settingsComponent ?: return
		val settings = StellarisSettingsState.getInstance()
		//cleanupRootDirectoryCache(settings)
		settings.resolveReferences = settingsComponent.resolveReferencesCheckBox.isSelected
		settings.resolveExternalReferences = settingsComponent.resolveExternalReferencesCheckBox.isSelected
		settings.validateScriptFiles = settingsComponent.validateScriptFilesCheckBox.isSelected
		//settings.useSteamDirectory = settingsComponent.useSteamDirectoryCheckBox.isSelected
		//settings.steamDirectory = settingsComponent.steamDirectoryTextField.text
		settings.stellarisDirectory = settingsComponent.stellarisDirectoryTextField.text
		//settings.stellarisModsDirectory = settingsComponent.stellarisModsDirectoryTextField.text
		//addToRootDirectoryCache(settings)
	}
	
	override fun reset() {
		val settingsComponent = settingsComponent ?: return
		val settings = StellarisSettingsState.getInstance()
		settingsComponent.resolveReferencesCheckBox.isSelected = settings.resolveReferences
		settingsComponent.resolveExternalReferencesCheckBox.isSelected = settings.resolveExternalReferences
		settingsComponent.validateScriptFilesCheckBox.isSelected = settings.validateScriptFiles
		//settingsComponent.useSteamDirectoryCheckBox.isSelected = settings.useSteamDirectory
		//settingsComponent.steamDirectoryTextField.text = settings.steamDirectory
		//settingsComponent.steamDirectoryTextField.isEnabled = settings.useSteamDirectory
		settingsComponent.stellarisDirectoryTextField.text = settings.stellarisDirectory
		//settingsComponent.stellarisDirectoryTextField.isEnabled = !settings.useSteamDirectory
		//settingsComponent.stellarisModsDirectoryTextField.text = settings.stellarisModsDirectory
		//settingsComponent.stellarisModsDirectoryTextField.isEnabled = !settings.useSteamDirectory
	}
	
	//TODO 配置变更时更改游戏或模组目录的缓存 - 当前不实现
	
	//private fun cleanupRootDirectoryCache(settings: StellarisSettingsState) {
	//	//rootDirectoryCache.remove(settings.stellarisPath)
	//}
	//
	//private fun addToRootDirectoryCache(settings: StellarisSettingsState) {
	//	//val file = VirtualFileManager.getInstance().refreshAndFindFileByNioPath(Path.of(settings.stellarisPath))
	//	//if(file != null && file.isRootDirectory()) rootDirectoryCache[settings.stellarisPath] = file
	//}
}
