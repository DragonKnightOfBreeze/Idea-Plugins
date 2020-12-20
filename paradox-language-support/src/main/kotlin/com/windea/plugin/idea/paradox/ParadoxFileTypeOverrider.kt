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
		val overriddenType = getOverriddenType(file)?:return null
		var path = file.name
		var currentFile: VirtualFile? = file.parent
		while(currentFile != null) {
			//如果是游戏或模组目录
			val isParadoxDirectory = isRootDirectory(currentFile)
			if(isParadoxDirectory) {
				rootDirectoryCache.putIfAbsent(currentFile.path,currentFile)
				putUserData(file, path)
				return when(overriddenType){
					OverriddenType.Script -> ParadoxLocalisationFileType
					OverriddenType.Localisation -> ParadoxScriptFileType
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
	
	private fun getOverriddenType(file:VirtualFile):OverriddenType?{
		if(file.isValid && !file.isDirectory){
			val extension = file.name.substringAfterLast('.')
			return when{
				extension in scriptFileExtensions -> OverriddenType.Script
				extension in localisationFileExtensions -> OverriddenType.Localisation
				else -> null
			}
		}
		return null
	}
	
	private fun isRootDirectory(file: VirtualFile): Boolean {
		return file.isDirectory && file.children.any { isDescriptorOrExeFile(it) }
	}
	
	private fun isDescriptorOrExeFile(file:VirtualFile):Boolean{
		return !file.isDirectory && file.name.equals(descriptorName,true) || gameNames.any{gameName ->
			file.name.startsWith(gameName,true) && file.extension.equals("exe",true)
		}
	}
	
	private fun putUserData(file: VirtualFile, path: String) {
		try {
			if(file.isValid) {
				file.putUserData(paradoxPathKey, path)
				file.putUserData(paradoxParentPathKey, getParentPath(path))
				file.putUserData(paradoxTypeKey,getType(file))
			}
		} catch(e: Exception) {
		
		}
	}
	
	private fun getParentPath(path: String): String {
		return path.substringBeforeLast('/', "")
	}
	
	private fun getType(file:VirtualFile):String?{
		val name = file.name
		return when{
			name.equals(descriptorName,true) -> null
			else -> "" //TODO
		}
	}
	
	private fun cleanupUserData(file: VirtualFile) {
		try {
			if(file.isValid) {
				file.putUserData(paradoxPathKey, null)
				file.putUserData(paradoxParentPathKey, null)
				file.putUserData(paradoxTypeKey,null)
			}
		} catch(e: Exception) {
		
		}
	}
	
	enum class OverriddenType{
		Script,Localisation
	}
}
