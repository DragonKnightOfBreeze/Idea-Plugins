@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.stellaris.settings

import com.intellij.openapi.fileChooser.*
import com.intellij.openapi.ui.*
import com.intellij.ui.*
import com.intellij.ui.components.*
import com.intellij.util.ui.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import javax.swing.*
import javax.swing.text.*

//* 是否解析外部引用
//* 路径
//  * 是否使用steam路径
//  * steam路径
//  * stellaris路径
//  * stellaris mods路径

//TODO 验证这些路径

class StellarisSettingsComponent {
	val resolveInternalReferencesCheckBox = JBCheckBox(message("stellaris.settings.resolveInternalReferences"))
	val resolveExternalReferencesCheckBox = JBCheckBox(message("stellaris.settings.resolveExternalReferences"))

	val useSteamDirectoryCheckBox = JBCheckBox(message("stellaris.settings.useSteamDirectory")).apply {
		addChangeListener {
			//it: JBCheckBox
			val useSteamDirectory = (it.source as JBCheckBox).isSelected
			steamDirectoryTextField.isEnabled = useSteamDirectory
			stellarisDirectoryTextField.isEnabled = !useSteamDirectory
			stellarisModsDirectoryTextField.isEnabled = !useSteamDirectory
		}
	}
	private val steamDirectoryLabel = JBLabel(message("stellaris.settings.steamDirectory"))
	private val steamDirectoryTextFieldBacking = JBTextField()
	val steamDirectoryTextField = TextFieldWithBrowseButton(steamDirectoryTextFieldBacking)
	private val stellarisDirectoryLabel = JBLabel(message("stellaris.settings.stellarisDirectory"))
	private val stellarisDirectoryTextFieldBacking = JBTextField()
	val stellarisDirectoryTextField = TextFieldWithBrowseButton(stellarisDirectoryTextFieldBacking)
	private val stellarisModsDirectoryLabel = JBLabel(message("stellaris.settings.stellarisMods.Directory"))
	private val stellarisModsDirectoryTextFieldBacking = JBTextField()
	val stellarisModsDirectoryTextField = TextFieldWithBrowseButton(stellarisModsDirectoryTextFieldBacking)

	private val pathsPanel = FormBuilder.createFormBuilder()
		.addComponent(useSteamDirectoryCheckBox, 1)
		.addLabeledComponent(steamDirectoryLabel, steamDirectoryTextField, 1, false)
		.addLabeledComponent(stellarisDirectoryLabel, stellarisDirectoryTextField, 1, false)
		.addLabeledComponent(stellarisModsDirectoryLabel, stellarisModsDirectoryTextField, 1, false)
		.panel.apply {
			border = IdeBorderFactory.createTitledBorder(message("stellaris.settings.paths"))
		}

	val panel = FormBuilder.createFormBuilder()
		.addComponent(resolveInternalReferencesCheckBox,1)
		.addComponent(resolveExternalReferencesCheckBox,1)
		.addComponent(pathsPanel)
		.addComponentFillVertically(JPanel(), 0)
		.panel

	init {
		SwingHelper.installFileCompletionAndBrowseDialog(
			null,
			steamDirectoryTextField,
			message("stellaris.settings.steamDirectory.select"),
			FileChooserDescriptorFactory.createSingleFileDescriptor()
		)
		SwingHelper.installFileCompletionAndBrowseDialog(
			null,
			stellarisDirectoryTextField,
			message("stellaris.settings.stellarisDirectory.select"),
			FileChooserDescriptorFactory.createSingleFileDescriptor()
		)
		SwingHelper.installFileCompletionAndBrowseDialog(
			null,
			stellarisModsDirectoryTextField,
			message("stellaris.settings.stellarisModsDirectory.select"),
			FileChooserDescriptorFactory.createSingleFileDescriptor()
		)
	}
}
