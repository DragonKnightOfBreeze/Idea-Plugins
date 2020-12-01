package com.windea.plugin.idea.pdx.settings

import com.intellij.openapi.components.*
import com.intellij.openapi.project.*
import com.intellij.util.xmlb.*

@State(name = "org.intellij.sdk.settings.PdxSettingsState", storages = [Storage("PdxModdingSupport.xml")])
class PdxSettingsState : PersistentStateComponent<PdxSettingsState> {
	@JvmField var resolveInternalReferences = true
	@JvmField var resolveExternalReferences = true

	//@JvmField var useSteamDirectory = true
	//@JvmField var steamDirectory = ""
	@JvmField var pdxDirectory = ""
	//@JvmField var pdxModsDirectory = ""

	//val steamPath
	//	get() = steamDirectory
	val pdxPath
		get() = pdxDirectory
		//get() = if(useSteamDirectory) "$steamDirectory\\steamapps\\common\\Pdx" else pdxDirectory
	//val pdxModsPath
	//	get() = if(useSteamDirectory) "$steamDirectory\\steamapps\\workshop\\content\\281990" else pdxModsDirectory

	override fun getState() = this

	override fun loadState(state: PdxSettingsState) = XmlSerializerUtil.copyBean(state, this)

	companion object {
		@JvmStatic
		fun getInstance(): PdxSettingsState = ServiceManager.getService(PdxSettingsState::class.java)
	}
}
