package com.windea.plugin.idea.paradox.settings

import com.intellij.openapi.components.*
import com.intellij.openapi.project.*
import com.intellij.util.xmlb.*

@State(name = "ParadoxSettingsState", storages = [Storage("paradoxLanguageSupport.xml")])
class ParadoxSettingsState : PersistentStateComponent<ParadoxSettingsState> {
	@JvmField var resolveInternalReferences = true
	@JvmField var resolveExternalReferences = true

	//@JvmField var useSteamDirectory = true
	//@JvmField var steamDirectory = ""
	@JvmField var paradoxDirectory = ""
	//@JvmField var paradoxModsDirectory = ""

	//val steamPath
	//	get() = steamDirectory
	val paradoxPath
		get() = paradoxDirectory
		//get() = if(useSteamDirectory) "$steamDirectory\\steamapps\\common\\Paradox" else paradoxDirectory
	//val paradoxModsPath
	//	get() = if(useSteamDirectory) "$steamDirectory\\steamapps\\workshop\\content\\281990" else paradoxModsDirectory

	override fun getState() = this

	override fun loadState(state: ParadoxSettingsState) = XmlSerializerUtil.copyBean(state, this)

	companion object {
		@JvmStatic
		fun getInstance(): ParadoxSettingsState = ServiceManager.getService(ParadoxSettingsState::class.java)
	}
}
