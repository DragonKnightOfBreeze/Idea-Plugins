package com.windea.plugin.idea.paradox.library

import com.intellij.openapi.components.*
import com.intellij.openapi.roots.libraries.*
import com.intellij.util.xmlb.*

@State(name = "ParadoxLibraryProperties", storages = [Storage("paradoxLanguageSupport.xml")])
data class ParadoxLibraryProperties(
	var name:String = "unknown",
	var version:String = "unknown"
): LibraryProperties<ParadoxLibraryProperties>(){
	override fun getState() = this
	
	override fun loadState(state: ParadoxLibraryProperties) = XmlSerializerUtil.copyBean(state, this)
}