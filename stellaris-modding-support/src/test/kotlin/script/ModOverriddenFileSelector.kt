package script

import com.windea.plugin.idea.stellaris.*
import java.io.*
import java.util.*
import java.util.concurrent.*

fun main() {
	val gamePath = "D:\\Programs\\Steam\\steamapps\\common\\Stellaris"
	val modPaths = (File("D:\\Programs\\Steam\\steamapps\\workshop\\content\\281990").listFiles() ?: return)
		.mapTo(mutableListOf()){ it.path }
		.apply{
			//add("D:\\Documents\\Projects\\Managed\\Stellaris-Mods\\kitsune\\package-mini")
			add("D:\\Documents\\Projects\\Managed\\Stellaris-Mods\\kareeze-stories\\src")
		}
	val savedPath = "D:\\Documents\\Projects\\Managed\\Idea-Plugins\\stellaris-modding-support\\tmp"
	selectOverriddenFiles(gamePath, modPaths, savedPath)
}

private val ignoredFileNameSnippets = arrayOf("README","CHANGELOG","CREDITS")

/**
 * 根据指定的游戏路径和一组模组路径，以及保存路径，找出与原版有冲突的脚本文件，统一保存到该保存路径中。
 * 默认要求至少2个mod的文件与原版有冲突才会保存。
 */
private fun selectOverriddenFiles(gamePath:String,modPaths:List<String>,savedPath:String,minSize:Int=2){
	val gameFile = File(gamePath)
	val modFiles = modPaths.map { modPath -> File(modPath) }
	val savedFile = File(savedPath)
	
	println("Prepare vanilla files ...")
	val gameScriptFiles = gameFile.walk().filter { it.isFile && it.extension == "txt" }.map{it.relativeTo(gameFile)}
	val gameScriptFileMap = gameScriptFiles.associateBy { it.path }
	val savedScriptFileMap = ConcurrentHashMap<File,CopyOnWriteArrayList<Pair<File,File>>>()
	
	println("Prepare mod names ...")
	val modNameMap = modFiles.associateWith { it.getModName() }
	
	println("Add overridden files ...")
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
					println("Add overridden file '${modScriptFile.name}'.")
					savedScriptFileMap.getOrPut(gameScriptFile) { CopyOnWriteArrayList() }.add(modFile to modScriptFile)
				}
			}
		}
	}
	
	val fqSavedScriptFileMap = savedScriptFileMap.filterValues { v->v.size >=minSize }
	val size = fqSavedScriptFileMap.size
	println("Select overridden files of $size groups.")
	
	for((gameScriptFile, modScriptFileMap) in fqSavedScriptFileMap) {
		gameFile.resolve(gameScriptFile).copyTo(savedFile.resolve(gameScriptFile.path))
		for((modFile,modScriptFile) in modScriptFileMap) {
			val marker = modNameMap[modFile] ?: modFile.name
			modFile.resolve(modScriptFile).copyTo(savedFile.resolve(modScriptFile.path.handlePath(marker)))
		}
	}
	
	println("Save overridden files of $size groups.")
}

//找到当前模组目录的模组名字
private fun File.getModName():String?{
	return try {
		this.resolve("descriptor.mod").useLines {
			it.map { l -> l.trim() }.find { l -> l.startsWith("name") }
				?.substringAfter("=")?.trim()?.unquote()?.replace(":","")
		}
	}catch(e:Exception){
		null
	}
}

//处理原始的文件路径
private fun String.handlePath(marker:Any): String {
	val separatorIndex = this.lastIndexOf('.')
	return when{
		separatorIndex == -1 ->  this + marker
		else -> {
			this.substring(0,separatorIndex) + "__" + marker + this.substring(separatorIndex,this.length)
		}
	}
}

