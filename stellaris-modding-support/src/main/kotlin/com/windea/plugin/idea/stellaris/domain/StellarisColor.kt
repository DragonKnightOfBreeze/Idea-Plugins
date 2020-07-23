package com.windea.plugin.idea.stellaris.domain

import com.intellij.util.ui.*
import com.windea.plugin.idea.stellaris.*
import java.awt.*

/**
 * 本地化文件的颜色枚举
 */
enum class StellarisColor(
	val key: String,
	val description: String,
	val colorName: String,
	val color: Color
) {
	Blue("B", "(color) Blue", "blue", Color.BLUE),
	Teal("E", "(color) Teal", "teal", Color(0x8080)),
	Green("G", "(color) Green", "green", Color.GREEN),
	Orange("H", "(color) Orange", "orange", Color.ORANGE),
	Brown("L", "(color) Brown", "brown", Color(0xa52a2a)),
	Purple("M", "(color) Purple", "purple", Color(0x800080)),
	LightRed("P", "(color) Light Red", "lightred", Color(0xcd5c5c)),
	Red("R", "(color) Red", "red", Color.RED),
	DarkOrange("S", "(color) Dark Orange", "darkorange", Color(0xff8c00)),
	LightGrey("T", "(color) Light Grey", "lightgrey", Color.LIGHT_GRAY),
	White("W", "(color) White", "white", Color.WHITE),
	Yellow("Y", "(color) Yellow", "yellow", Color.YELLOW);

	val icon = ColorIcon(16,color)
	val popupText = "'$key' - $description"

	fun format(text: String) = "£${this.key}$text£!"

	fun formatRemain(text: String) = "£${this.key}$text"

	companion object{
		val map = values().associateBy { it.key }
		val keys = values().mapArray { it.key }
	}
}
