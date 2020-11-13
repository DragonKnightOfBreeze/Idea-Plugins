@file:Suppress("UNCHECKED_CAST")

package com.windea.plugin.idea.stellaris.script.navigation

import com.intellij.codeInsight.navigation.actions.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*

//定位定义：
//DONE 变量 - 当前文件
//DONE 变量 - 当前项目
//TODO 变量 - 游戏目录
//TODO 变量 - mod目录

class StellarisScriptGoToDeclarationHandler:  GotoDeclarationHandler {
	override fun getGotoDeclarationTargets(sourceElement: PsiElement?, offset: Int, editor: Editor?): Array<out PsiElement?>? {
		return when(sourceElement) {
			null -> null
			is StellarisScriptVariable -> {
				//查找当前文件，如果没有，再查找当前项目
				val name = sourceElement.name ?:return null
				findScriptVariableInFile(name,sourceElement.containingFile)?.toSingletonArray()?.let { return it }
				findScriptVariables(name, sourceElement.project).toTypedArray()
			}
			//字符串可以是脚本文件属性，也可以是本地化文件属性
			is StellarisScriptString -> {
				//查找当前项目的本地化文件属性，如果没有，再查找当前项目的本地化文件属性
				val name = sourceElement.value
				//如果包含空格则跳过，不可能是合法的属性键
				if(name.containsBlank()) return null
				val project = sourceElement.project
				findScriptProperties(name, project).toTypedArray().let { if(it.isNotEmpty()) return it }
				//寻找推断的语言区域的本地化属性
				findLocalizationProperties(name, project, inferedStellarisLocale).toTypedArray()
			}
			else -> null
		}
	}
}
