package com.windea.plugin.idea.paradox.library

import com.intellij.openapi.roots.*
import com.intellij.openapi.roots.libraries.*
import com.intellij.openapi.roots.ui.configuration.libraryEditor.*
import com.intellij.openapi.vfs.*

class ParadoxNewLibraryConfiguration(
	private val libraryType: StellarisLibraryType,
	private val libraryProperties: ParadoxLibraryProperties,
	private val libraryFile: VirtualFile
): NewLibraryConfiguration(libraryProperties.name){
	override fun getLibraryType() = libraryType
	
	override fun getProperties() = libraryProperties
	
	//TODO 区分脚本文件和本地化文件，仅添加有效的
	override fun addRoots(editor: LibraryEditor) {
		editor.addRoot(libraryFile, OrderRootType.SOURCES)
	}
}