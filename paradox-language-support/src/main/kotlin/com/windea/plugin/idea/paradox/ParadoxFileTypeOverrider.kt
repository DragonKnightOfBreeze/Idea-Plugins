package com.windea.plugin.idea.paradox

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.fileTypes.impl.*
import com.intellij.openapi.vfs.*
import com.windea.plugin.idea.paradox.localisation.*
import com.windea.plugin.idea.paradox.script.*

@Suppress("UnstableApiUsage")
class ParadoxFileTypeOverrider : FileTypeOverrider {
	//仅当从所在目录下找到exe文件或者descriptor.mod文件时
	//才有可能将所在目录（以及子目录）下的文件识别为Paradox本地化文件和脚本文件
	
	override fun getOverriddenFileType(file: VirtualFile): FileType? {
		if(!isValidFileNameForOverridden(file.name)) return null
		var path = file.name
		var currentFile: VirtualFile? = file.parent
		while(currentFile != null) {
			//如果是游戏或模组目录
			val isParadoxDirectory = currentFile.isRootDirectory
			if(isParadoxDirectory) {
				rootDirectoryCache[currentFile.path] = currentFile
				when(file.extension) {
					in localisationFileExtensions -> {
						putUserData(file, path)
						return ParadoxLocalisationFileType
					}
					in scriptFileExtensions -> {
						putUserData(file, path)
						return ParadoxScriptFileType
					}
				}
			} else {
				rootDirectoryCache.remove(currentFile.path)
			}
			path = currentFile.name + '/' + path
			currentFile = currentFile.parent
		}
		cleanupUserData(file)
		return null
	}
	
	private fun putUserData(file: VirtualFile, path: String) {
		val parentPath = path.substringBeforeLast('/', "")
		file.putUserData(paradoxPathKey, path)
		file.putUserData(paradoxParentPathKey, parentPath)
	}
	
	private fun cleanupUserData(file: VirtualFile) {
		file.putUserData(paradoxPathKey, null)
		file.putUserData(paradoxParentPathKey, null)
	}
	
	private fun isValidFileNameForOverridden(fileName: String): Boolean {
		val index = fileName.lastIndexOf('.')
		if(index == -1) return false
		val fileExtension = fileName.substring(index + 1, fileName.length)
		return fileExtension in fileExtensions
	}
}
