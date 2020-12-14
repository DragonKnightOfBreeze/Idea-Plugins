package com.windea.plugin.idea.paradox.library

import com.intellij.openapi.fileChooser.*
import com.intellij.openapi.project.*
import com.intellij.openapi.roots.libraries.*
import com.intellij.openapi.roots.libraries.ui.*
import com.intellij.openapi.vfs.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.util.*
import javax.swing.*

abstract class ParadoxLibraryType(
	libraryKind: ParadoxLibraryKind,
	private val type:String,
	private val _icon: Icon
): LibraryType<ParadoxLibraryProperties>(libraryKind){
	//可以是一个zip文件，也可以是一个文件夹，但必须包含descriptor.mod或者stellaris.exe
	
	override fun createNewLibrary(parentComponent: JComponent, contextDirectory: VirtualFile?, project: Project): NewLibraryConfiguration? {
		if(contextDirectory == null) return null
		val chooserDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
		chooserDescriptor.title = message("paradox.library.chooser.title")
		chooserDescriptor.description = message("paradox.library.chooser.description")
		val file = FileChooser.chooseFile(chooserDescriptor, parentComponent, project, contextDirectory) ?: return null
		val libraryProperties = getLibraryProperties(contextDirectory,project)?:return null
		val name = libraryProperties.getOrDefault("name","$type Stdlib").toString()
		return ParadoxNewLibraryConfiguration(name,this, libraryProperties, file)
	}
	
	private fun getLibraryProperties(contextDirectory:VirtualFile,project:Project):ParadoxLibraryProperties?{
		val descriptorFile = contextDirectory.findChild(descriptorModFileName)
		if(descriptorFile != null){
			val properties = ParadoxScriptDataExtractor.extract(PsiUtilCore.getPsiFile(project,descriptorFile))
			return ParadoxLibraryProperties(handleProperties(properties))
		}else{
			val exeFile = contextDirectory.findChild("${type.toLowerCase()}.exe")
			if(exeFile != null){
				return ParadoxLibraryProperties()
			}
		}
		return null
	}
	
	private fun handleProperties(properties:List<Any>):Map<String,Any?>{
		val map = mutableMapOf<String,Any?>()
		for(property in properties) {
			if(property is Pair<*,*>){
				val (k,v) = property
				map[k.toString()] = v
			}
		}
		return map
	}
	
	override fun createPropertiesEditor(editorComponent: LibraryEditorComponent<ParadoxLibraryProperties>) = null
	
	override fun getCreateActionName() = type
	
	override fun getIcon(properties: ParadoxLibraryProperties?) = _icon
}

class StellarisLibraryType:ParadoxLibraryType(StellarisLibraryKind,stellarisName,stellarisIcon)

//TODO Other Games