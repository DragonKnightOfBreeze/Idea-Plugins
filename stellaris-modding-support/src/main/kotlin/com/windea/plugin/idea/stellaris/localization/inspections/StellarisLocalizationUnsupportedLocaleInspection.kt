package com.windea.plugin.idea.stellaris.localization.inspections

import com.intellij.codeInspection.*
import com.intellij.icons.*
import com.intellij.openapi.command.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.intellij.openapi.ui.popup.*
import com.intellij.openapi.ui.popup.util.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.localization.psi.*
import javax.swing.*

class StellarisLocalizationUnsupportedLocaleInspection : LocalInspectionTool() {
	//processFile 无效

	override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
		return Visitor(holder)
	}

	private class Visitor(
		private val holder: ProblemsHolder
	) : StellarisLocalizationVisitor(){
		override fun visitFile(file: PsiFile) {
			val propertyHeader = PsiTreeUtil.findChildOfType(file, StellarisLocalizationPropertyHeader::class.java)?:return
			val locale = propertyHeader.name?:return
			val isValid = locale in stellarisLocalizationLocales
			if(!isValid) {
				val description = message("stellaris.localization.inspection.unsupportedLocale.description", locale)
				val quickFix = ChooseLocale(propertyHeader)
				holder.registerProblem(propertyHeader, description, quickFix)
			}
		}
	}

	private class ChooseLocale(
		element: StellarisLocalizationPropertyHeader
	) : LocalQuickFixAndIntentionActionOnPsiElement(element) {
		override fun getText(): String {
			return message("stellaris.localization.quickFix.chooseLocale")
		}

		override fun getFamilyName(): String {
			return text
		}

		override fun invoke(project: Project, file: PsiFile, editor: Editor?, startElement: PsiElement, endElement: PsiElement) {
			if(editor == null) return
			//选中该元素，要求重命名
			//selectElement(editor,startElement)
			//直接弹出popup，要求用户选择其中一个，然后替换propertyHeader
			val values = stellarisLocalizationPropertyHeaderCache.register(project)
			val popup = Popup(startElement as StellarisLocalizationPropertyHeader, values)
			JBPopupFactory.getInstance().createListPopup(popup).showInBestPositionFor(editor)
		}

		private class Popup(
			private val value:StellarisLocalizationPropertyHeader,
			values: Array<out StellarisLocalizationPropertyHeader>
		):BaseListPopupStep<StellarisLocalizationPropertyHeader>(message("stellaris.localization.quickFix.chooseLocale.header"),*values){
			override fun getIconFor(value: StellarisLocalizationPropertyHeader): Icon {
				return AllIcons.FileTypes.Properties
			}

			override fun getTextFor(value: StellarisLocalizationPropertyHeader): String {
				return value.name!!
			}

			override fun getDefaultOptionIndex(): Int {
				return 0
			}

			override fun onChosen(selectedValue: StellarisLocalizationPropertyHeader, finalChoice: Boolean): PopupStep<*> {
				//使用选择的元素替换原始元素
				//不能直接替换，不允许这样做
				//可以在WriteCommandAction里面执行
				WriteCommandAction.runWriteCommandAction(selectedValue.project){
					value.headerToken.replace(selectedValue.headerToken)
				}
				return PopupStep.FINAL_CHOICE
			}

			override fun isSpeedSearchEnabled(): Boolean {
				return true
			}
		}
	}
}
