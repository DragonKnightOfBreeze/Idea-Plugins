package com.windea.plugin.idea.stellaris.localization.intentions

import com.intellij.codeInsight.intention.*
import com.intellij.openapi.command.WriteCommandAction.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.intellij.openapi.ui.popup.*
import com.intellij.openapi.ui.popup.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class ChangeSerialNumberIntention : IntentionAction {
	companion object{
		val instance = ChangeSerialNumberIntention()
	}

	override fun startInWriteAction() = false

	override fun getText() = message("stellaris.localization.intention.changeSerialNumber")

	override fun getFamilyName() = text

	override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean {
		if(editor == null || file == null) return false
		val element = file.findElementAt(editor.caretModel.offset)?.parent as? StellarisLocalizationSerialNumber
		return element != null
	}

	override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
		if(editor == null || file == null) return
		val element = file.findElementAt(editor.caretModel.offset)?.parent as? StellarisLocalizationSerialNumber ?: return
		val values = localizationSerialNumberCache.register(project)
		JBPopupFactory.getInstance().createListPopup(Popup(element, values)).showInBestPositionFor(editor)
	}

	private class Popup(
		private val value: StellarisLocalizationSerialNumber,
		values: Array<StellarisLocalizationSerialNumber>
	) : BaseListPopupStep<StellarisLocalizationSerialNumber>(message("stellaris.localization.intention.changeSerialNumber.title"), *values){
		override fun getTextFor(value: StellarisLocalizationSerialNumber) = value.stellarisSerialNumber!!.popupText

		override fun getDefaultOptionIndex() = 0

		override fun isSpeedSearchEnabled(): Boolean = true

		override fun onChosen(selectedValue: StellarisLocalizationSerialNumber?, finalChoice: Boolean): PopupStep<*>? {
			if(selectedValue!= null) {
				//需要在WriteCommandAction里面执行
				runWriteCommandAction(selectedValue.project) { value.setName(selectedValue.name!!) }
			}
			return PopupStep.FINAL_CHOICE
		}
	}
}
