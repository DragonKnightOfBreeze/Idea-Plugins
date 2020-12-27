package com.windea.plugin.idea.paradox.model

class ParadoxPath(
	val subPaths:List<String>
){
	val parentSubPaths = subPaths.dropLast(1)
	val name = subPaths.lastOrNull().orEmpty()
	val parent = parentSubPaths.joinToString("/","/")
	val path = subPaths.joinToString("/","/")
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