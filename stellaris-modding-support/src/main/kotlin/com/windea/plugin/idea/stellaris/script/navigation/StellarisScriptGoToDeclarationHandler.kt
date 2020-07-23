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
class StellarisScriptGoToDeclarationHandler:  GotoDeclarationHandlerBase() {
	override fun getGotoDeclarationTarget(sourceElement: PsiElement?, editor: Editor?): PsiElement? {
		return when(sourceElement) {
			null -> null
			is StellarisScriptVariableDefinition -> {
				//查找当前文件，如果没有，查找当前项目
				findScriptVariableDefinitionInFile(sourceElement.name, sourceElement.containingFile)?.let{return it}
				findScriptVariableDefinitionInProject(sourceElement.name,sourceElement.project)
			}
			is StellarisScriptStringLiteral ->{
				if(!sourceElement.isValidPropertyKey) return null

				val name = sourceElement.value
				val project = sourceElement.project
				//查找当前项目的脚本文件属性，然后再查找当前项目的本地化文件属性
				findScriptPropertyInProject(name,project)?.let { return it }
				findLocalizationPropertyInProject(name,project)?.let { return it }
			}
			else -> null
		}
	}

	override fun getGotoDeclarationTargets(sourceElement: PsiElement?, offset: Int, editor: Editor?): Array<out PsiElement?>? {
		return when(sourceElement) {
			null -> null
			is StellarisScriptVariableDefinition -> {
				//查找当前文件，然后查找当前项目
				findScriptVariableDefinitionInFile(sourceElement.name,sourceElement.containingFile)?.let { return arrayOf(it) }
				findScriptVariableDefinitionsInProject(sourceElement.name,sourceElement.project)?.let { return it.toTypedArray() }
			}
			//字符串可以是脚本文件属性，也可以是本地化文件属性
			is StellarisScriptStringLiteral -> {
				if(!sourceElement.isValidPropertyKey) return null

				val name = sourceElement.value
				val project = sourceElement.project
				//查找当前项目的脚本文件属性，然后再查找当前项目的本地化文件属性
				findScriptPropertiesInProject(name, project)?.let { return it.toTypedArray() }
				findLocalizationPropertiesInProject(name, project).let { return it.toTypedArray() }
			}
			else -> null
		}
	}
}
