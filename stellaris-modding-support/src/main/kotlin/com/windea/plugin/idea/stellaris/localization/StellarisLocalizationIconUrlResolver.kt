package com.windea.plugin.idea.stellaris.localization

import java.net.*
import java.net.http.*
import java.net.http.HttpResponse.*
import java.time.*
import java.util.concurrent.*
import kotlin.system.*

/**
 * 图标地址的解析器
 *
 * 基于名称和stellaris.paradoxwikis.com上的文件解析图标地址。
 */
object StellarisLocalizationIconUrlResolver {
	private  val timeout = Duration.ofMinutes(3) //3分钟
	private val httpClient = HttpClient.newBuilder().connectTimeout(timeout).build()
	private val bodyHandler = BodyHandlers.ofLines()
	private val urlCache = ConcurrentHashMap<String, String>()
	private const val host = "https://stellaris.paradoxwikis.com"
	private const val prefix = "<div class=\"fullImageLink\" id=\"file\"><a href=\""
	private const val prefixLength = prefix.length
	private const val suffixLength = 8
	private const val length = prefixLength + suffixLength
	private const val unknownIconUrl = "https://stellaris.paradoxwikis.com/images/thumb/d/dd/Unknown.png/24px-Unknown.png"
	private val iconNameMap = ConcurrentHashMap<String,String>().apply{
		put("pops","Pop")
	}
	
	fun resolve(name: String): String {
		return try {
			urlCache.getOrPut(name) { doResolveUrl(name) }
		}catch(e:TimeoutException){
			"(resolve icon url timeout)"
		}
	}
	
	//平均5~20s
	
	//存在icon_parameter的情况只有leader_skill和fleet_status
	//需要处理的图标名：planetsize，pops->pop
	
	//https://stellaris.paradoxwikis.com/images/2/20/Research.png
	private fun doResolveUrl(name:String): String {
		val iconName = iconName(name)
		if(iconName.isEmpty()) return ""
		val httpResponse = httpClient.send(httpRequest(iconName), bodyHandler)
		if(httpResponse.statusCode() == 200) {
			val lines = httpResponse.body()
			return lines.filter {
				 it.startsWith("<li>") && it.contains(prefix)
			}.map {
				val index = it.indexOf(prefix)
				val startIndex = index + prefixLength
				val endIndex = it.indexOf('"', startIndex + 1)
				host + it.substring(startIndex, endIndex)
			}.findFirst().orElseGet { unknownIconUrl }
		}
		return unknownIconUrl
	}
	
	private fun iconName(name:String):String{
		if(name.isEmpty()) return ""
		val handledIconName = iconNameMap[name]
		if(handledIconName != null) return handledIconName
		return name[0].toUpperCase() + name.substring(1)
	}
	
	private fun httpRequest(name: String): HttpRequest? {
		return HttpRequest.newBuilder().GET().uri(URI.create("https://stellaris.paradoxwikis.com/File:$name.png")).build()
	}
}

fun main() {
	println(measureNanoTime {
		StellarisLocalizationIconUrlResolver.resolve("pop").also{ println(it)}
	})
	println(measureNanoTime {
		StellarisLocalizationIconUrlResolver.resolve("research").also{ println(it)}
	})
}
