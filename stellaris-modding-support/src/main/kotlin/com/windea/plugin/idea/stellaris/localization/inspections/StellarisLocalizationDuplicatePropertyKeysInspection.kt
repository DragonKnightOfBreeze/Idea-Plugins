@file:Suppress("DuplicatedCode")

package com.windea.plugin.idea.stellaris.localization.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.intellij.openapi.ui.popup.*
import com.intellij.openapi.ui.popup.util.*
import com.intellij.psi.*
import com.intellij.util.*
import com.intellij.util.containers.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import javax.swing.*

@ExtensionPoint
class StellarisLocalizationDuplicatePropertyKeysInspection : LocalInspectionTool() {
	override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
		return Visitor(holder)
	}

	private class Visitor(private val holder: ProblemsHolder) : StellarisLocalizationVisitor() {
		override fun visitFile(element: PsiFile) {
			val file = element as? StellarisLocalizationFile ?: return
			val propertyGroup = file.properties.groupBy { it.name }
			for((key, values) in propertyGroup) {
				if(values.size <= 1) continue
				for(value in values) {
					val quickFix = NavigateToDuplicates(key, value, values)
					//第一个元素指定为file，则是在文档头部弹出，否则从psiElement上通过contextActions显示
					val description = message("stellaris.localization.inspection.duplicatePropertyKeys.description", key)
					holder.registerProblem(value.propertyKey, description, quickFix)
				}
			}
		}
	}

	private class NavigateToDuplicates(
		private val key: String,
		property: StellarisLocalizationProperty,
		duplicates: List<StellarisLocalizationProperty>
	) : LocalQuickFixAndIntentionActionOnPsiElement(property) {
		private val pointers = ContainerUtil.map(duplicates) { SmartPointerManager.createPointer(it) }

		override fun getText(): String {
			return message("stellaris.localization.quickFix.navigateToDuplicates")
		}

		override fun getFamilyName(): String {
			return text
		}

		override fun invoke(project: Project, file: PsiFile, editor: Editor?, startElement: PsiElement, endElement: PsiElement) {
			if(editor == null) return
			//当重复的属性只有另外一个时，直接导航即可
			//如果有多个，则需要创建listPopup
			if(pointers.size == 2) {
				val iterator = pointers.iterator()
				val next = iterator.next().element
				val toNavigate = if(next != startElement) next else iterator.next().element
				navigateToElement(editor, toNavigate)
			} else {
				val allElements = pointers.mapNotNull { it.element }.filter { it !== startElement }
				val popup = Popup(allElements, key, editor)
				JBPopupFactory.getInstance().createListPopup(popup).showInBestPositionFor(editor)
			}
		}

		private class Popup(
			values: List<StellarisLocalizationProperty>,
			private val key: String,
			private val editor: Editor
		) : BaseListPopupStep<PsiElement>(message("stellaris.localization.quickFix.navigateToDuplicates.header", key), values) {
			override fun getIconFor(aValue: PsiElement): Icon {
				return PlatformIcons.PROPERTY_ICON
			}

			override fun getTextFor(value: PsiElement): String {
				return message("stellaris.localization.quickFix.navigateToDuplicates.text", key, editor.document.getLineNumber(value.textOffset))
			}

			override fun getDefaultOptionIndex(): Int {
				return 0
			}

			override fun onChosen(selectedValue: PsiElement, finalChoice: Boolean): PopupStep<*>? {
				navigateToElement(editor, selectedValue)
				return PopupStep.FINAL_CHOICE
			}

			override fun isSpeedSearchEnabled(): Boolean {
				return true
			}
		}
	}
}
