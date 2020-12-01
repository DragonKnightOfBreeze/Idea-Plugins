package com.windea.plugin.idea.pdx

import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.fileTypes.impl.*
import com.intellij.openapi.vfs.*
import com.windea.plugin.idea.pdx.localisation.*
import com.windea.plugin.idea.pdx.script.*

@Suppress("UnstableApiUsage")
class PdxFileTypeOverrider : FileTypeOverrider {
	//仅当从所在目录下找到pdx.exe或者descriptor.mod文件时
	//才有可能将所在目录（以及子目录）下的文件识别为Pdx本地化文件和脚本文件
	
	override fun getOverriddenFileType(file: VirtualFile): FileType? {
		if(!isValidFileNameForOverridden(file.name)) return null
		var path = file.name
		var currentFile: VirtualFile? = file.parent
		while(currentFile != null) {
			//如果是游戏或模组目录
			val isPdxDirectory = currentFile.isRootDirectory
			if(isPdxDirectory) {
				rootDirectoryCache[currentFile.path] = currentFile
				when(file.extension) {
					in localisationFileExtensions -> {
						putUserData(file, path)
						return PdxLocalisationFileType
					}
					in scriptFileExtensions -> {
						putUserData(file, path)
						return PdxScriptFileType
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
		file.putUserData(pdxPathKey, path)
		file.putUserData(pdxParentPathKey, parentPath)
	}
	
	private fun cleanupUserData(file: VirtualFile) {
		file.putUserData(pdxPathKey, null)
		file.putUserData(pdxParentPathKey, null)
	}
	
	private fun isValidFileNameForOverridden(fileName: String): Boolean {
		val index = fileName.lastIndexOf('.')
		if(index == -1) return false
		val fileExtension = fileName.substring(index + 1, fileName.length)
		return fileExtension in fileExtensions
	}
}
