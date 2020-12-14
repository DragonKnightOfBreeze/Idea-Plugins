package com.windea.plugin.idea.paradox.library

import com.intellij.openapi.roots.libraries.ui.*
import com.intellij.ui.components.*
import com.intellij.util.ui.*
import com.windea.plugin.idea.paradox.*
import javax.swing.*

class ParadoxLibraryPropertiesEditor(
	private val editorComponent: LibraryEditorComponent<ParadoxLibraryProperties>
): LibraryPropertiesEditor(){
	private val nameTextField = JBTextField(message("paradox.library.properties.name"))
	private val versionTextField = JBTextField(message("paradox.library.properties.version"))
	private val panel = FormBuilder.createFormBuilder()
		.addComponent(nameTextField,1)
		.addComponent(versionTextField,1)
		.addComponentFillVertically(JPanel(), 0)
		.panel
	
	override fun createComponent(): JComponent {
		return panel
	}
	
	override fun isModified(): Boolean {
		return editorComponent.properties.name != nameTextField.text
		       || editorComponent.properties.version != versionTextField.text
	}
	
	override fun apply() {
		editorComponent.properties.name = nameTextField.text
		editorComponent.properties.version = versionTextField.text
	}
	
	override fun reset() {
		nameTextField.text = editorComponent.properties.name
		versionTextField.text = editorComponent.properties.version
	}
}