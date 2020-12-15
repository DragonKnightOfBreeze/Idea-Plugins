package com.windea.plugin.idea.paradox.script.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.message
import java.nio.charset.*

class InvalidFileEncodingInspection: LocalInspectionTool(){
	companion object{
		private fun description(charset: Charset,bom:String) = message("paradox.script.inspection.invalidFileEncoding.description", charset,bom)
	}
	
	override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<out ProblemDescriptor?>? {
		val charset = file.virtualFile.charset
		val hasBom = file.virtualFile.bom != null
		val isNameList = file.virtualFile.parent.name == "name_lists"
		val isValid = charset == Charsets.UTF_8 && (if(isNameList) hasBom else !hasBom)
		if(!isValid){
			val holder = ProblemsHolder(manager,file,isOnTheFly)
			val bom = if(hasBom) "BOM" else "NO BOM"
			holder.registerProblem(file, description(charset,bom), ChangeFileEncoding(file, isNameList))
			return holder.resultsArray
		}
		return null
	}

	private class ChangeFileEncoding(
		element: PsiElement,
		private val isNameList:Boolean
	) : LocalQuickFixAndIntentionActionOnPsiElement(element) {
		companion object{
			private val name = message("paradox.script.quickFix.changeFileEncoding")
		}
		
		override fun getText() = name

		override fun getFamilyName() = name

		override fun invoke(project: Project, file: PsiFile, editor: Editor?, startElement: PsiElement, endElement: PsiElement) {
			//TODO 让IDE知道修改bom是对文档进行了修改
			file.virtualFile.charset = Charsets.UTF_8
			if(isNameList) file.virtualFile.bom = utf8Bom else file.virtualFile.bom = null
			file.virtualFile.refresh(false,false)
			file.subtreeChanged()
		}
	}
}
