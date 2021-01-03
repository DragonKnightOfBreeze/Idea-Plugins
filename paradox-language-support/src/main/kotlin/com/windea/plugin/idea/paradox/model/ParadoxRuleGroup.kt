package com.windea.plugin.idea.paradox.model

import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.script.psi.*

@Suppress("UNCHECKED_CAST")
class ParadoxRuleGroup(
	data: Map<String, Map<String, Any>>
) : Map<String, Map<String, Any>> by data {
	val definitions = Definitions(getOrDefault("definitions", emptyMap()))
	val enums = Enums(getOrDefault("enums", emptyMap()))
	val types = Types(getOrDefault("types", emptyMap()))
	
	class Definitions(
		data: Map<String, Any>
	) : Map<String, Definition> by data.mapValues({ (k, v) -> Definition(k, v) })
	
	class Enums(
		data: Map<String, Any>
	) : Map<String, Any> by data
	
	class Types(
		data: Map<String, Any>
	) : Map<String, Any> by data
	
	class Definition(
		key: String, data: Any
	) : Map<String, Any> by data as? Map<String, Any> ?: emptyMap() {
		val name = key
		
		//不要单纯地遍历列表进行匹配，需要先通过某种方式过滤不合法的scriptProperty
		//暂时认为3级的scriptProperty不再需要匹配
		//path和propertyPath不要重复获取
		
		fun matches(elementName: String, path: ParadoxPath, scriptPath: ParadoxPath): Boolean {
			//判断路径是否匹配
			val pathData = get("path") as String? ?: return false
			val pathStrictData = get("path_strict") as Boolean? ?: false
			if(pathStrictData) {
				if(pathData != path.parent) return false
			} else {
				if(!pathData.matchesPath(path.parent)) return false
			}
			//判断文件名和文件扩展名是否匹配
			val fileNameData = get("file_name") as String?
			if(fileNameData != null) {
				if(fileNameData != path.fileName) return false
			} else {
				val fileExtensionData = get("file_extension") as String? ?: "txt"
				if(fileExtensionData != path.fileExtension) return false
			}
			//处理是否需要跳过rootKey
			val skipRootKeyData = get("skip_root_key") as String?
			if(skipRootKeyData != null) {
				//skip_root_key=any表示跳过任意的rootKey
				if(scriptPath.length != 2 || skipRootKeyData != "any" && skipRootKeyData != scriptPath.root) return false
			} else {
				if(scriptPath.length != 1) return false
			}
			//过滤key
			val keyFilterData = get("key_filter") as String?
			if(keyFilterData != null) {
				val keyConditions = keyFilterData.split(',')
				for(keyCondition in keyConditions) {
					if(keyCondition.isNotEmpty() && keyCondition[0] == '!') {
						if(elementName == keyCondition.drop(1)) return false
					} else {
						if(elementName != keyCondition) return false
					}
				}
			}
			return true
		}
		
		fun getName(element: ParadoxScriptProperty): String {
			val nameKeyData = get("name_key") as String? ?: return element.name
			val nameProperty = element.findProperty(nameKeyData) ?: return anonymousName
			return nameProperty.value ?: anonymousName
		}
		
		fun getLocalisation(name: String): Map<ConditionalString, String> {
			val localisationData = get("localisation") as Map<String, String>? ?: return emptyMap()
			val result = mutableMapOf<ConditionalString, String>()
			for((k, v) in localisationData) {
				result[k.toConditionalKey()] = replacePlaceholder(v, name)
			}
			return result
		}
		
		//将$替换成name
		private fun replacePlaceholder(placeholder: String, name: String): String {
			return buildString {
				for(c in placeholder) {
					if(c == '$') append(name) else append(c)
				}
			}
		}
		
		fun getScopes(): Map<String, String> {
			return get("replace_scopes") as Map<String, String>? ?: return emptyMap()
		}
		
		fun getFromVersion(): String {
			return get("from_version") as String? ?: ""
		}
		
		fun toMetadata(element: ParadoxScriptProperty): ParadoxTypeMetadata {
			val type = this.name
			val name = getName(element)
			val localisation = getLocalisation(name)
			val scopes = getScopes()
			val fromVersion = getFromVersion()
			return ParadoxTypeMetadata(type, name, localisation, scopes, fromVersion)
		}
	}
}