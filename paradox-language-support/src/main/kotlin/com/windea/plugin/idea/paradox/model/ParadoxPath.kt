package com.windea.plugin.idea.paradox.model

class ParadoxPath(
	val subPaths:List<String>
){
	val path = subPaths.joinToString("/")
	val fileName = subPaths.lastOrNull().orEmpty()
	val fileExtension = fileName.substringAfterLast('.') 
	val parentSubPaths = subPaths.dropLast(1)
	val parent = parentSubPaths.joinToString("/")
	val root = parentSubPaths.firstOrNull().orEmpty()
	val length = subPaths.size
	
	override fun equals(other: Any?): Boolean {
		return other is ParadoxPath && path == other.path
	}
	
	override fun hashCode(): Int {
		return path.hashCode()
	}
	
	override fun toString(): String {
		return path
	}
}