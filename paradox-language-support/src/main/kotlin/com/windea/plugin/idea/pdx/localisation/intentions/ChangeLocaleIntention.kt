package com.windea.plugin.idea.pdx.localisation.intentions

import com.intellij.codeInsight.intention.*
import com.intellij.openapi.command.WriteCommandAction.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.intellij.openapi.ui.popup.*
import com.intellij.openapi.ui.popup.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.message
import com.windea.plugin.idea.pdx.localisation.psi.*

class ChangeLocaleIntention : IntentionAction {
	companion object{
		val instance = ChangeLocaleIntention()
	}

	override fun startInWriteAction() = false

	override fun getText() = message("pdx.localisation.intention.changeLocale")

	override fun getFamilyName() = text

	override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean {
		if(editor == null || file == null) return false
		val element = file.findElementAt(editor.caretModel.offset)?.parent as? PdxLocalisationLocale
		return element != null
	}

	override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
		if(editor == null || file == null) return
		val element = file.findElementAt(editor.caretModel.offset)?.parent as? PdxLocalisationLocale ?: return
		val values = localisationLocaleCache.register(project)
		JBPopupFactory.getInstance().createListPopup(Popup(element, values)).showInBestPositionFor(editor)
	}

	private class Popup(
		private val value: PdxLocalisationLocale,
		values: Array<PdxLocalisationLocale>
	) : BaseListPopupStep<PdxLocalisationLocale>(message("pdx.localisation.intention.changeLocale.title"), *values) {
		override fun getTextFor(value: PdxLocalisationLocale) = value.pdxLocale!!.popupText

		override fun getDefaultOptionIndex() = 0

		override fun isSpeedSearchEnabled(): Boolean = true

		override fun onChosen(selectedValue: PdxLocalisationLocale?, finalChoice: Boolean): PopupStep<*>? {
			if(selectedValue != null) {
				//需要在WriteCommandAction里面执行
				runWriteCommandAction(selectedValue.project) { value.setName(selectedValue.name!!) }
			}
			return PopupStep.FINAL_CHOICE
		}
	}
}

