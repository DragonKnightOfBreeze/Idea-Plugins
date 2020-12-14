package com.windea.plugin.idea.paradox.util

import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.intellij.psi.util.*
import com.intellij.util.io.*
import com.windea.plugin.idea.paradox.*
import java.nio.file.*
import java.util.concurrent.*
import java.util.concurrent.atomic.*

/**
 * Paradox规则组的提供器。
 */
object ParadoxRuleGroupProvider {
	//ruleGroups[groupName][rulePath] = rule
	private val ruleGroups:MutableMap<String,MutableMap<String,List<Any>>> = ConcurrentHashMap()
	private val initFinished = AtomicBoolean(false)
	
	private val rootPath = "/rules".toClassPathResource()?.toPath()!!
	
	fun init(project:Project){
		if(!initFinished.get()) {
			addRuleGroups(project)
			initFinished.set(true)
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
			val virtualFile = VfsUtil.findFile(path, false) ?: return
			val psiFile = PsiUtilCore.getPsiFile(project, virtualFile)
			val ruleName = groupPath.relativize(path).toString()
			val rule = ParadoxScriptDataExtractor.extract(psiFile)
			group[ruleName] = rule
		}catch(e:Exception){
			e.printStackTrace()
		}
	}
}