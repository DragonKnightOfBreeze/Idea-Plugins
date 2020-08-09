package com.windea.plugin.idea.stellaris.settings

import com.intellij.openapi.components.*
import com.intellij.util.xmlb.*

@State(name = "org.intellij.sdk.settings.StellarisSettingsState", storages = [Storage("StellarisModdingSupport.xml")])
class StellarisSettingsState : PersistentStateComponent<StellarisSettingsState> {
	@JvmField var useSteamPath = true
	@JvmField var steamPath = ""
	@JvmField var stellarisPath = ""
	@JvmField var stellarisModsPath = ""

	val actualStellarisPath
		get() = if(useSteamPath) "$steamPath\\steamapps\\common\\Stellaris" else stellarisPath
	val actualStellarisModsPath
		get() = if(useSteamPath) "$steamPath\\steamapps\\workshop\\content\\281990" else stellarisModsPath

	override fun getState() = this

	override fun loadState(state: StellarisSettingsState) = XmlSerializerUtil.copyBean(state, this)

	companion object {
		val instance get() = ServiceManager.getService(StellarisSettingsState::class.java)
	}
}
