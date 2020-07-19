package com.windea.plugin.idea.stellaris.localization.intentions;

import com.intellij.codeInsight.intention.*
import com.intellij.icons.*
import com.intellij.openapi.command.WriteCommandAction.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.intellij.openapi.ui.popup.*
import com.intellij.openapi.ui.popup.util.*
import com.intellij.psi.*
import com.intellij.util.ui.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import javax.swing.*

@ExtensionPoint
class StellarisLocalizationChangeLocaleIntention : IntentionAction {
	override fun startInWriteAction() = false

	override fun getText() = message("stellaris.localization.intention.changeLocale")

	override fun getFamilyName() = text

	override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean {
		if(editor == null || file == null) return false
		val element = file.findElementAt(editor.caretModel.offset)?.parent as? StellarisLocalizationPropertyHeader
		return element != null
	}

	override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
		if(editor == null || file == null) return
		val element = file.findElementAt(editor.caretModel.offset)?.parent as? StellarisLocalizationPropertyHeader ?: return
		val values = localizationPropertyHeaderCache.register(project)
		JBPopupFactory.getInstance().createListPopup(Popup(element, values)).showInBestPositionFor(editor)
	}

	private class Popup(
		private val value: StellarisLocalizationPropertyHeader,
		values: Array<StellarisLocalizationPropertyHeader>
	) : BaseListPopupStep<StellarisLocalizationPropertyHeader>(message("stellaris.localization.intention.changeLocale.title"), *values){
		override fun getIconFor(value: StellarisLocalizationPropertyHeader) = AllIcons.FileTypes.Properties

		override fun getTextFor(value: StellarisLocalizationPropertyHeader) = value.locale!!.popupText

		override fun getDefaultOptionIndex() = 0

		override fun isSpeedSearchEnabled(): Boolean = true

		override fun onChosen(selectedValue: StellarisLocalizationPropertyHeader?, finalChoice: Boolean): PopupStep<*>? {
			if(selectedValue!= null) {
			//需要在WriteCommandAction里面执行
				runWriteCommandAction(selectedValue.project) { value.name = selectedValue.name }
			}
			return PopupStep.FINAL_CHOICE
		}
	}
}

