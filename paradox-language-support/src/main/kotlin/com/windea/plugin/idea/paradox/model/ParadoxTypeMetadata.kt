package com.windea.plugin.idea.paradox.model

import com.windea.plugin.idea.paradox.*
import java.util.*

data class ParadoxTypeMetadata(
	val type:String,
	val name:String,
	val localisation: Map<ConditionalString,String>,
	val scopes:Map<String,String>,
	val fromVersion:String
){
	override fun equals(other: Any?): Boolean {
		return other is ParadoxTypeMetadata && type == other.type && name == other.name
	}
	
	override fun hashCode(): Int {
		return Objects.hash(type, name)
	}
	
	override fun toString(): String {
		return "$name: $type"
	}
}