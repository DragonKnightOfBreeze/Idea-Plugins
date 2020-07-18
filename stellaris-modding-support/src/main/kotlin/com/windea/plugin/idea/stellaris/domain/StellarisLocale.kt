package com.windea.plugin.idea.stellaris.domain

//"l_braz_por" | "l_english" | "l_french" | "l_german" | "l_ponish" | "l_russian" | "l_simp_chinese" | "l_spanish"

/**
 * 本地化文件的语言区域枚举
 */
enum class StellarisLocale(
	val text: String,
	val description:String
) {
	SIMP_CHINESE("simp_chinese","(locale) Simple Chinese"),
	ENGLISH("english","(locale) English"),
	BRAZ_POR("braz_por","(locale) Brazil Portuguese"),
	FRENCH("french","(locale) French"),
	GERMAN("german","(locale) German"),
	PONISH("ponish","(locale) Ponish"),
	RUSSIAN("russian","(locale) Russian"),
	SPANISH("spanish","(locale) Spanish");
}
