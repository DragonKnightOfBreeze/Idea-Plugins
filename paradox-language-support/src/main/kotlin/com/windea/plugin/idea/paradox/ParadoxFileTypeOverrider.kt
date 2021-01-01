package com.windea.plugin.idea.paradox

import com.intellij.openapi.fileTypes.impl.*
import com.intellij.openapi.vfs.*
import com.windea.plugin.idea.paradox.localisation.*
import com.windea.plugin.idea.paradox.model.*
import com.windea.plugin.idea.paradox.script.*

@Suppress("UnstableApiUsage")
class ParadoxFileTypeOverrider : FileTypeOverrider {
	//仅当从所在目录下找到exe文件或者descriptor.mod文件时
	//才有可能将所在目录（以及子目录）下的文件识别为Paradox本地化文件和脚本文件
	
	override fun getOverriddenFileType(file: VirtualFile): com.intellij.openapi.fileTypes.FileType? {
		val fileType = getFileType(file) ?: return null
		val subPaths = mutableListOf(file.name)
		var currentFile: VirtualFile? = file.parent
		while(currentFile != null) {
			//只有能够确定根目录类型的文件才会被解析
			val rootType = getRooType(currentFile)
			if(rootType != null) {
				//忽略描述符文件和cwt文件
				if(file.name != descriptorFileName && file.extension != "cwt") {
					val path = getPath(subPaths)
					putUserData(file, fileType, rootType, path)
				}
				return when(fileType) {
					ParadoxFileType.Script -> ParadoxScriptFileType
					ParadoxFileType.Localisation -> ParadoxLocalisationFileType
				}
			}
			subPaths.add(0, currentFile.name)
			currentFile = currentFile.parent
		}
		cleanupUserData(file)
		return null
	}
	
	private fun getFileType(file: VirtualFile): ParadoxFileType? {
		if(!file.isDirectory) {
			val fileName = file.name.toLowerCase()
			val fileExtension = fileName.substringAfterLast('.')
			return when {
				fileName in ignoredFileNames -> null //某些特殊名字的文件会被忽略
				fileExtension in scriptFileExtensions -> ParadoxFileType.Script
				fileExtension in localisationFileExtensions -> ParadoxFileType.Localisation
				else -> null
			}
		}
		return null
	}
	
	private fun getRooType(file: VirtualFile): ParadoxRootType? {
		if(!file.isDirectory) return null
		for(child in file.children) {
			val name = child.name
			when {
				gameNames.any { gameName ->
					file.nameWithoutExtension.equals(gameName, true) && file.extension.equals("exe", true)
				} -> return ParadoxRootType.Stdlib
				name.equals(descriptorFileName, true) -> return ParadoxRootType.Mod
			}
		}
		return null
	}
	
	private fun getPath(subPaths: List<String>): ParadoxPath {
		return ParadoxPath(subPaths)
	}
	
	private fun putUserData(file: VirtualFile, fileType: ParadoxFileType, rootType: ParadoxRootType, path: ParadoxPath) {
		try {
			if(file.isValid) {
				file.putUserData(paradoxFileTypeKey, fileType)
				file.putUserData(paradoxRootTypeKey, rootType)
				file.putUserData(paradoxGameTypeKey, ParadoxGameType.Stellaris) //TODO
				file.putUserData(paradoxPathKey, path)
			}
		} catch(e: Exception) {
			//e.printStackTrace()
		}
	}
	
	private fun cleanupUserData(file: VirtualFile) {
		try {
			if(file.isValid) {
				file.putUserData(paradoxFileTypeKey, null)
				file.putUserData(paradoxRootTypeKey, null)
				file.putUserData(paradoxGameTypeKey, null)
				file.putUserData(paradoxPathKey, null)
			}
		} catch(e: Exception) {
			//e.printStackTrace()
		}
	}
}

