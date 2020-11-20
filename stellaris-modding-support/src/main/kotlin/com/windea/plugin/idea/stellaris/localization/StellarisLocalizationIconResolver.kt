package com.windea.plugin.idea.stellaris.localization

import com.intellij.codeInsight.documentation.*
import com.windea.plugin.idea.stellaris.*
import java.net.*
import java.net.http.*
import java.net.http.HttpResponse.*
import java.time.*
import java.util.concurrent.*
import kotlin.system.*

//平均5~20s

//存在icon_parameter的情况只有leader_skill和fleet_status
//需要处理的图标名：planetsize，pops->pop

//https://stellaris.paradoxwikis.com/images/2/20/Research.png

/**
 * 图标地址的解析器
 *
 * 基于名称和stellaris.paradoxwikis.com上的文件解析图标地址。
 */
object StellarisLocalizationIconUrlResolver {
	private val timeout = Duration.ofMinutes(5)
	private val httpClient = HttpClient.newBuilder().connectTimeout(timeout).build()
	private val bodyHandler = BodyHandlers.ofLines()
	private val urlCache = ConcurrentHashMap<String, String>()
	private val executor = Executors.newCachedThreadPool()
	private val doResolveCache = CopyOnWriteArrayList<String>()
	private const val host = "https://stellaris.paradoxwikis.com"
	private const val prefix = "<div class=\"fullImageLink\" id=\"file\"><a href=\""
	private const val prefixLength = prefix.length
	private const val unknownIconUrl = "https://stellaris.paradoxwikis.com/images/thumb/d/dd/Unknown.png/24px-Unknown.png"
	private val iconNameMap = ConcurrentHashMap<String, String>().apply{
		put("pops", "Pop")
	}
	
	val iconSize get() = DocumentationComponent.getQuickDocFontSize().size
	
	fun resolve(name: String): String {
		return try {
			//尝试从缓存中获取，如果获取到空字符串，则返回未知图标的地址
			val url = urlCache[name]
			if(url != null) {
				return when {
					url.isEmpty() -> unknownIconUrl
					else -> url
				}
			}
			//如果缓存中没有，则返回“正在解析中”，另开线程解析图标
			if(!doResolveCache.contains(name)) resolveUrlAsync(name)
			"<code>(resolving icon)</code>"
		}catch(e: HttpTimeoutException){
			"<code>(resolve icon timeout)</code>"
		}catch(e: Exception){
			e.printStackTrace()
			"<code>(resolve icon error)</code>"
		}
	}
	
	private fun resolveUrlAsync(name: String){
		doResolveCache.add(name)
		executor.execute {
			val url = doResolveUrl(name)
			urlCache[name] = url
		}
	}

	private fun doResolveUrl(name: String): String {
		val iconName = iconName(name)
		val httpResponse = httpClient.send(httpRequest(iconName), bodyHandler)
		println(httpResponse.statusCode())
		if(httpResponse.statusCode() == 200) {
			val lines = httpResponse.body()
			return lines.filter {
				!it.startsWith(" ") && it.contains(prefix)
			}.map {
				val index = it.indexOf(prefix)
				val startIndex = index + prefixLength
				val endIndex = it.indexOf('"', startIndex + 1)
				host + it.substring(startIndex, endIndex)
			}.findFirst().orElseGet { "" }
		}
		return ""
	}
	
	private fun iconName(name: String):String{
		if(name.isEmpty()) return ""
		val handledIconName = iconNameMap[name]
		if(handledIconName != null) return handledIconName
		return name[0].toUpperCase() + name.substring(1)
	}
	
	private fun httpRequest(name: String): HttpRequest? {
		return HttpRequest.newBuilder().GET().uri(URI.create("https://stellaris.paradoxwikis.com/File:$name.png")).build()
	}
}
