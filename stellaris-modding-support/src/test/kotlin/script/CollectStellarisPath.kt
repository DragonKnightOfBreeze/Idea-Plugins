package script

import java.io.*

fun main() {
	val rootPath = "stellaris-modding-support\\src\\test\\resources\\stdlib"
	val propsPath = "stellaris-modding-support\\src\\test\\resources\\types.properties"
	val props = readFromProps(propsPath)
	val paths = collectStellarisPath(rootPath)
	writeToProps(propsPath,paths,props)
	
}

private fun collectStellarisPath(path:String):List<String>{
	val rootFile = File(path)
	val rootPath = rootFile.path
	return rootFile.walk().mapNotNull { file->
		if(!file.isDirectory) return@mapNotNull null
		val p = file.path.removePrefix(rootPath).replace("\\","/")
		if(p.isBlank()) null else p
	}.toList()
}

private fun readFromProps(path:String): Map<String, String> {
	return File(path).readText().lines().map {
		val kv = it.split('=')
		kv[0].trim() to kv[1]
	}.toMap()
}

private fun writeToProps(path:String,paths:List<String>,props:Map<String,String>){
	val text = paths.joinToString("\n"){ "${props[it].let{ s -> if(s == null || s.isBlank()) it else s}} = $it" }
	File(path).writeText(text)
}
