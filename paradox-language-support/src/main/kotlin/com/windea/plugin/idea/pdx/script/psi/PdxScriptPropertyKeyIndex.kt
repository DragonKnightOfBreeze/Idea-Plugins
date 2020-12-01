package com.windea.plugin.idea.pdx.script.psi

import com.intellij.openapi.project.*
import com.intellij.psi.search.*
import com.intellij.psi.stubs.*

object PdxScriptPropertyKeyIndex: StringStubIndexExtension<PdxScriptProperty>() {
	private val key = StubIndexKey.createIndexKey<String,PdxScriptProperty>("pdxScript.propertyIndex")
	
	override fun getKey() = key
	
	override fun get(key: String, project: Project, scope: GlobalSearchScope): List<PdxScriptProperty> {
		val result =  mutableListOf<PdxScriptProperty>()
		for(element in StubIndex.getElements(this.key, key, project, scope, PdxScriptProperty::class.java)) {
			result.add(element)
		}
		return result
	}
	
	
	fun getOne(key: String, project: Project, scope: GlobalSearchScope): PdxScriptProperty? {
		for(element in StubIndex.getElements(this.key, key, project, scope, PdxScriptProperty::class.java)) {
			return element
		}
		return null
	}
	
	fun getAll( project: Project, scope: GlobalSearchScope): List<PdxScriptProperty> {
		val result = mutableListOf<PdxScriptProperty>()
		for(key in getAllKeys(project)) {
			for(element in get(key, project, scope)) {
				result.add(element)
			}
		}
		return result
	}
	
	inline fun filter(project: Project, scope: GlobalSearchScope, predicate:(String)->Boolean): List<PdxScriptProperty> {
		val result = mutableListOf<PdxScriptProperty>()
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


