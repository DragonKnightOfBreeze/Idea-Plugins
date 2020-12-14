package com.windea.plugin.idea.paradox.library

import com.intellij.openapi.components.*
import com.intellij.openapi.roots.libraries.*
import com.intellij.util.xmlb.*

@State(name = "ParadoxLibraryProperties", storages = [Storage("paradoxLanguageSupport.xml")])
data class ParadoxLibraryProperties(
	private val map:Map<String,Any?> = mapOf()
): LibraryProperties<ParadoxLibraryProperties>(),Map<String,Any?> by map{
	override fun getState() = this
	
	override fun loadState(state: ParadoxLibraryProperties) = XmlSerializerUtil.copyBean(state, this)
}