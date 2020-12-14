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
	
	val panel = FormBuilder.createFormBuilder()
		.addComponent(resolveInternalReferencesCheckBox,1)
		.addComponent(resolveExternalReferencesCheckBox,1)
		.addComponentFillVertically(JPanel(), 0)
		.panel
	
}
