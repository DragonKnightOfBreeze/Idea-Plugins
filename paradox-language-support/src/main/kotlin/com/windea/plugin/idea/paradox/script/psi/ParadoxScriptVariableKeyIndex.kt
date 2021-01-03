package com.windea.plugin.idea.paradox.script.psi

import com.intellij.openapi.project.*
import com.intellij.psi.search.*
import com.intellij.psi.stubs.*

object ParadoxScriptVariableKeyIndex: StringStubIndexExtension<ParadoxScriptVariable>() {
	private val key = StubIndexKey.createIndexKey<String, ParadoxScriptVariable>("paradoxScript.variable.index")
	
	override fun getKey() = key
	
	override fun get(key: String, project: Project, scope: GlobalSearchScope): List<ParadoxScriptVariable> {
		val result =  mutableListOf<ParadoxScriptVariable>()
		for(element in StubIndex.getElements(this.key, key, project, scope, ParadoxScriptVariable::class.java)) {
			result.add(element)
		}
		return result
	}
	
	fun getOne(name: String, project: Project, scope: GlobalSearchScope): ParadoxScriptVariable? {
		for(element in StubIndex.getElements(this.key, name, project, scope, ParadoxScriptVariable::class.java)) {
			return element
		}
		return null
	}
	
	fun getAll(name: String, project: Project, scope: GlobalSearchScope): List<ParadoxScriptVariable> {
		val result =  mutableListOf<ParadoxScriptVariable>()
		for(element in StubIndex.getElements(this.key, name, project, scope, ParadoxScriptVariable::class.java)) {
			result.add(element)
		}
		return result
	}
	
	fun getAll(project: Project, scope: GlobalSearchScope): List<ParadoxScriptVariable> {
		val result = mutableListOf<ParadoxScriptVariable>()
		for(key in getAllKeys(project)) {
			for(element in get(key, project, scope)) {
				result.add(element)
			}
		}
		return result
	}
	
	inline fun filter(project: Project, scope: GlobalSearchScope, predicate:(String)->Boolean): List<ParadoxScriptVariable> {
		val result = mutableListOf<ParadoxScriptVariable>()
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

