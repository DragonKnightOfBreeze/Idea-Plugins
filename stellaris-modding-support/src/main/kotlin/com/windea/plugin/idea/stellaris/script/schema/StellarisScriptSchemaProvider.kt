package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.openapi.vfs.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.impl.*
import com.windea.plugin.idea.stellaris.script.*

//name: descriptor.mod, events/*.txt

class StellarisScriptSchemaProvider(
	private val name:String,
	private val schemaFile: VirtualFile
) : JsonSchemaFileProvider {
	private val reversedPathList = name.split("/").reversed()

	override fun isAvailable(file: VirtualFile): Boolean {
		return file.fileType == StellarisScriptFileType && isMatched(file)
	}

	//判断被匹配的文件（脚本文件）是否与schema名字（ant路径）相匹配
	private fun isMatched(file: VirtualFile): Boolean {
		var current = file
		reversedPathList.forEach { path ->
			if(!matches(current.name,path)) return false
			current = current.parent
		}
		return true
	}
	
	//判断指定的路径是否匹配指定的ant路径，允许的通配符：?, *
	private fun matches(path:String,antPath:String):Boolean{
		val antPathChars = antPath.toCharArray()
		val pathChars = path.toCharArray()
		val antPathLength = antPath.length
		val pathLength = path.length
		var antPathCharIndex = 0
		var pathCharIndex = 0
		var antPathChar:Char
		var pathChar:Char
		while(true){
			antPathChar = antPathChars[antPathCharIndex]
			pathChar = pathChars[pathCharIndex]
			when{
				//直接跳过
				antPathChar == '?' -> {}
				//跳到下一个匹配的地方
				antPathChar == '*' -> {
					antPathCharIndex++
					if(antPathCharIndex == antPathLength) return true
					val nextAntPathChar = antPathChars[antPathCharIndex]
					while(pathCharIndex < pathLength){
						if(pathChars[pathCharIndex] == nextAntPathChar){
							antPathCharIndex ++
							pathCharIndex ++
							continue
						}
						pathCharIndex ++
					}
					return true
				}
				//要求匹配
				else -> {
					if(antPathChar != pathChar) return false
				}
			}
			antPathCharIndex ++
			pathCharIndex ++
			if(pathCharIndex == pathLength){
				while(antPathCharIndex != antPathLength && antPathChars[antPathCharIndex] == '*') antPathCharIndex ++
				return antPathCharIndex == antPathLength
			}
		}
	}

	override fun getName(): String {
		return name
	}

	override fun getSchemaFile(): VirtualFile {
		return schemaFile
	}

	override fun getSchemaType(): SchemaType {
		return SchemaType.schema
	}

	override fun getSchemaVersion(): JsonSchemaVersion {
		return JsonSchemaVersion.SCHEMA_7
	}
}
