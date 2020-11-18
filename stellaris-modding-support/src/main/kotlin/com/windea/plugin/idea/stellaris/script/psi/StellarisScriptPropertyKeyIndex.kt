package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.openapi.project.*
import com.intellij.psi.search.*
import com.intellij.psi.stubs.*

object StellarisScriptPropertyKeyIndex: StringStubIndexExtension<StellarisScriptProperty>() {
	private val key = StubIndexKey.createIndexKey<String,StellarisScriptProperty>("stellarisScript.property.index")
	
	override fun getKey() = key
	
	override fun get(key: String, project: Project, scope: GlobalSearchScope): List<StellarisScriptProperty> {
		val result =  mutableListOf<StellarisScriptProperty>()
		for(element in StubIndex.getElements(this.key, key, project, scope, StellarisScriptProperty::class.java)) {
			result.add(element)
		}
		return result
	}
	
	
	fun getOne(key: String, project: Project, scope: GlobalSearchScope): StellarisScriptProperty? {
		for(element in StubIndex.getElements(this.key, key, project, scope, StellarisScriptProperty::class.java)) {
			return element
		}
		return null
	}
	
	fun getAll( project: Project, scope: GlobalSearchScope): List<StellarisScriptProperty> {
		val result = mutableListOf<StellarisScriptProperty>()
		for(key in getAllKeys(project)) {
			for(element in get(key, project, scope)) {
				result.add(element)
			}
		}
		return result
	}
	
	inline fun filter(project: Project, scope: GlobalSearchScope, predicate:(String)->Boolean): List<StellarisScriptProperty> {
		val result = mutableListOf<StellarisScriptProperty>()
		for(key in getAllKeys(project)) {
			if(predicate(key)) {
				for(element in get(key, project, scope)) {
					result.add(element)
				}
			}
		}
		return result
	}
}


