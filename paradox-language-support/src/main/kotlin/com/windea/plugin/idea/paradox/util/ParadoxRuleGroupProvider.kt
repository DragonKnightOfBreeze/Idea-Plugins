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
	//ruleGroups[groupName][rulePath] = rule
	private val ruleGroups:MutableMap<String,MutableMap<String,List<Any>>> = ConcurrentHashMap()
	private val rootPath = "/rules".toClassPathResource()?.toPath()!!
	
	init {
		//打开项目时注册规则组
		runWhenProjectOpened { project ->
			addRuleGroups(project)
		}
	}
	
	private fun addRuleGroups(project: Project) {
		//并发添加规则
		val executor = Executors.newCachedThreadPool()
		for(groupPath in rootPath) {
			try {
				executor.submit {
					addRuleGroup(groupPath, project)
				}.get()
			}catch(e:Exception){
				e.printStackTrace()
			}
		}
	}
	
	private fun addRuleGroup(groupPath:Path,project:Project){
		val groupName = groupPath.fileName.toString()
		val group = ConcurrentHashMap<String,List<Any>>()
		doAddRuleGroup(groupPath,group,project)
		ruleGroups[groupName] = group
	}
	
	private fun doAddRuleGroup(groupPath:Path,group:MutableMap<String,List<Any>>,project:Project){
		for(p in groupPath) {
			when{
				p.isDirectory() -> doAddRuleGroup(p,group,project)
				p.isFile()  -> doAddRule(groupPath,p,group,project)
			}
		}
	}
	
	private fun doAddRule(groupPath:Path,path:Path,group: MutableMap<String, List<Any>>,project:Project){
		try {
			val file = VfsUtil.findFile(path, false) ?: return
			val ruleName = groupPath.relativize(path).toString()
			val rule = extractRule(file)
			group[ruleName] = rule
		}catch(e:Exception){
			e.printStackTrace()
		}
	}
	
	private val yaml = Yaml()
	
	private fun extractRule(file: VirtualFile): List<Any> {
		return yaml.load(file.inputStream)
	}
	
	//private fun extractRule(file: VirtualFile): List<Any> {
	//	return ParadoxScriptDataExtractor.extract(PsiUtilCore.getPsiFile(project, file))
	//}
}