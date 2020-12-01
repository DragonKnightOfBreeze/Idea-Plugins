package com.windea.plugin.idea.pdx.localisation.psi

import com.intellij.openapi.project.*
import com.intellij.psi.search.*
import com.intellij.psi.stubs.*
import com.windea.plugin.idea.pdx.*

object PdxLocalisationPropertyKeyIndex : StringStubIndexExtension<PdxLocalisationProperty>() {
	private val key = StubIndexKey.createIndexKey<String, PdxLocalisationProperty>("pdxLocalisation.index")
	
	override fun getKey() = key
	
	override fun get(key: String, project: Project, scope: GlobalSearchScope): List<PdxLocalisationProperty> {
		return get(key, null, project, scope)
	}
	
	fun get(key: String, locale: PdxLocale?, project: Project, scope: GlobalSearchScope): List<PdxLocalisationProperty> {
		val result = mutableListOf<PdxLocalisationProperty>()
		var index = 0
		for(element in StubIndex.getElements(this.key, key, project, scope, PdxLocalisationProperty::class.java)) {
			val elementLocale = element.pdxLocale
			if(locale == null) {
				//需要将用户的语言区域对应的本地化属性放到该组本地化属性的最前面
				if(elementLocale == inferredPdxLocale) {
					result.add(index++, element)
				} else {
					result.add(element)
				}
			} else {
				if(locale == elementLocale) {
					result.add(element)
				}
			}
		}
		return result
	}
	
	fun getOne(key: String, locale: PdxLocale?, project: Project, scope: GlobalSearchScope): PdxLocalisationProperty? {
		for(element in StubIndex.getElements(this.key, key, project, scope, PdxLocalisationProperty::class.java)) {
			if(locale == null || locale == element.pdxLocale) return element
		}
		return null
	}
	
	fun getAll(locale: PdxLocale?, project: Project, scope: GlobalSearchScope): List<PdxLocalisationProperty> {
		val result = mutableListOf<PdxLocalisationProperty>()
		var index = 0
		for(key in getAllKeys(project)) {
			val group = get(key, project, scope)
			val nextIndex = index + group.size
			for(element in group) {
				val elementLocale = element.pdxLocale
				if(locale == null) {
					//需要将用户的语言区域对应的本地化属性放到该组本地化属性的最前面
					if(elementLocale == inferredPdxLocale) {
						result.add(index++, element)
					} else {
						result.add(element)
					}
				} else if(locale == elementLocale) {
					result.add(element)
				}
			}
			index = nextIndex
		}
		return result
	}
	
	inline fun filter(locale: PdxLocale?, project: Project, scope: GlobalSearchScope, predicate: (String) -> Boolean): List<PdxLocalisationProperty> {
		val result = mutableListOf<PdxLocalisationProperty>()
		var index = 0
		for(key in getAllKeys(project)) {
			if(predicate(key)) {
				val group = get(key, project, scope)
				val nextIndex = index + group.size
				for(element in group) {
					val elementLocale = element.pdxLocale
					if(locale == null) {
						//需要将用户的语言区域对应的本地化属性放到该组本地化属性的最前面
						if(elementLocale == inferredPdxLocale) {
							result.add(index++, element)
						} else {
							result.add(element)
						}
					} else {
						if(locale == elementLocale) {
							result.add(element)
						}
					}
				}
				index = nextIndex
			}
		}
		return result
	}
}
