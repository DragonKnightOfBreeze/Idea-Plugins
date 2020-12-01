package script

import com.windea.plugin.idea.stellaris.*
import org.junit.*
import java.io.*

fun main() {
	val root =File("D:\\Documents\\Projects\\Managed\\Idea-Plugins\\paradox-language-support")
	root.replaceInDirectoryMatchCase("pdx","paradox")
}

/**
 * 匹配并保留显示格式（如camelCase），替换指定目录下的任意地方的字符串（包括文件名、文件内容）。
 */
private fun File.replaceInDirectoryMatchCase(string:String,replacement:String){
	println("Replace everywhere in directory '$path' match case.")
	println("Replace file names and contents ...")
	this.walkBottomUp().forEach {
		if(it.isFile) {
			val text = it.readText()
			it.writeText(text.replaceMatchCase(string, replacement))
		}
		if(it.name.contains(string,true)){
			it.renameTo(File(it.path.replaceMatchCase(string,replacement)))
		}
	}
	println("Done.")
}

/**
 * 匹配并保留显示格式（如camelCase），替换当前字符串中子字符串。
 */
private fun String.replaceMatchCase(string:String,replacement: String):String{
	var result = this
	while(true) {
		val index = result.indexOf(string, 0, true)
		if(index == -1) break
		val fqString = result.substring(index, index + string.length)
		val fqReplacement = replacement.matchCase(fqString)
		result = result.replace(fqString, fqReplacement)
	}
	return result
}

/**
 * 将当前字符串替换成指定的字符串的格式。
 */
private fun String.matchCase(string:String):String{
	val chars = string.toCharArray()
	return when {
		chars.isEmpty() -> this
		chars.all{ it.isLowerCase()} -> this.toLowerCase()
		chars.all { it.isUpperCase() } -> this.toUpperCase()
		chars[0].isUpperCase() && chars.drop(1).all { it.isLowerCase() } -> this.toCapitalizedCase()
		else -> this
	}
}

