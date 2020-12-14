package com.windea.plugin.idea.paradox.library

import com.intellij.openapi.roots.*
import com.intellij.openapi.roots.libraries.*
import com.intellij.openapi.roots.ui.configuration.libraryEditor.*
import com.intellij.openapi.vfs.*

class ParadoxNewLibraryConfiguration(
	name:String,
	libraryType: ParadoxLibraryType,
	private val libraryFile: VirtualFile,
	libraryProperties :ParadoxLibraryProperties = ParadoxLibraryProperties.instance
): NewLibraryConfiguration(name,libraryType,libraryProperties){
	//TODO 区分脚本文件和本地化文件，仅添加有效的
	override fun addRoots(editor: LibraryEditor) {
		editor.addRoot(libraryFile, OrderRootType.SOURCES)
	}
}