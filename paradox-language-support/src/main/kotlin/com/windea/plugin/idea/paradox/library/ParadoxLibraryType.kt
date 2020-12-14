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
	private val type:String,
	private val _icon: Icon
): LibraryType<ParadoxLibraryProperties>(libraryKind){
	private val _createActionName = "$paradoxName: $type"
	
	//可以是一个zip文件，也可以是一个文件夹，但必须包含descriptor.mod或者.exe文件
	
	override fun createNewLibrary(parentComponent: JComponent, contextDirectory: VirtualFile?, project: Project): NewLibraryConfiguration? {
		if(contextDirectory == null) return null
		val chooserDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
		chooserDescriptor.title = message("paradox.library.chooser.title")
		chooserDescriptor.description = message("paradox.library.chooser.description")
		val file = FileChooser.chooseFile(chooserDescriptor, parentComponent, project, contextDirectory) ?: return null
		val name = getLibraryName(file,project)?:return null
		return ParadoxNewLibraryConfiguration(name,this, file)
	}
	
	private fun getLibraryName(file: VirtualFile,project:Project):String?{
		//如果存在描述符文件，其中有name属性则取name属性的值，否则取库的文件名/目录
		//否则，如果存在游戏执行文件，则认为是标准库
		val rootFile = file.optimized()
		for(child in rootFile.children) {
			when {
				child.name.equals(descriptorModFileName,true) -> {
					val text = child.inputStream.reader().use{ it.readText() }
					for(line in text.lines()) {
						if(line.startsWith("name")) return line.substringAfter('=').trim().unquote().trim()
					}
					return rootFile.nameWithoutExtension
				}
				child.name.startsWith("stellaris",true) && child.extension.equals("exe",true) -> {
					return  "$type Stdlib"
				}
			}
		}
		showInvalidLibraryDialog(project)
		return null
	}
	
	override fun createPropertiesEditor(editorComponent: LibraryEditorComponent<ParadoxLibraryProperties>) = null
	
	override fun getCreateActionName() = _createActionName
	
	override fun getIcon(properties: ParadoxLibraryProperties?) = _icon
	
	override fun getExternalRootTypes() = arrayOf(OrderRootType.SOURCES)
}

private	val message = message("paradox.library.dialog.invalidLibraryPath.message")
private	val title = message("paradox.library.dialog.invalidLibraryPath.title")

private fun showInvalidLibraryDialog(project: Project) {
	Messages.showWarningDialog(project, message, title)
}

class StellarisLibraryType:ParadoxLibraryType(StellarisLibraryKind,stellarisName,stellarisIcon)

//TODO Other Games