package com.windea.plugin.idea.pdx.script.psi

import com.intellij.openapi.project.*
import com.intellij.psi.search.*
import com.intellij.psi.stubs.*

object PdxScriptVariableKeyIndex: StringStubIndexExtension<PdxScriptVariable>() {
	private val key = StubIndexKey.createIndexKey<String, PdxScriptVariable>("pdxScript.variableIndex")
	
	override fun getKey() = key
	
	override fun get(key: String, project: Project, scope: GlobalSearchScope): List<PdxScriptVariable> {
		val result =  mutableListOf<PdxScriptVariable>()
		for(element in StubIndex.getElements(this.key, key, project, scope, PdxScriptVariable::class.java)) {
			result.add(element)
		}
		return result
	}
	
	
	fun getOne(key: String, project: Project, scope: GlobalSearchScope): PdxScriptVariable? {
		for(element in StubIndex.getElements(this.key, key, project, scope, PdxScriptVariable::class.java)) {
			return element
		}
		return null
	}
	
	fun getAll( project: Project, scope: GlobalSearchScope): List<PdxScriptVariable> {
		val result = mutableListOf<PdxScriptVariable>()
		for(key in getAllKeys(project)) {
			for(element in get(key, project, scope)) {
				result.add(element)
			}
		}
		return result
	}
	
	inline fun filter(project: Project, scope: GlobalSearchScope, predicate:(String)->Boolean): List<PdxScriptVariable> {
		val result = mutableListOf<PdxScriptVariable>()
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

