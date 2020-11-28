import java.io.*
import java.util.*
import java.util.concurrent.*

fun main() {
	val gamePath = "D:\\Programs\\Steam\\steamapps\\common\\Stellaris"
	val modPaths = (File("D:\\Programs\\Steam\\steamapps\\workshop\\content\\281990").listFiles() ?: return)
		.mapTo(mutableListOf()){ it.path }
		.apply{
			add("D:\\Documents\\Projects\\Managed\\Stellaris-Mods\\kitsune\\package-mini")
		}
	val savedPath = "D:\\Documents\\Projects\\Managed\\Idea-Plugins\\stellaris-modding-support\\tmp"
	selectOverriddenFiles(gamePath, modPaths, savedPath)
}

private val ignoredFileNameSnippets = arrayOf("README","CHANGELOG","CREDITS")

/**
 * 根据指定的游戏路径和一组模组路径，以及保存路径，找出有冲突的脚本文件，统一保存到该保存路径中。
 */
private fun selectOverriddenFiles(gamePath:String,modPaths:List<String>,savedPath:String){
	val gameFile = File(gamePath)
	val modFiles = modPaths.map { modPath -> File(modPath) }
	val savedFile = File(savedPath)
	
	val gameScriptFiles = gameFile.walk().filter { it.isFile && it.extension == "txt" }.map{it.relativeTo(gameFile)}
	val gameScriptFileMap = gameScriptFiles.associateBy { it.path }
	val savedScriptFileMap = ConcurrentHashMap<File,CopyOnWriteArrayList<Pair<File,File>>>()
	
	println("Select overrided files ...")
	
	for(modFile in modFiles) {
		val modScriptFiles = modFile.walk().filter { it.isFile && it.extension == "txt" }.map{it.relativeTo(modFile)}
		for(modScriptFile in modScriptFiles) {
			//如果找到了有冲突的文件，则添加到缓存中
			val key = modScriptFile.path
			//排除readme和changelog和credits
			if(key.substringBefore(".").toUpperCase() !in ignoredFileNameSnippets) {
				val gameScriptFile = gameScriptFileMap[key]
				//排除不存在的情况
				if(gameScriptFile != null) {
					println("Add overrided file '${modScriptFile.name}'.")
					savedScriptFileMap.getOrPut(gameScriptFile) { CopyOnWriteArrayList() }.add(modFile to modScriptFile)
				}
			}
		}
	}
	
	val size = savedScriptFileMap.size
	println("Select overrided files of $size groups.")
	
	for((gameScriptFile, modScriptFileMap) in savedScriptFileMap) {
		gameFile.resolve(gameScriptFile).copyTo(savedFile.resolve(gameScriptFile.path))
		var n = 1
		for((modFile,modScriptFile) in modScriptFileMap) {
			modFile.resolve(modScriptFile).copyTo(savedFile.resolve(modScriptFile.path.handlePath(n++)))
		}
	}
	
	println("Save overrided files of $size groups.")
}

private fun String.handlePath(marker:Any): String {
	val separatorIndex = this.lastIndexOf('.')
	return when{
		separatorIndex == -1 ->  this + UUID.randomUUID()
		else -> {
			this.substring(0,separatorIndex) + "_" + marker + this.substring(separatorIndex,this.length)
		}
	}
}

