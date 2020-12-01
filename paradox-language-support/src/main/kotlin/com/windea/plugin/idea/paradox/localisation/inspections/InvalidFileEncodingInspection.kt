package com.windea.plugin.idea.paradox.localisation.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.message

class InvalidFileEncodingInspection : LocalInspectionTool() {
	override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<out ProblemDescriptor?>? {
		val charset = file.virtualFile.charset
		val hasBom = file.virtualFile.bom != null
		val isValid = charset == Charsets.UTF_8 && hasBom
		if(!isValid){
			val holder = ProblemsHolder(manager,file,isOnTheFly)
			val bomText = if(hasBom) "BOM" else "NO BOM"
			val description = message("paradox.localisation.inspection.invalidFileEncoding.description", charset,bomText)
			holder.registerProblem(file, description, ChangeFileEncoding(file))
			return holder.resultsArray
		}
		return null
	}

	private class ChangeFileEncoding(
		element: PsiElement
	) : LocalQuickFixAndIntentionActionOnPsiElement(element) {
		override fun getText(): String {
			return message("paradox.localisation.quickFix.changeFileEncoding")
		}

		override fun getFamilyName(): String {
			return text
		}

		override fun invoke(project: Project, file: PsiFile, editor: Editor?, startElement: PsiElement, endElement: PsiElement) {
			file.virtualFile.charset = Charsets.UTF_8
			file.virtualFile.bom = utf8Bom
			file.virtualFile.refresh(true,false)
			file.subtreeChanged()
		}
	}
}
