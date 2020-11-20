package com.windea.plugin.idea.stellaris.localization

import com.intellij.codeInsight.documentation.*
import com.windea.plugin.idea.stellaris.*
import java.net.*
import java.net.http.*
import java.net.http.HttpResponse.*
import java.time.*
import java.util.concurrent.*


//https://qunxing.huijiwiki.com/
//https://stellaris.paradoxwikis.com/
//https://qunxing.huijiwiki.com/wiki/%E6%96%87%E4%BB%B6:Xxx.png
//https://stellaris.paradoxwikis.com/File:Xxx.png

/**
 * 图标地址的解析器
 *
 * 基于名称和stellaris.paradoxwikis.com上的文件解析图标地址。
 */
object StellarisLocalizationIconUrlResolver {
	private val timeout = Duration.ofMinutes(3)
	private val httpClient = HttpClient.newBuilder().connectTimeout(timeout).build()
	private val bodyHandler = BodyHandlers.ofLines()
	private val urlCache = ConcurrentHashMap<String, String>()
	private val executor = Executors.newCachedThreadPool()
	private val doResolveCache = CopyOnWriteArrayList<String>()

	private const val unknownIconUrl = "https://huiji-public.huijistatic.com/qunxing/uploads/d/dd/Unknown.png"
	
	val iconSize get() = DocumentationComponent.getQuickDocFontSize().size
	
	fun resolve(name: String,defaultToUnknown:Boolean=true): String {
		if(name.isEmpty()) return getDefaultUrl(defaultToUnknown)
		return try {
			//尝试从缓存中获取，如果获取到空字符串，则返回未知图标的地址
			val url = urlCache[name]
			if(url != null) {
				return when {
					url.isEmpty() -> getDefaultUrl(defaultToUnknown)
					else -> url
				}
			}
			//如果缓存中没有，则返回“正在解析中”，另开线程解析图标
			if(!doResolveCache.contains(name)) resolveUrlAsync(name)
			//"<code>(resolving icon)</code>"
			getDefaultUrl(defaultToUnknown)
		}catch(e: HttpTimeoutException){
			//"<code>(resolve icon timeout)</code>"
			getDefaultUrl(defaultToUnknown)
		}catch(e: Exception){
			e.printStackTrace()
			//"<code>(resolve icon error)</code>"
			getDefaultUrl(defaultToUnknown)
		}
	}
	
	private fun getDefaultUrl(defaultToUnknown:Boolean): String {
		return if(defaultToUnknown) unknownIconUrl else ""
	}
	
	private fun resolveUrlAsync(name: String){
		doResolveCache.add(name)
		executor.execute {
			val url = doResolveUrl(name)
			urlCache[name] = url
		}
	}
	
	private fun doResolveUrl(name: String): String {
		return doResolveUrlFromhuijiwiki(name) ?: doResolveUrlFromParadoxwikis(name) ?: ""
	}
	
	private const val huijiwikiPrefix = "<li><a href=\"#filelinks\">文件用途</a></li></ul><div class=\"fullImageLink\" id=\"file\"><a href=\""
	private const val huijiwikiPrefixLength = huijiwikiPrefix.length
	
	private fun doResolveUrlFromhuijiwiki(name: String): String? {
		val fullUrl = huijiwikiPngUrl(name)
		val httpResponse = httpClient.send(HttpRequest.newBuilder().GET().uri(URI.create(fullUrl)).build(), bodyHandler)
		println(httpResponse.statusCode())
		if(httpResponse.statusCode() == 200) {
			val lines = httpResponse.body()
			return lines.filter {
				it.startsWith(huijiwikiPrefix)
			}.map {
				val index = it.indexOf(huijiwikiPrefix)
				val startIndex = index + huijiwikiPrefixLength
				val endIndex = it.indexOf('"', startIndex + 1)
				it.substring(startIndex, endIndex)
			}.findFirst().orElseGet { null }
		}
		return null
	}
	
	private const val paradoxwikisPrefix = "<div class=\"fullImageLink\" id=\"file\"><a href=\""
	private const val paradoxwikisPrefixLength = paradoxwikisPrefix.length
	
	private fun doResolveUrlFromParadoxwikis(name: String): String? {
		val fullUrl = paradoxwikisPngUrl(name)
		val httpResponse = httpClient.send(HttpRequest.newBuilder().GET().uri(URI.create(fullUrl)).build(), bodyHandler)
		println(httpResponse.statusCode())
		if(httpResponse.statusCode() == 200) {
			val lines = httpResponse.body()
			return lines.filter {
				it.startsWith("<li>") && it.contains(paradoxwikisPrefix)
			}.map {
				val index = it.indexOf(paradoxwikisPrefix)
				val startIndex = index + paradoxwikisPrefixLength
				val endIndex = it.indexOf('"', startIndex + 1)
				it.substring(startIndex, endIndex)
			}.findFirst().orElseGet { null }
		}
		return null
	}
}

//fun main() {
//	val name = "pop"
//	println(measureNanoTime {
//		StellarisLocalizationIconUrlResolver.resolve(name).also{ println(it)}
//	})
//	while(true) {
//		Thread.sleep(1000)
//		println(measureNanoTime {
//			StellarisLocalizationIconUrlResolver.resolve(name).also { println(it) }
//		})
//	}
//}
