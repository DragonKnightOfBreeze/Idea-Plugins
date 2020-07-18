package com.windea.plugin.idea.stellaris.localization.refactoring

import com.intellij.lang.refactoring.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*

//代码重构：
//重命名：属性
//安全删除：属性

@ExtensionPoint
class StellarisLocalizationRefactoringSupportProvider : RefactoringSupportProvider() {
	override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
		return element is StellarisLocalizationProperty
	}

	override fun isSafeDeleteAvailable(element: PsiElement): Boolean {
		return element is StellarisLocalizationProperty
	}
}
