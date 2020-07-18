@file:Suppress("PropertyName")

package com.windea.plugin.idea.stellaris.localization.settings

import com.intellij.psi.codeStyle.*
import com.windea.plugin.idea.stellaris.*


class StellarisLocalizationCodeStyleSettings(
	container: CodeStyleSettings
) : CustomCodeStyleSettings(stellarisLocalizationNamePc, container) {
	//DELAY 不清楚是否允许这种语法
	////这里需要声明自定义设置项对应的var，名字需要对应，需要添加@JvmField
	//@JvmField var ALIGN_PROPERTY_VALUES = true
	//
	////以下相当于自定义设置项的KEY
	//enum class Option {
	//	ALIGN_PROPERTY_VALUES
	//}
}

