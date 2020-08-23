package com.windea.plugin.idea.stellaris.settings

import com.intellij.openapi.components.*
import com.intellij.openapi.project.*
import com.intellij.util.xmlb.*

@State(name = "org.intellij.sdk.settings.StellarisSettingsState", storages = [Storage("StellarisModdingSupport.xml")])
class StellarisSettingsState : PersistentStateComponent<StellarisSettingsState> {
	@JvmField var resolveInternalReferences = true
	@JvmField var resolveExternalReferences = false

	@JvmField var useSteamDirectory = true
	@JvmField var steamDirectory = ""
	@JvmField var stellarisDirectory = ""
	@JvmField var stellarisModsDirectory = ""

	val steamPath
		get() = steamDirectory
	val stellarisPath
		get() = if(useSteamDirectory) "$steamDirectory\\steamapps\\common\\Stellaris" else stellarisDirectory
	val stellarisModsPath
		get() = if(useSteamDirectory) "$steamDirectory\\steamapps\\workshop\\content\\281990" else stellarisModsDirectory

	override fun getState() = this

	override fun loadState(state: StellarisSettingsState) = XmlSerializerUtil.copyBean(state, this)

	companion object {
		@JvmStatic
		fun getInstance(): StellarisSettingsState = ServiceManager.getService(StellarisSettingsState::class.java)
	}
}
