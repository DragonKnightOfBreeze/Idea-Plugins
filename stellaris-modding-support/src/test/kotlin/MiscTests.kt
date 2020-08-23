import com.windea.plugin.idea.stellaris.*
import org.junit.*

class MiscTests {
	@Test
	fun test1(){
		//file:/D:/My%20Documents/My%20Projects/Managed/Idea-Plugins/stellaris-modding-support/build/resources/main/example.yml
		println("example.yml".toClassPathResource())
		//file:/D:/My%20Documents/My%20Projects/Managed/Idea-Plugins/stellaris-modding-support/build/resources/main/example.yml
		println("example.yml".toClassPathResource()?.toURI())
	}
}
