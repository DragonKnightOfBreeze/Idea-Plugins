@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.stellaris.settings

import com.intellij.openapi.components.*
import com.intellij.openapi.options.*
import com.intellij.openapi.ui.*
import com.intellij.ui.components.*
import com.intellij.util.ui.*
import com.intellij.util.xmlb.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import javax.swing.*

@State(name = "org.intellij.sdk.settings.AppSettingsState", storages = [Storage("StellarisModdingSupport.xml")])
class AppSettingsState : PersistentStateComponent<AppSettingsState> {
	@JvmField var useSteamPath = true
	@JvmField var steamPath = ""
	@JvmField var stellarisPath = ""
	@JvmField var stellarisModsPath = ""

	val actualStellarisPath
		get() = if(useSteamPath) "$steamPath\\steamapps\\common\\Stellaris" else stellarisPath
	val actualStellarisModsPath
		get() = if(useSteamPath) "$steamPath\\steamapps\\workshop\\content\\281990" else stellarisModsPath

	override fun getState() = this

	override fun loadState(state: AppSettingsState) = XmlSerializerUtil.copyBean(state, this)

	companion object {
		val instance get() = ServiceManager.getService(AppSettingsState::class.java)
	}
}

/**
 * * 是否使用steam路径
 * * steam路径
 * * stellaris路径
 * * stellaris mods路径
 */
class AppSettingsComponent {
	val useSteamPathCheckBox = JBCheckBox(message("stellaris.settings.useSteamPath")).apply {
		addChangeListener {
			println(it)
			val useSteamPath = it.source as Boolean
			steamPathTextField.isEnabled = useSteamPath
			stellarisPathTextField.isEnabled = !useSteamPath
			stellarisPathTextField.isEnabled = !useSteamPath
		}
	}
	private val steamPathLabel = JBLabel(message("stellaris.settings.steamPath"))
	private val steamPathTextFieldBacking = JBTextField()
	val steamPathTextField = TextFieldWithBrowseButton(steamPathTextFieldBacking)
	private val stellarisPathLabel = JBLabel(message("stellaris.settings.stellarisPath"))
	private val stellarisPathTextFieldBacking = JBTextField()
	val stellarisPathTextField = TextFieldWithBrowseButton(stellarisPathTextFieldBacking)
	private val stellarisModsPathLabel = JBLabel(message("stellaris.settings.stellarisModsPath"))
	private val stellarisModsPathTextFieldBacking = JBTextField()
	val stellarisModsPathTextField = TextFieldWithBrowseButton(stellarisModsPathTextFieldBacking)

	val panel = FormBuilder.createFormBuilder()
		.addComponent(useSteamPathCheckBox, 1)
		.addLabeledComponent(steamPathLabel, steamPathTextField, 1, false)
		.addLabeledComponent(stellarisPathLabel, stellarisPathTextField, 1, false)
		.addLabeledComponent(stellarisModsPathLabel, stellarisModsPathLabel, 1, false)
		.panel
}

class AppSettingsConfigurable : Configurable {
	private var settingsComponent: AppSettingsComponent? = null

	override fun getDisplayName() = message("stellaris.settings")

	override fun isModified(): Boolean {
		val settingsComponent = settingsComponent!!
		val settings = AppSettingsState.instance
		return settingsComponent.useSteamPathCheckBox.isSelected != settings.useSteamPath
		       || settingsComponent.steamPathTextField.text != settings.steamPath
		       || settingsComponent.stellarisPathTextField.text != settings.stellarisPath
		       || settingsComponent.stellarisModsPathTextField.text != settings.stellarisModsPath
	}

	override fun createComponent(): JComponent? {
		val component = AppSettingsComponent()
		settingsComponent = component
		return component.panel
	}

	override fun disposeUIResources() {
		settingsComponent = null
	}

	override fun apply() {
		val settingsComponent = settingsComponent!!
		val settings = AppSettingsState.instance
		settings.useSteamPath = settingsComponent.useSteamPathCheckBox.isSelected
		settings.steamPath = settingsComponent.steamPathTextField.text
		settings.stellarisPath = settingsComponent.stellarisPathTextField.text
		settings.stellarisModsPath = settingsComponent.stellarisModsPathTextField.text
	}

	override fun reset() {
		val settingsComponent = settingsComponent!!
		val settings = AppSettingsState.instance
		settingsComponent.useSteamPathCheckBox.isSelected = settings.useSteamPath
		settingsComponent.steamPathTextField.text = settings.steamPath
		settingsComponent.steamPathTextField.isEnabled = settings.useSteamPath
		settingsComponent.stellarisPathTextField.text = settings.stellarisPath
		settingsComponent.stellarisPathTextField.isEnabled = !settings.useSteamPath
		settingsComponent.stellarisModsPathTextField.text = settings.stellarisModsPath
		settingsComponent.stellarisModsPathTextField.isEnabled = !settings.useSteamPath
	}
}
