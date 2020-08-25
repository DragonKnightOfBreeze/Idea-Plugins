import com.windea.plugin.idea.stellaris.*
import org.junit.*
import java.io.*
import java.nio.file.*

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
}
