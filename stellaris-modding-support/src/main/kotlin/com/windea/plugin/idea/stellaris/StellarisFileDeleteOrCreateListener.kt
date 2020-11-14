package com.windea.plugin.idea.stellaris

import com.intellij.openapi.vfs.newvfs.*
import com.intellij.openapi.vfs.newvfs.events.*
import java.io.*

class StellarisFileDeleteOrCreateListener : BulkFileListener {
	override fun after(events: MutableList<out VFileEvent>) {
		//当删除游戏或模组目录中的文件时，重新构建本地化和脚本文件缓存
		//DELAY 直接更改缓存而非重建缓存-目前代码结构下可能存在一些问题
		for(event in events) {
			if(event is VFileDeleteEvent || event is VFileCreateEvent) {
				val path = event.path
				val fileName = event.path.substringAfterLast(File.separator)
				when {
					//如果删除或创建的文件是stellaris.exe或descriptor.mod，则重建文件缓存
					fileName == stellarisExeFileName || fileName == descriptorModFileName -> {
						shouldRebuildLocalizationFileCache = true
						localizationFileCache.clear()
						shouldRebuildScriptFileCache = true
						scriptFileCache.clear()
					}
					//如果删除或创建的文件在游戏或模组目录中，则重建文件缓存
					rootDirectoryPathCache.any { path.contains(it) } -> {
						//根据文件的扩展名来判断
						val fileExtension = fileName.substringAfterLast('.')
						if(fileExtension in localizationFileExtensions) {
							shouldRebuildLocalizationFileCache = true
							localizationFileCache.clear()
						} else if (fileExtension in scriptFileExtensions){
							shouldRebuildScriptFileCache = true
							scriptFileCache.clear()
						}
					}
				}
			}
		}
	}
}
