@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.pdx.settings

import com.intellij.openapi.fileChooser.*
import com.intellij.openapi.ui.*
import com.intellij.ui.*
import com.intellij.ui.components.*
import com.intellij.util.ui.*
import com.windea.plugin.idea.pdx.message
import javax.swing.*

//* 是否解析外部引用
//* 路径
//  * 是否使用steam路径
//  * steam路径
//  * pdx路径
//  * pdx mods路径

//TODO 验证这些路径

class PdxSettingsComponent {
	val resolveInternalReferencesCheckBox = JBCheckBox(message("pdx.settings.resolveInternalReferences"))
	val resolveExternalReferencesCheckBox = JBCheckBox(message("pdx.settings.resolveExternalReferences"))

	//val useSteamDirectoryCheckBox = JBCheckBox(message("pdx.settings.useSteamDirectory")).apply {
	//	addChangeListener {
	//		//it: JBCheckBox
	//		val useSteamDirectory = (it.source as JBCheckBox).isSelected
	//		steamDirectoryTextField.isEnabled = useSteamDirectory
	//		pdxDirectoryTextField.isEnabled = !useSteamDirectory
	//		pdxModsDirectoryTextField.isEnabled = !useSteamDirectory
	//	}
	//}
	//private val steamDirectoryLabel = JBLabel(message("pdx.settings.steamDirectory"))
	//private val steamDirectoryTextFieldBacking = JBTextField()
	//val steamDirectoryTextField = TextFieldWithBrowseButton(steamDirectoryTextFieldBacking)
	private val pdxDirectoryLabel = JBLabel(message("pdx.settings.pdxDirectory"))
	private val pdxDirectoryTextFieldBacking = JBTextField()
	val pdxDirectoryTextField = TextFieldWithBrowseButton(pdxDirectoryTextFieldBacking)
	//private val pdxModsDirectoryLabel = JBLabel(message("pdx.settings.pdxMods.Directory"))
	//private val pdxModsDirectoryTextFieldBacking = JBTextField()
	//val pdxModsDirectoryTextField = TextFieldWithBrowseButton(pdxModsDirectoryTextFieldBacking)

	private val pathsPanel = FormBuilder.createFormBuilder()
		//.addComponent(useSteamDirectoryCheckBox, 1)
		//.addLabeledComponent(steamDirectoryLabel, steamDirectoryTextField, 1, false)
		.addLabeledComponent(pdxDirectoryLabel, pdxDirectoryTextField, 1, false)
		//.addLabeledComponent(pdxModsDirectoryLabel, pdxModsDirectoryTextField, 1, false)
		.panel.apply {
			border = IdeBorderFactory.createTitledBorder(message("pdx.settings.paths"))
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
		//	message("pdx.settings.steamDirectory.select"),
		//	FileChooserDescriptorFactory.createSingleFileDescriptor()
		//)
		SwingHelper.installFileCompletionAndBrowseDialog(
			null,
			pdxDirectoryTextField,
			message("pdx.settings.pdxDirectory.select"),
			FileChooserDescriptorFactory.createSingleFileDescriptor()
		)
		//SwingHelper.installFileCompletionAndBrowseDialog(
		//	null,
		//	pdxModsDirectoryTextField,
		//	message("pdx.settings.pdxModsDirectory.select"),
		//	FileChooserDescriptorFactory.createSingleFileDescriptor()
		//)
	}
}
