@file:Suppress("UNCHECKED_CAST")

package com.windea.plugin.idea.stellaris.localization.navigation

import com.intellij.codeInsight.navigation.actions.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*

//定位定义：
//TODO 属性 - 当前文件
//TODO 属性 - 当前项目
//TODO 属性 - 游戏目录
//TODO 属性 - mod目录

@ExtensionPoint
class StellarisLocalizationGoToDeclarationHandler : GotoDeclarationHandlerBase() {
	override fun getGotoDeclarationTarget(sourceElement: PsiElement?, editor: Editor?): PsiElement? {
		return when(sourceElement) {
			null -> null
			is StellarisLocalizationProperty -> {
				//查找当前文件，如果没有查找当前项目
				val name = sourceElement.name
				findLocalizationPropertyInFile(name, sourceElement.containingFile)?.let{return it}
				findLocalizationPropertyInProject(name, sourceElement.project)
			}
			else -> null
		}
	}

	override fun getGotoDeclarationTargets(sourceElement: PsiElement?, offset: Int, editor: Editor?): Array<out PsiElement?>? {
		return when(sourceElement) {
			null -> null
			is StellarisLocalizationProperty -> {
				//查找当前文件，然后查找当前项目
				val name = sourceElement.name
				findLocalizationPropertyInFile(name, sourceElement.containingFile)?.let { return arrayOf(it) }
				findLocalizationPropertiesInProject(name, sourceElement.project)?.let { return it.toTypedArray() }
			}
			else -> null
		}
	}
}

