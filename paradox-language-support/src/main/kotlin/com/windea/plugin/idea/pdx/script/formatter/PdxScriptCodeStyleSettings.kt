@file:Suppress("PropertyName")

package com.windea.plugin.idea.stellaris.script.formatter

import com.intellij.psi.codeStyle.*
import com.windea.plugin.idea.stellaris.*

class StellarisScriptCodeStyleSettings(
	container: CodeStyleSettings
): CustomCodeStyleSettings(stellarisScriptNamePc,container){
	//这里需要声明自定义设置项对应的var，名字需要对应，需要添加@JvmField
	@JvmField var SPACE_AROUND_VARIABLE_SEPARATOR = true
	@JvmField var SPACE_AROUND_PROPERTY_SEPARATOR = true
	@JvmField var SPACE_WITHIN_BRACES = true

	//以下相当于自定义设置项的KEY
	enum class Option {
		SPACE_AROUND_VARIABLE_SEPARATOR,
		SPACE_AROUND_PROPERTY_SEPARATOR,
		SPACE_WITHIN_BRACES
	}
}
