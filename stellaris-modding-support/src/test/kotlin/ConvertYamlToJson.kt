import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.dataformat.yaml.*
import java.io.*

fun main() {
	val jsonMapper = ObjectMapper()
	jsonMapper.enable(SerializationFeature.INDENT_OUTPUT)

	val yamlMapper = YAMLMapper()

	val path = "D:\\My Documents\\My Projects\\Managed\\Idea-Plugins\\stellaris-modding-support\\src\\main\\resources\\yamlSchema"

	File(path).walk().forEach {
		runCatching {
			if(it.isFile && it.extension == "yaml") {
				val yaml = it.readText()
				val json = jsonMapper.writeValueAsString(yamlMapper.readValue(yaml, Any::class.java))
					.replace(".yaml",".json")
				File(it.path.replace("yamlSchema", "schema").replace(".yaml", ".json")).writeText(json)
			} else {
				it.copyTo(File(it.path.replace("yamlSchema", "schema")), true)
			}
		}
	}
}
