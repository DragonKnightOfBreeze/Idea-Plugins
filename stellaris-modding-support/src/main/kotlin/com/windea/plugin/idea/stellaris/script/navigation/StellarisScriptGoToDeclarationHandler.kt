@file:Suppress("UNCHECKED_CAST")

package com.windea.plugin.idea.stellaris.script.navigation

import com.intellij.codeInsight.navigation.actions.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.psi.*

//定位定义：
//DONE 变量 - 当前文件
//DONE 变量 - 当前项目
//TODO 变量 - 游戏目录
//TODO 变量 - mod目录

@ExtensionPoint
class StellarisScriptGoToDeclarationHandler:  GotoDeclarationHandler {
	override fun getGotoDeclarationTargets(sourceElement: PsiElement?, offset: Int, editor: Editor?): Array<out PsiElement?>? {
		return when(sourceElement) {
			null -> null
			is StellarisScriptVariableDefinition -> {
				//查找当前文件，如果没有，再查找当前项目
				val name = sourceElement.name ?:return null
				findScriptVariableDefinitionInFile(name,sourceElement.containingFile)?.toSingletonArray()?.let { return it }
				findScriptVariableDefinitions(name,sourceElement.project).toArray()
			}
			//字符串可以是脚本文件属性，也可以是本地化文件属性
			is StellarisScriptString -> {
				if(!sourceElement.isValidPropertyKey) return null

				//查找当前项目的本地化文件属性，如果没有，再查找当前项目的本地化文件属性
				val name = sourceElement.value
				val project = sourceElement.project
				findScriptProperties(name, project).toArray().let { if(it.isNotEmpty()) return it }
				findLocalizationProperties(name, project).toArray()
			}
			else -> null
		}
	}
}
