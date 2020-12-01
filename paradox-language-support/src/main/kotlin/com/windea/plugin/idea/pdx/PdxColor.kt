package com.windea.plugin.idea.pdx

import com.intellij.util.ui.*
import java.awt.*

/**
 * 本地化文件的颜色枚举
 */
enum class PdxColor(
	val key: String,
	val description: String,
	val color: Color,
	val rgbText:String
) {
	Blue("B", "Blue", Color(0x0000ff),"#0000ff"),
	Teal("E", "Teal", Color(0x008080),"#008080"),
	Green("G", "Green", Color(0x00ff00),"#00ff00"),
	Orange("H", "Orange", Color(0xffa500),"#ffa500"),
	Brown("L", "Brown", Color(0xa52a2a),"#a52a2a"),
	Purple("M", "Purple", Color(0x800080),"#800080"),
	LightRed("P", "Light Red", Color(0xcd5c5c),"#cd5c5c"),
	Red("R", "Red", Color(0xff0000),"#ff0000"),
	DarkOrange("S", "Dark Orange", Color(0xff8c00),"#ff8c00"),
	LightGrey("T", "Light Grey", Color(0xd3d3d3),"#d3d3d3"),
	White("W", "White", Color(0xffffff),"#ffffff"),
	Yellow("Y", "Yellow", Color(0xffff00),"#ffff00");

	val documentText = "(color) <b>$description</b>"
	val popupText = "'$key' - $description"
	val icon = ColorIcon(16, color)
	val gutterIcon = ColorIcon(12, color)

	fun format(text: String) = "£${key}$text£!"

	fun formatRemain(text: String) = "£${key}$text"

	companion object {
		val values = values()
		val map = values().associateBy { it.key }
		val keys = values().mapArray { it.key }
	}
}
