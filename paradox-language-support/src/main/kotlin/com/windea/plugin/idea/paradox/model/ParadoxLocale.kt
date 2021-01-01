package com.windea.plugin.idea.paradox.model

import com.windea.plugin.idea.paradox.*

enum class ParadoxLocale(
	val key: String,
	val description: String
) {
	SIMP_CHINESE("l_simp_chinese", "Simple Chinese"),
	ENGLISH("l_english", "English"),
	BRAZ_POR("l_braz_por", "Brazil Portuguese"),
	FRENCH("l_french", "French"),
	GERMAN("l_german", "German"),
	PONISH("l_polish", "Polish"),
	RUSSIAN("l_russian", "Russian"),
	SPANISH("l_spanish", "Spanish"),
	DEFAULT("l_default", "Default");

	val popupText = "'$key' - $description"

	companion object {
		val values = values()
		val map = values().associateBy { it.key }
		val keys = values().mapArray { it.key }
	}
}

