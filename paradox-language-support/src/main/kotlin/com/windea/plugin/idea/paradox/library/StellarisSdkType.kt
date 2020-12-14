package com.windea.plugin.idea.paradox.library

import com.intellij.openapi.fileChooser.*
import com.intellij.openapi.project.*
import com.intellij.openapi.roots.libraries.*
import com.intellij.openapi.roots.libraries.ui.*
import com.intellij.openapi.vfs.*
import com.windea.plugin.idea.paradox.*
import javax.swing.*


class StellarisLibraryKind:PersistentLibraryKind<ParadoxLibraryProperties>(stellarisName) {
	override fun createDefaultProperties(): ParadoxLibraryProperties {
		return ParadoxLibraryProperties()
	}
}

class StellarisLibraryType:LibraryType<ParadoxLibraryProperties>(StellarisLibraryKind()){
	//可以是一个zip文件，也可以是一个文件夹，但必须包含descriptor.mod或者stellaris.exe
	
	override fun createNewLibrary(parentComponent: JComponent, contextDirectory: VirtualFile?, project: Project): NewLibraryConfiguration? {
		val chooserDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
		chooserDescriptor.title = message("paradox.library.chooser.title")
		chooserDescriptor.description = message("paradox.library.chooser.description")
		val file = FileChooser.chooseFile(chooserDescriptor,parentComponent,project,contextDirectory) ?: return null
		val properties = getLibraryProperties(file)?:return null
		return ParadoxNewLibraryConfiguration(this,properties,file)
	}
	
	private fun getLibraryProperties(file: VirtualFile):ParadoxLibraryProperties?{
		var name = "unknown"
		var version = "unknown"
		
		val descriptorFile = file.findChild(descriptorModFileName)
		if(descriptorFile != null) {
			val text = descriptorFile.inputStream.reader().use{ it.readText() }
			for(line in text.lines()) {
				when {
					line.startsWith("name") -> name = line.substringAfter('=').trim().unquote()
					line.startsWith("version") -> version = line.substringAfter('=').trim().unquote()
				}
			}
			return ParadoxLibraryProperties(name,version)
		}else{
			val exeFile = file.findChild(stellarisExeFileName)
			if(exeFile != null){
				name = "Stellaris Stdlib"
				return ParadoxLibraryProperties(name,version)
			}
		}
		return null
	}
	
	override fun createPropertiesEditor(editorComponent: LibraryEditorComponent<ParadoxLibraryProperties>): LibraryPropertiesEditor {
		return ParadoxLibraryPropertiesEditor(editorComponent)
	}
	
	override fun getCreateActionName(): String {
		return message("paradox.library.type.stellaris")
	}
	
	override fun getIcon(properties: ParadoxLibraryProperties?): Icon {
		return stellarisIcon
	}
}

//class StellarisSdkType: SdkType(stellarisName) {
//	override fun saveAdditionalData(additionalData: SdkAdditionalData, additional: Element) {}
//
//	override fun suggestHomePath() = null
//
//	override fun isValidSdkHome(path: String?) = true
//
//	override fun suggestSdkName(currentSdkName: String?, sdkHome: String?) = "Stellaris"
//
//	override fun createAdditionalDataConfigurable(sdkModel: SdkModel, sdkModificator: SdkModificator) = null
//
//	override fun getPresentableName(): String = stellarisName
//
//	override fun getIcon() = stellarisIcon
//
//	override fun setupSdkPaths(sdk: Sdk) {
//		val modificator = sdk.sdkModificator
//		modificator.setVersionString(getVersionString(sdk))
//		modificator.commitChanges() // save
//	}
//}