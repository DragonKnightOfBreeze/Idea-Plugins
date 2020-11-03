package com.windea.plugin.idea.stellaris

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.util.io.*
import com.intellij.openapi.vfs.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.script.*

@Deprecated("")
class StellarisFileTypeDetector :FileTypeRegistry.FileTypeDetector{
	override fun detect(file: VirtualFile, firstBytes: ByteSequence, firstCharsIfText: CharSequence?): FileType? {
		var currentFile: VirtualFile? = file.parent
		while(currentFile != null){
			if(currentFile.findChild(descriptorFileName) != null){
				when(file.extension){
					"txt","mod","gfx","asset","gui" -> return StellarisScriptFileType
					"yml" -> return StellarisLocalizationFileType
				}
			}
			currentFile = currentFile.parent
		}
		return null
	}
}
