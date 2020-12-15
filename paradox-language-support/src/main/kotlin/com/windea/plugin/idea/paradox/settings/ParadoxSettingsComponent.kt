package com.windea.plugin.idea.paradox.settings

import com.intellij.ui.layout.*
import com.windea.plugin.idea.paradox.*
import javax.swing.*

class ParadoxSettingsComponent {
	companion object{
		private val performanceTitle = message("paradox.settings.performance")
		private val resolveReferencesName = message("paradox.settings.resolveReferences")
		private val validateScriptName = message("paradox.settings.validateScripts")
	}
	
	lateinit var resolveReferencesCheckBox: JCheckBox
	lateinit var validateScriptsCheckBox: JCheckBox
	
	val panel = panel {
		titledRow(performanceTitle) {
			resolveReferencesCheckBox = checkBox(resolveReferencesName, true).withLeftGap().component
			validateScriptsCheckBox = checkBox(validateScriptName, true).withLeftGap().component
		}
	}
}