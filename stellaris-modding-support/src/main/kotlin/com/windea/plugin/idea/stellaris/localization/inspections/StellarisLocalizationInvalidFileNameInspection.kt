package com.windea.plugin.idea.stellaris.localization.inspections

import com.intellij.codeInspection.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationInvalidFileNameInspection: LocalInspectionTool(){
	override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<out ProblemDescriptor?>? {
		//如果文件名和其中的属性头部的时间区域不匹配
		val propertyHeader = PsiTreeUtil.getChildOfType(file, StellarisLocalizationPropertyHeader::class.java) ?: return null
		val propertyHeaderText = propertyHeader.text
		val fileName = file.name
		val isValid = fileName.contains(propertyHeader.text)
		if(!isValid){
			val holder = ProblemsHolder(manager,file,isOnTheFly)
			val description = message("stellaris.localization.inspection.invalidFileName.description", fileName,propertyHeaderText)
			holder.registerProblem(file, description)
			//holder.registerProblem(file, description,RenameFile(file))
			return holder.resultsArray
		}
		return null
	}

	//private class RenameFile(
	//	element: PsiElement
	//) : LocalQuickFixAndIntentionActionOnPsiElement(element) {
	//	override fun getText(): String {
	//		return message("stellaris.localization.quickFix.renameFile")
	//	}
	//
	//	override fun getFamilyName(): String {
	//		return text
	//	}
	//
	//	override fun invoke(project: Project, file: PsiFile, editor: Editor?, startElement: PsiElement, endElement: PsiElement) {
	//
	//	}
	//}
}
