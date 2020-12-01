package com.windea.plugin.idea.stellaris.localization.refactoring

import com.intellij.lang.refactoring.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.localization.psi.*

//代码重构：
//重命名：属性
//安全删除：属性

class StellarisLocalizationRefactoringSupportProvider : RefactoringSupportProvider() {
	override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
		return element is PsiNameIdentifierOwner
	}
}
