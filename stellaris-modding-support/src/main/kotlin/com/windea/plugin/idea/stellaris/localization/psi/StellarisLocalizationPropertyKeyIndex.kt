package com.windea.plugin.idea.stellaris.localization.psi

import com.intellij.openapi.project.*
import com.intellij.psi.search.*
import com.intellij.psi.stubs.*
import com.windea.plugin.idea.stellaris.*

object StellarisLocalizationPropertyKeyIndex: StringStubIndexExtension<StellarisLocalizationProperty>() {
	private val key = StubIndexKey.createIndexKey<String,StellarisLocalizationProperty>("stellarisLocalization.index")
	
	override fun getKey() = key
	
	override fun get(key: String, project: Project, scope: GlobalSearchScope): List<StellarisLocalizationProperty> {
		return get(key,null,project,scope)
	}
	
	fun get(key: String,locale: StellarisLocale?, project: Project, scope: GlobalSearchScope): List<StellarisLocalizationProperty> {
		val result =  mutableListOf<StellarisLocalizationProperty>()
		for(element in StubIndex.getElements(this.key, key, project, scope, StellarisLocalizationProperty::class.java)) {
			if(locale == null || locale == element.stellarisLocale) result.add(element)
		}
		return result
	}
	
	fun getOne(key: String,locale: StellarisLocale?, project: Project, scope: GlobalSearchScope): StellarisLocalizationProperty? {
		for(element in StubIndex.getElements(this.key, key, project, scope, StellarisLocalizationProperty::class.java)) {
			if(locale == null || locale == element.stellarisLocale) return element
		}
		return null
	}
	
	fun getAll(locale: StellarisLocale?, project: Project, scope: GlobalSearchScope): List<StellarisLocalizationProperty> {
		val result = mutableListOf<StellarisLocalizationProperty>()
		var index = 0
		for(key in getAllKeys(project)) {
			val group = get(key, project, scope)
			val nextIndex = index+ group.size
			for(element in group) {
				val elementLocale = element.stellarisLocale
				if(locale == null) {
					//需要将用户的语言区域对应的本地化属性放到该组本地化属性的最前面
					if(elementLocale == inferedStellarisLocale){
						result.add(index++,element)
					}else{
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
	
	inline fun filter(locale:StellarisLocale?,project: Project, scope: GlobalSearchScope, predicate:(String)->Boolean): List<StellarisLocalizationProperty> {
		val result = mutableListOf<StellarisLocalizationProperty>()
		var index = 0
		for(key in getAllKeys(project)) {
			if(predicate(key)) {
				val group = get(key, project, scope)
				val nextIndex = index + group.size
				for(element in group) {
					val elementLocale = element.stellarisLocale
					if(locale == null) {
						//需要将用户的语言区域对应的本地化属性放到该组本地化属性的最前面
						if(elementLocale == inferedStellarisLocale){
							result.add(index++,element)
						}else{
							result.add(element)
						}
					} else {
						if(locale == elementLocale) {
							result.add(element)
						}
					}
				}
				index  = nextIndex
			}
		}
		return result
	}
}
