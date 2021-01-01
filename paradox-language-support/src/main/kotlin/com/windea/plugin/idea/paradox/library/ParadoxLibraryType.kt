package com.windea.plugin.idea.paradox.library

import com.intellij.openapi.fileChooser.*
import com.intellij.openapi.project.*
import com.intellij.openapi.roots.*
import com.intellij.openapi.roots.libraries.*
import com.intellij.openapi.roots.libraries.ui.*
import com.intellij.openapi.ui.*
import com.intellij.openapi.vfs.*
import com.windea.plugin.idea.paradox.*
import javax.swing.*

abstract class ParadoxLibraryType(
	libraryKind: ParadoxLibraryKind,
	private val libraryIcon: Icon,
	private val type: String
) : LibraryType<ParadoxLibraryProperties>(libraryKind) {
	private val createActionName = "$paradoxName/$type"
	private val namePrefix = "$createActionName: "
	
	companion object {
		private val _chooserTitle =  message("paradox.library.chooser.title")
		private val _chooserDescription =  message("paradox.library.chooser.description")
		private val _ivalidLibraryPathMessage = message("paradox.library.dialog.invalidLibraryPath.message")
		private val _invalidLibraryPathTitle = message("paradox.library.dialog.invalidLibraryPath.title")
	}
	
	//必须是一个文件夹，但必须包含descriptor.mod或者.exe文件
	override fun createNewLibrary(parentComponent: JComponent, contextDirectory: VirtualFile?, project: Project): NewLibraryConfiguration? {
		if(contextDirectory == null) return null
		val chooserDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
		chooserDescriptor.title = _chooserTitle
		chooserDescriptor.description = _chooserDescription
		val file = FileChooser.chooseFile(chooserDescriptor, parentComponent, project, contextDirectory) ?: return null
		val name = getLibraryName(file, project) ?: return null
		return ParadoxNewLibraryConfiguration(namePrefix + name, this, file)
	}
	
	//如果存在描述符文件，其中有name属性则取name属性的值，否则取库的文件名/目录
	//如果存在游戏执行文件，则认为是标准库，否则认为不是一个合法的库
	private fun getLibraryName(file: VirtualFile, project: Project): String? {
		for(child in file.children) {
			when {
				child.name.equals(descriptorFileName, true) -> {
					val text = child.inputStream.reader().use { it.readText() }
					for(line in text.lines()) {
						if(line.startsWith("name")) {
							return line.substringAfter('=').trim().unquote().trim()
						}
					}
					return file.nameWithoutExtension
				}
				child.nameWithoutExtension.equals(type, true) && child.extension.equals("exe", true) -> {
					return stdlibName
				}
			}
		}
		showInvalidLibraryDialog(project)
		return null
	}
	
	private fun showInvalidLibraryDialog(project: Project) {
		Messages.showWarningDialog(project, _ivalidLibraryPathMessage, _invalidLibraryPathTitle)
	}
	
	override fun createPropertiesEditor(editorComponent: LibraryEditorComponent<ParadoxLibraryProperties>) = null
	
	override fun getCreateActionName() = createActionName
	
	override fun getIcon(properties: ParadoxLibraryProperties?) = libraryIcon
	
	override fun getExternalRootTypes() = arrayOf(OrderRootType.SOURCES)
}