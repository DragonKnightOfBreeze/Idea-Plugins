package com.windea.plugin.idea.stellaris.domain

import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*

//"l_braz_por" | "l_english" | "l_french" | "l_german" | "l_ponish" | "l_russian" | "l_simp_chinese" | "l_spanish"

/**
 * 本地化文件的语言区域枚举
 */
enum class StellarisLocale(
	val key: String,
	val description: String
) {
	SIMP_CHINESE("l_simp_chinese", "(locale) Simple Chinese"),
	ENGLISH("l_english", "(locale) English"),
	BRAZ_POR("l_braz_por", "(locale) Brazil Portuguese"),
	FRENCH("l_french", "(locale) French"),
	GERMAN("l_german", "(locale) German"),
	PONISH("l_ponish", "(locale) Ponish"),
	RUSSIAN("l_russian", "(locale) Russian"),
	SPANISH("l_spanish", "(locale) Spanish");

	val popupText = "'$key' - $description"

	companion object {
		val map = values().associateBy { it.key }
		val keys = values().mapArray { it.key }
	}
}
