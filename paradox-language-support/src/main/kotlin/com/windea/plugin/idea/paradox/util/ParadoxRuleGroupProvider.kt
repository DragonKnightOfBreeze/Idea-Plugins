package com.windea.plugin.idea.paradox.util

import com.intellij.openapi.vfs.*
import com.windea.plugin.idea.paradox.*
import org.yaml.snakeyaml.*
import java.util.concurrent.*

/**
 * Paradox规则组的提供器。
 */
object ParadoxRuleGroupProvider {
	private var shouldLoad = true
	private val shouldLoadLock = Any()
	private val ruleGroups: MutableMap<String, MutableMap<String, Map<String, Any>>> = ConcurrentHashMap()
	
	fun getRuleGroups(): Map<String, Map<String, Map<String, Any>>> {
		if(shouldLoad) {
			shouldLoad = false
			addRuleGroups()
		}
		return ruleGroups
	}
	
	private fun addRuleGroups() {
		val rootUrl = "rules".toClassPathResource()
		if(rootUrl != null) {
			val rootPath = rootUrl.file.replace("%20", " ").removePrefix("file:")
			val rootFile = JarFileSystem.getInstance().findFileByPath(rootPath)
			if(rootFile != null) {
				//并发添加规则组
				val executor = Executors.newCachedThreadPool()
				for(child in rootFile.children) {
					if(child.isDirectory) {
						try {
							executor.submit {
								addRuleGroup(child)
							}.get()
						} catch(e: Exception) {
							e.printStackTrace()
						}
					}
				}
			}
		}
	}
	
	private fun addRuleGroup(file: VirtualFile) {
		//添加规则组
		val groupName = file.name
		val group = ConcurrentHashMap<String, Map<String, Any>>()
		ruleGroups[groupName] = group
		addRuleGroupOrRule(file, group)
	}
	
	private fun addRuleGroupOrRule(file: VirtualFile, group: MutableMap<String, Map<String, Any>>) {
		for(child in file.children) {
			when {
				child.isDirectory -> addRuleGroupOrRule(child, group)
				else -> addRule(child, group)
			}
		}
	}
	
	private fun addRule(file: VirtualFile, group: MutableMap<String, Map<String, Any>>) {
		try {
			val rule = extractRule(file)
			//规则数据可能需要合并
			//for((ruleName, ruleValue) in rule) {
			//	group.compute(ruleName){ _,v-> if(v != null) v + ruleValue else ruleValue }
			//}
			group.putAll(rule)
		} catch(e: Exception) {
			e.printStackTrace()
		}
	}
	
	private val yaml = Yaml()
	
	private fun extractRule(file: VirtualFile): Map<String, Map<String, Any>> {
		return yaml.load(file.inputStream)?: emptyMap()
	}
}