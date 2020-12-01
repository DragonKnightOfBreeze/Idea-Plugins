package com.windea.plugin.idea.pdx.localisation.refactoring

import com.intellij.lang.refactoring.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.localisation.psi.*

//代码重构：
//重命名：属性
//安全删除：属性

class PdxLocalisationRefactoringSupportProvider : RefactoringSupportProvider() {
	override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
		return element is PsiNameIdentifierOwner
	}
}
