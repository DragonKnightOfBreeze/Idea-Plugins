package com.windea.plugin.idea.stellaris.script.refactoring

import com.intellij.openapi.module.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.intellij.refactoring.*
import com.intellij.refactoring.safeDelete.*
import com.intellij.usageView.*
import com.windea.plugin.idea.stellaris.script.psi.*
import java.util.*

class StellarisScriptVariableSafeDeleteProcessor : SafeDeleteProcessorDelegateBase() {
	override fun getElementsToSearch(element: PsiElement, module: Module?, allElementsToDelete: MutableCollection<PsiElement>): MutableCollection<out PsiElement> {
		return Collections.singleton(element)
	}

	override fun handlesElement(element: PsiElement?): Boolean {
		return element is StellarisScriptFile
	}

	override fun prepareForDeletion(element: PsiElement?) {}

	override fun preprocessUsages(project: Project?, usages: Array<UsageInfo>?): Array<UsageInfo>? {
		return usages
	}

	override fun findUsages(element: PsiElement, allElementsToDelete: Array<out PsiElement>, result: MutableList<UsageInfo>): NonCodeUsageSearchInfo? {
		SafeDeleteProcessor.findGenericElementUsages(element, result, allElementsToDelete)
		return NonCodeUsageSearchInfo(SafeDeleteProcessor.getDefaultInsideDeletedCondition(allElementsToDelete), element)
	}

	override fun getAdditionalElementsToDelete(element: PsiElement, allElementsToDelete: MutableCollection<PsiElement>, askUser: Boolean): MutableCollection<PsiElement>? {
		return mutableListOf()
	}

	override fun findConflicts(element: PsiElement, allElementsToDelete: Array<out PsiElement>): MutableCollection<String>? {
		return mutableListOf()
	}

	override fun isToSearchInComments(element: PsiElement?): Boolean {
		return RefactoringSettings.getInstance().SAFE_DELETE_SEARCH_IN_COMMENTS
	}

	override fun isToSearchForTextOccurrences(element: PsiElement?): Boolean {
		return RefactoringSettings.getInstance().SAFE_DELETE_SEARCH_IN_NON_JAVA
	}

	override fun setToSearchInComments(element: PsiElement?, enabled: Boolean) {
		RefactoringSettings.getInstance().SAFE_DELETE_SEARCH_IN_COMMENTS = enabled
	}

	override fun setToSearchForTextOccurrences(element: PsiElement?, enabled: Boolean) {
		RefactoringSettings.getInstance().SAFE_DELETE_SEARCH_IN_NON_JAVA = enabled
	}
}
