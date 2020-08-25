import com.windea.plugin.idea.stellaris.*
import org.junit.*
import java.io.*
import java.nio.file.*
import java.util.jar.*

class MiscTests {
	@Test
	fun test1(){
		//file:/D:/My%20Documents/My%20Projects/Managed/Idea-Plugins/stellaris-modding-support/build/resources/main/example.yml
		println("example.yml".toClassPathResource())
		//file:/D:/My%20Documents/My%20Projects/Managed/Idea-Plugins/stellaris-modding-support/build/resources/main/example.yml
		println("example.yml".toClassPathResource()?.toURI())

		val path = "file:\\D:\\My Documents\\My Projects\\Managed\\Idea-Plugins\\stellaris-modding-support\\build\\idea-sandbox\\plugins\\Stellaris Modding Support\\lib\\stellaris-modding-support-2.4.jar!\\schema"

		val file = File(path)
		println(file.list())
	}

	@Test
	fun jarTest(){
		//从jar中读取文件：
		//jarFile.entries()

		val path = "D:\\My Documents\\My Projects\\Managed\\Idea-Plugins\\stellaris-modding-support\\build\\idea-sandbox\\plugins\\Stellaris Modding Support\\lib\\stellaris-modding-support-2.4.jar"
		val jarFile = JarFile(File(path))
		val schemaEntry = jarFile.getJarEntry("schema")
		val entiries = jarFile.entries()
		jarFile.stream().forEach {
			println(it.name)
		}
	}
}
