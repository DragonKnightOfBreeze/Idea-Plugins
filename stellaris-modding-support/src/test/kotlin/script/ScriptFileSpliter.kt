package script

import java.io.*

fun main(){
	splitFile("stellaris-modding-support\\src\\test\\resources\\lib\\common")
	splitFile("stellaris-modding-support\\src\\test\\resources\\lib\\prescripted_countries")
}

private val skippedFileNames = arrayOf("00_colors.txt")

private fun splitFile(directory:String){
	val dirFile = File(directory)
	dirFile.walk().forEach { file ->
		when {
			file.isDirectory -> {
				File(file.path.replace("\\lib","\\stdlib")).mkdirs()
			}
			file.name in skippedFileNames -> {
				file.copyTo(File(file.path.replace("\\lib","\\stdlib")),true)
			}
			else -> {
				var name = ""
				val content = StringBuilder()
				var inContent = false
				file.forEachLine { line ->
					when{
						!inContent && line.isEmpty() -> {}
						!inContent && line.startsWith("#") -> {}
						!inContent && line.first().isLetterOrDigit() -> {
							name = line.substringBefore('=').trimEnd()
							content.appendLine(line)
							inContent = true
						}
						inContent && line == "}" -> {
							content.appendLine("}")
							File(file.parent.replace("\\lib","\\stdlib"),"$name.txt").writeText(content.toString())
							content.clear()
							inContent = false
						}
						inContent -> {
							content.appendLine(line)
						}
					}
				}
			}
		}
	}
}
