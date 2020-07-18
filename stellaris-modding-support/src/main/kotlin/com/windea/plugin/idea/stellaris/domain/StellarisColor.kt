package com.windea.plugin.idea.stellaris.domain

/**
 * 本地化文件的颜色枚举
 */
enum class StellarisColor(
	val text: String,
	val color:String?,
	val description: String
) {
	Default("!",null,"(color) Default"),
	Blue("B","blue","(color) Blue"),
	Teal("E","teal","(color) Teal"),
	Green("G","green","(color) Green"),
	Orange("H","orange","(color) Orange"),
	Brown("L","brown","(color) Brown"),
	Purple("M","purple","(color) Purple"),
	LightRed("P","lightred","(color) Light Red"),
	Red("R","red","(color) Red"),
	DarkOrange("S","darkorange","(color) Dark Orange"),
	LightGrey("T","lightgrey","(color) Light Grey"),
	White("W","white","(color) White"),
	Yellow("Y","yellow","(color) Yellow");

	fun format(text: String) = "£${this.text}$text£!"

	fun formatRemain(text: String) = "£${this.text}$text"
}
