@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.paradox.settings

import com.intellij.openapi.fileChooser.*
import com.intellij.openapi.ui.*
import com.intellij.ui.*
import com.intellij.ui.components.*
import com.intellij.util.ui.*
import com.windea.plugin.idea.paradox.message
import javax.swing.*

//* 是否解析外部引用
//* 路径
//  * 是否使用steam路径
//  * steam路径
//  * paradox路径
//  * paradox mods路径

//TODO 验证这些路径

class ParadoxSettingsComponent {
	val resolveInternalReferencesCheckBox = JBCheckBox(message("paradox.settings.resolveInternalReferences"))
	val resolveExternalReferencesCheckBox = JBCheckBox(message("paradox.settings.resolveExternalReferences"))

	//val useSteamDirectoryCheckBox = JBCheckBox(message("paradox.settings.useSteamDirectory")).apply {
	//	addChangeListener {
	//		//it: JBCheckBox
	//		val useSteamDirectory = (it.source as JBCheckBox).isSelected
	//		steamDirectoryTextField.isEnabled = useSteamDirectory
	//		paradoxDirectoryTextField.isEnabled = !useSteamDirectory
	//		paradoxModsDirectoryTextField.isEnabled = !useSteamDirectory
	//	}
	//}
	//private val steamDirectoryLabel = JBLabel(message("paradox.settings.steamDirectory"))
	//private val steamDirectoryTextFieldBacking = JBTextField()
	//val steamDirectoryTextField = TextFieldWithBrowseButton(steamDirectoryTextFieldBacking)
	private val paradoxDirectoryLabel = JBLabel(message("paradox.settings.paradoxDirectory"))
	private val paradoxDirectoryTextFieldBacking = JBTextField()
	val paradoxDirectoryTextField = TextFieldWithBrowseButton(paradoxDirectoryTextFieldBacking)
	//private val paradoxModsDirectoryLabel = JBLabel(message("paradox.settings.paradoxMods.Directory"))
	//private val paradoxModsDirectoryTextFieldBacking = JBTextField()
	//val paradoxModsDirectoryTextField = TextFieldWithBrowseButton(paradoxModsDirectoryTextFieldBacking)

	private val pathsPanel = FormBuilder.createFormBuilder()
		//.addComponent(useSteamDirectoryCheckBox, 1)
		//.addLabeledComponent(steamDirectoryLabel, steamDirectoryTextField, 1, false)
		.addLabeledComponent(paradoxDirectoryLabel, paradoxDirectoryTextField, 1, false)
		//.addLabeledComponent(paradoxModsDirectoryLabel, paradoxModsDirectoryTextField, 1, false)
		.panel.apply {
			border = IdeBorderFactory.createTitledBorder(message("paradox.settings.paths"))
		}

	val panel = FormBuilder.createFormBuilder()
		.addComponent(resolveInternalReferencesCheckBox,1)
		.addComponent(resolveExternalReferencesCheckBox,1)
		.addComponent(pathsPanel)
		.addComponentFillVertically(JPanel(), 0)
		.panel

	init {
		//SwingHelper.installFileCompletionAndBrowseDialog(
		//	null,
		//	steamDirectoryTextField,
		//	message("paradox.settings.steamDirectory.select"),
		//	FileChooserDescriptorFactory.createSingleFileDescriptor()
		//)
		SwingHelper.installFileCompletionAndBrowseDialog(
			null,
			paradoxDirectoryTextField,
			message("paradox.settings.paradoxDirectory.select"),
			FileChooserDescriptorFactory.createSingleFileDescriptor()
		)
		//SwingHelper.installFileCompletionAndBrowseDialog(
		//	null,
		//	paradoxModsDirectoryTextField,
		//	message("paradox.settings.paradoxModsDirectory.select"),
		//	FileChooserDescriptorFactory.createSingleFileDescriptor()
		//)
	}
}
