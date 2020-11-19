package com.windea.plugin.idea.stellaris.localization

import java.net.*
import java.net.http.*
import java.net.http.HttpResponse.*
import java.util.concurrent.*

/**
 * 图标地址的解析器
 *
 * 基于名称和stellaris.paradoxwikis.com上的文件解析图标地址。
 */
object StellarisLocalizationIconUrlResolver {
	private val httpClient = HttpClient.newHttpClient()
	private val bodyHandler = BodyHandlers.ofLines()
	private val urlCache = ConcurrentHashMap<String, String>()
	private const val host = "https://stellaris.paradoxwikis.com"
	private const val prefix = "<div class=\"fullImageLink\" id=\"file\"><a href=\""
	private const val prefixLength = prefix.length
	
	fun resolve(name: String): String {
		return urlCache.getOrPut(name){ doResolveUrl(name) }
	}
	
	//TODO 继续提高性能
	
	//https://stellaris.paradoxwikis.com/images/2/20/Research.png
	private fun doResolveUrl(name:String): String {
		val httpResponse = httpClient.send(httpRequest(imageName(name)), bodyHandler)
		if(httpResponse.statusCode() == 200) {
			val lines = httpResponse.body()
			var hasResult = false
			return lines.filter {
				if(hasResult) return@filter false
				val isResult = it.contains(prefix)
				if(isResult) hasResult = true
				isResult
			}.map {
				val index = it.indexOf(prefix)
				val startIndex = index + prefixLength
				val endIndex = it.indexOf('"', startIndex + 1)
				host + it.substring(startIndex, endIndex)
			}.findFirst().orElseGet { "" }
		}
		return ""
	}
	
	private fun imageName(name:String):String{
		return if(name.isEmpty()) "" else name[0].toUpperCase() + name.substring(1)
	}
	
	private fun httpRequest(name: String): HttpRequest? {
		return HttpRequest.newBuilder().GET().uri(URI.create("https://stellaris.paradoxwikis.com/File:$name.png")).build()
	}
}
