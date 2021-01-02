package com.windea.plugin.idea.paradox.util

import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.intellij.util.io.*
import com.windea.plugin.idea.paradox.*
import org.yaml.snakeyaml.*
import java.nio.file.*
import java.util.concurrent.*

/**
 * Paradox规则组的提供器。
 */
object ParadoxRuleGroupProvider {
	private val rootPath = "/rules".toClassPathResource()?.toPath()!!
	private val mutableRuleGroups: MutableMap<String, MutableMap<String, Map<String, Any>>> = ConcurrentHashMap()
	
	val ruleGroups: Map<String, Map<String, Map<String, Any>>> = mutableRuleGroups
	val stellarisRuleGroup: Map<String, Map<String, Any>> = ruleGroups.getOrDefault("stellaris", mapOf())
	
	init {
		println("init")
		addRuleGroups()
		println(mutableRuleGroups)
		
		runWhenProjectOpened {
			println("init runWhenProjectOpened")
			addRuleGroups()
			println(mutableRuleGroups)
		}
	}
	
	private fun addRuleGroups() {
		//并发添加规则组
		val executor = Executors.newCachedThreadPool()
		for(groupPath in rootPath) {
			try {
				executor.submit {
					addRuleGroup(groupPath)
				}.get()
			} catch(e: Exception) {
				e.printStackTrace()
			}
		}
	}
	
	private fun addRuleGroup(groupPath: Path) {
		//添加规则组
		val groupName = groupPath.fileName.toString()
		val group = ConcurrentHashMap<String, Map<String, Any>>()
		mutableRuleGroups[groupName] = group
		addRuleGroupOrRule(groupPath, group)
	}
	
	private fun addRuleGroupOrRule(groupPath: Path, group: MutableMap<String, Map<String, Any>>) {
		for(p in groupPath) {
			when {
				p.isDirectory() -> addRuleGroupOrRule(p, group)
				p.isFile() -> addRule(p, group)
			}
		}
	}
	
	private fun addRule(path: Path, group: MutableMap<String, Map<String, Any>>) {
		try {
			val file = VfsUtil.findFile(path, false) ?: return
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
		return yaml.load(file.inputStream)
	}
}