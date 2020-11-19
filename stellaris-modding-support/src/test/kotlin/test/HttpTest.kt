package test

import org.junit.*
import java.net.*
import java.net.http.*
import java.util.stream.*
import kotlin.system.*

class HttpTest {
	//<div class="fullImageLink" id="file"><a href="/images/2/20/Research.png"><img alt="File:Research.png" src="/images/2/20/Research.png" width="38" height="38" data-file-width="38" data-file-height="38"></a><div class="mw-filepage-resolutioninfo">No higher resolution available.</div></div>
	
	//200
	@Test
	fun test1(){
		//https://stellaris.paradoxwikis.com/File:Research.png
		//https://stellaris.paradoxwikis.com/images/2/20/Research.png
		println(measureNanoTime {
			val httpClient = HttpClient.newBuilder().build()
			val httpRequest = HttpRequest.newBuilder().GET()
				.uri(URI.create("https://stellaris.paradoxwikis.com/File:Research.png")).build()
			val bodyHandler = HttpResponse.BodyHandlers.ofLines()
			val httpResponse = httpClient.send(httpRequest, bodyHandler)
			println(httpResponse.uri())
			println(httpResponse.statusCode())
			val prefix = "<div class=\"fullImageLink\" id=\"file\"><a href=\""
			val prefixLength = prefix.length
			var isOk = false
			httpResponse.body().filter {
				if(isOk) return@filter false
				println("abc")
				val index = it.indexOf(prefix)
				if(index != -1) {
					val startIndex = index + prefixLength
					val endIndex = it.indexOf('"', startIndex + 1)
					if(endIndex != -1) {
						println(it.substring(startIndex, endIndex))
						isOk = true
						return@filter true
					}
				}
				return@filter false
			}.findFirst()
		})
	}
	
	@Test
	fun test2(){
		//https://stellaris.paradoxwikis.com/File:Research.png
		val httpClient = HttpClient.newBuilder().build()
		val httpRequest = HttpRequest.newBuilder().GET()
			.uri(URI.create("https://stellaris.paradoxwikis.com/File:Research.png")).build()
		val bodyHandler = HttpResponse.BodyHandlers.ofString()
		val httpResponse = httpClient.send(httpRequest,bodyHandler)
		println(httpResponse.uri())
		println(httpResponse.statusCode())
		println(httpResponse.body())
	}
	
	//404
	@Test
	fun test3(){
		//https://stellaris.paradoxwikis.com/File:Researc.png
		val httpClient = HttpClient.newBuilder().build()
		val httpRequest = HttpRequest.newBuilder().GET()
			.uri(URI.create("https://stellaris.paradoxwikis.com/File:Job_servant.png")).build()
		val bodyHandler = HttpResponse.BodyHandlers.ofString()
		val httpResponse = httpClient.send(httpRequest,bodyHandler)
		println(httpResponse.uri())
		println(httpResponse.statusCode())
		println(httpResponse.body())
	}
}
