package com.windea.plugin.idea.stellaris

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.fileTypes.impl.*
import com.intellij.openapi.vfs.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.script.*



@Suppress("UnstableApiUsage")
class StellarisFileTypeOverrider : FileTypeOverrider {
	//仅当从所在目录下找到stellaris.exe或者descriptor.mod文件时
	//才有可能将所在目录（以及子目录）下的文件识别为Stellaris本地化文件和脚本文件

	override fun getOverriddenFileType(file: VirtualFile): FileType? {
		if(!isValidFileNameForOverridden(file.name)) return null
		var path = file.name
		var currentFile:VirtualFile? = file.parent
		while(currentFile != null) {
			//如果是游戏或模组目录
			val isStellarisDirectory = currentFile.isStellarisDirectory()
			if(isStellarisDirectory) {
				stellarisDirectoryCache.add(currentFile)
				when(file.extension) {
					in localizationFileExtensions -> {
						filePathCache[file] = path
						return StellarisLocalizationFileType
					}
					in scriptFileExtensions -> {
						filePathCache[file] = path
						return StellarisScriptFileType
					}
				}
			}else{
				stellarisDirectoryCache.remove(currentFile)
			}
			path = currentFile.name + "/" + path
			currentFile = currentFile.parent
		}
		filePathCache.remove(file)
		return null
	}

	private fun isValidFileNameForOverridden(fileName:String):Boolean{
		val index = fileName.lastIndexOf('.')
		if(index == -1) return false
		val fileExtension = fileName.substring(index+1,fileName.length)
		return fileExtension in fileExtensions
	}
}
