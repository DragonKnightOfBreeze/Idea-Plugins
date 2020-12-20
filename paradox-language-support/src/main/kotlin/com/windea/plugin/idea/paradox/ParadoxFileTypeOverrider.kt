package com.windea.plugin.idea.paradox

import com.intellij.openapi.fileTypes.impl.*
import com.intellij.openapi.vfs.*
import com.windea.plugin.idea.paradox.localisation.*
import com.windea.plugin.idea.paradox.script.*

@Suppress("UnstableApiUsage")
class ParadoxFileTypeOverrider : FileTypeOverrider {
	//仅当从所在目录下找到exe文件或者descriptor.mod文件时
	//才有可能将所在目录（以及子目录）下的文件识别为Paradox本地化文件和脚本文件
	
	override fun getOverriddenFileType(file: VirtualFile): com.intellij.openapi.fileTypes.FileType? {
		val overriddenType = getOverriddenType(file) ?: return null
		val pathList = mutableListOf(file.name)
		var currentFile: VirtualFile? = file.parent
		while(currentFile != null) {
			//只有能够确定根目录类型的文件才会被解析
			val rootDirectoryType = getRooDirectoryType(currentFile)
			if(rootDirectoryType != null) {
				//规则文件以及描述符文件不会解析路径和类型，因此也不会加入索引（包括cwtools的规则文件*.cwt）
				if(rootDirectoryType != RootDirectoryType.Rules && file.name != descriptorFileName && file.extension != "cwt") {
					putUserData(file, pathList)
				}
				return when(overriddenType) {
					FileType.Script -> ParadoxLocalisationFileType
					FileType.Localisation -> ParadoxScriptFileType
				}
			}
			pathList.add(0,currentFile.name)
			currentFile = currentFile.parent
		}
		cleanupUserData(file)
		return null
	}
	
	private fun getOverriddenType(file:VirtualFile): FileType?{
		if(file.isValid && !file.isDirectory){
			val fileName = file.name.toLowerCase()
			val fileExtension = fileName.substringAfterLast('.')
			return when{
				fileName in ignoredFileNames -> null //某些特殊名字的文件会被忽略
				fileExtension in scriptFileExtensions -> FileType.Script
				fileExtension in localisationFileExtensions -> FileType.Localisation
				else -> null
			}
		}
		return null
	}
	
	private fun getRooDirectoryType(file:VirtualFile):RootDirectoryType?{
		if(!file.isDirectory) return null
		for(child in file.children) {
			val name = child.name
			when{
				name.equals(descriptorFileName,true) -> return RootDirectoryType.Stdlib
				gameNames.any { gameName ->
					file.nameWithoutExtension.equals(gameName, true) && file.extension.equals("exe", true)
				} -> return RootDirectoryType.Mod
				name.equals(ruleMarkerFileName,true) -> return RootDirectoryType.Rules
			}
		}
		return null
	}
	
	private fun putUserData(file: VirtualFile, pathList: List<String>) {
		try {
			if(file.isValid) {
				val name = file.name
				val parentPathList =  pathList.dropLast(1)
				val path = getPath(pathList)
				val parentPath = getParentPath(parentPathList)
				val type = getType(pathList, parentPathList,name,parentPath)
				file.putUserData(paradoxPathKey, path)
				file.putUserData(paradoxParentPathKey, parentPath)
				file.putUserData(paradoxTypeKey, type)
			}
		} catch(e: Exception) {
		
		}
	}
	
	private fun getPath(pathList: List<String>): String {
		return pathList.joinToString("/")
	}
	
	private fun getParentPath(parentPathList: List<String>): String {
		return parentPathList.joinToString("/")
	}
	
	private fun getType(pathList:List<String>,parentPathList:List<String>,name:String,parentPath:String):String?{
		return "" //TODO
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
	
	enum class FileType{
		Script,Localisation
	}
	
	enum class RootDirectoryType{
		Stdlib,Mod,Rules
	}
}
