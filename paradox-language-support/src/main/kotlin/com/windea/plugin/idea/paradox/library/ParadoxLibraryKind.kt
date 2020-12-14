package com.windea.plugin.idea.paradox.library

import com.intellij.openapi.roots.libraries.*
import com.windea.plugin.idea.paradox.*

abstract class ParadoxLibraryKind(kindId:String) : PersistentLibraryKind<ParadoxLibraryProperties>(kindId) {
	override fun createDefaultProperties(): ParadoxLibraryProperties {
		return ParadoxLibraryProperties()
	}
}

object StellarisLibraryKind: ParadoxLibraryKind(stellarisName)

//TODO Other Games