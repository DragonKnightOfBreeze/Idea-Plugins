package com.windea.plugin.idea.stellaris.settings

import com.intellij.openapi.ui.*
import com.intellij.ui.components.*
import com.intellij.util.ui.*
import com.windea.plugin.idea.stellaris.*

/**
 * * 是否使用steam路径
 * * steam路径
 * * stellaris路径
 * * stellaris mods路径
 */
class StellarisSettingsComponent {
	val useSteamPathCheckBox = JBCheckBox(StellarisBundle.message("stellaris.settings.useSteamPath")).apply {
		addChangeListener {
			println(it)
			val useSteamPath = it.source as Boolean
			steamPathTextField.isEnabled = useSteamPath
			stellarisPathTextField.isEnabled = !useSteamPath
			stellarisPathTextField.isEnabled = !useSteamPath
		}
	}
	private val steamPathLabel = JBLabel(StellarisBundle.message("stellaris.settings.steamPath"))
	private val steamPathTextFieldBacking = JBTextField()
	val steamPathTextField = TextFieldWithBrowseButton(steamPathTextFieldBacking)
	private val stellarisPathLabel = JBLabel(StellarisBundle.message("stellaris.settings.stellarisPath"))
	private val stellarisPathTextFieldBacking = JBTextField()
	val stellarisPathTextField = TextFieldWithBrowseButton(stellarisPathTextFieldBacking)
	private val stellarisModsPathLabel = JBLabel(StellarisBundle.message("stellaris.settings.stellarisModsPath"))
	private val stellarisModsPathTextFieldBacking = JBTextField()
	val stellarisModsPathTextField = TextFieldWithBrowseButton(stellarisModsPathTextFieldBacking)

	val panel = FormBuilder.createFormBuilder()
		.addComponent(useSteamPathCheckBox, 1)
		.addLabeledComponent(steamPathLabel, steamPathTextField, 1, false)
		.addLabeledComponent(stellarisPathLabel, stellarisPathTextField, 1, false)
		.addLabeledComponent(stellarisModsPathLabel, stellarisModsPathLabel, 1, false)
		.panel
}
