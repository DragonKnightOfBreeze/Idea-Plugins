package com.windea.plugin.idea.bbcode

import java.net.*
import java.util.*

//region Stdlib
fun Boolean.toInt() = if(this) 1 else 0

inline fun <reified T : Any> String.toClassPathResource(): URL = T::class.java.getResource(this)


@Suppress("UNCHECKED_CAST")
fun <T> Array<out T?>.cast() = this as Array<T>

inline fun <T, reified R> List<T>.mapArray(block: (T) -> R): Array< R> {
	return Array<R>(size) { block(this[it]) }
}

inline fun <T, reified R> Array<out T>.mapArray(block: (T) -> R): Array< R> {
	return Array<R>(size) { block(this[it]) }
}

inline fun <T, reified R> Sequence<T>.mapArray(block: (T) -> R): Array< R> {
	return this.toList().mapArray(block)
}


fun String.quote() = if(startsWith('"') && endsWith('"')) this else "\"$this\""

fun String.quoteIfNecessary() = if(contains("\\s".toRegex())) quote() else this

fun String.unquote() = if(startsWith('"') && endsWith('"')) substring(1, length - 1) else this


fun String.truncate(limit:Int) = if(this.length <= limit) this else this.take(limit) + "..."


fun CharSequence.indicesOf(char: Char, ignoreCase: Boolean = false): MutableList<Int> {
	val indices = mutableListOf<Int>()
	var lastIndex = indexOf(char, 0, ignoreCase)
	while(lastIndex != -1) {
		indices += lastIndex
		lastIndex = indexOf(char, lastIndex + 1, ignoreCase)
	}
	return indices
}

inline fun <reified T> T.toSingletonArray():Array<T>{
	return arrayOf(this)
}

inline fun <reified T> Sequence<T>.toArray():Array<T>{
	return this.toList().toTypedArray()
}

fun <T> T.toSingletonList():List<T>{
	return Collections.singletonList(this)
}

fun <T:Any> T?.toSingletonOrEmpty(): MutableCollection<T> {
	return if(this == null) Collections.emptySet() else Collections.singleton(this)
}
//endregion
