package com.windea.plugin.idea.stellaris

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.fileTypes.impl.*
import com.intellij.openapi.vfs.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.script.*
import java.util.concurrent.*

@Suppress("UnstableApiUsage")
class StellarisFileTypeOverrider : FileTypeOverrider {
	companion object {
		private const val stellarisExeFileName = "stellaris.exe"
		private const val descriptorModFileName = "descriptor.mod"
		private val fileExtensions = arrayOf("yml", "txt", "mod", "gui", "gfx", "asset")
		private val localizationFileExtensions = arrayOf("yml","yaml")
		private val scriptFileExtensions = arrayOf("txt","mod","gfx","gui","asset")
	}

	//仅当从所在目录下找到stellaris.exe或者descriptor.mod文件时
	//才有可能将所在目录（以及子目录）下的文件识别为Stellaris本地化文件和脚本文件

	override fun getOverriddenFileType(file: VirtualFile): FileType? {
		val isValid = isValidFileNameForOverridden(file.name)
		if(!isValid) return null

		var currentFile: VirtualFile? = file.parent
		while(currentFile != null) {
			//如果是游戏或模组目录
			val isGameOrModDirectory = currentFile.children.any {
				it.name == descriptorModFileName || it.name == stellarisExeFileName
			}
			if(isGameOrModDirectory) {
				when(file.extension) {
					in localizationFileExtensions -> return StellarisLocalizationFileType
					in scriptFileExtensions -> return StellarisScriptFileType
				}
			}
			currentFile = currentFile.parent
		}
		return null
	}

	private fun isValidFileNameForOverridden(fileName:String):Boolean{
		val index = fileName.lastIndexOf('.')
		if(index == -1) return false
		val fileExtension = fileName.substring(index+1,fileName.length)
		return fileExtension in fileExtensions
	}
}
