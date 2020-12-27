package com.windea.plugin.idea.paradox.localisation.psi

import com.intellij.openapi.project.*
import com.intellij.psi.search.*
import com.intellij.psi.stubs.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.model.*

object ParadoxLocalisationPropertyKeyIndex : StringStubIndexExtension<ParadoxLocalisationProperty>() {
	private val key = StubIndexKey.createIndexKey<String, ParadoxLocalisationProperty>("paradoxLocalisation.property.index")
	
	override fun getKey() = key
	
	override fun get(key: String, project: Project, scope: GlobalSearchScope): List<ParadoxLocalisationProperty> {
		return get(key, null, project, scope)
	}
	
	fun get(key: String, locale: ParadoxLocale?, project: Project, scope: GlobalSearchScope): List<ParadoxLocalisationProperty> {
		val result = mutableListOf<ParadoxLocalisationProperty>()
		var index = 0
		for(element in StubIndex.getElements(this.key, key, project, scope, ParadoxLocalisationProperty::class.java)) {
			val elementLocale = element.paradoxLocale
			if(locale == null) {
				//需要将用户的语言区域对应的本地化属性放到该组本地化属性的最前面
				if(elementLocale == inferredParadoxLocale) {
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
	
	fun getOne(key: String, locale: ParadoxLocale?, project: Project, scope: GlobalSearchScope): ParadoxLocalisationProperty? {
		for(element in StubIndex.getElements(this.key, key, project, scope, ParadoxLocalisationProperty::class.java)) {
			if(locale == null || locale == element.paradoxLocale) return element
		}
		return null
	}
	
	fun getAll(locale: ParadoxLocale?, project: Project, scope: GlobalSearchScope): List<ParadoxLocalisationProperty> {
		val result = mutableListOf<ParadoxLocalisationProperty>()
		var index = 0
		for(key in getAllKeys(project)) {
			val group = get(key, project, scope)
			val nextIndex = index + group.size
			for(element in group) {
				val elementLocale = element.paradoxLocale
				if(locale == null) {
					//需要将用户的语言区域对应的本地化属性放到该组本地化属性的最前面
					if(elementLocale == inferredParadoxLocale) {
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
	
	inline fun filter(locale: ParadoxLocale?, project: Project, scope: GlobalSearchScope, predicate: (String) -> Boolean): List<ParadoxLocalisationProperty> {
		val result = mutableListOf<ParadoxLocalisationProperty>()
		var index = 0
		for(key in getAllKeys(project)) {
			if(predicate(key)) {
				val group = get(key, project, scope)
				val nextIndex = index + group.size
				for(element in group) {
					val elementLocale = element.paradoxLocale
					if(locale == null) {
						//需要将用户的语言区域对应的本地化属性放到该组本地化属性的最前面
						if(elementLocale == inferredParadoxLocale) {
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
