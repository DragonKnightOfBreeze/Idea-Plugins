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
		for(key in getAllKeys(project)) {
			for(element in get(key, project, scope)) {
				if(locale == null || locale == element.stellarisLocale) result.add(element)
			}
		}
		return result
	}
	
	inline fun filter(locale:StellarisLocale?,project: Project, scope: GlobalSearchScope, predicate:(String)->Boolean): List<StellarisLocalizationProperty> {
		val result = mutableListOf<StellarisLocalizationProperty>()
		for(key in getAllKeys(project)) {
			if(predicate(key)) {
				for(element in get(key, project, scope)) {
					if(locale == null || locale == element.stellarisLocale) result.add(element)
				}
			}
		}
		return result
	}
}
