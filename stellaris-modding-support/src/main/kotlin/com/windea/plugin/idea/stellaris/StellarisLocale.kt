package com.windea.plugin.idea.stellaris

/**
 * 本地化文件的语言区域枚举
 */
enum class StellarisLocale(
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

	val documentText = "(locale) $description"
	val popupText = "'$key' - $description"

	companion object {
		val values = values()
		val map = values().associateBy { it.key }
		val keys = values().mapArray { it.key }
	}
}
