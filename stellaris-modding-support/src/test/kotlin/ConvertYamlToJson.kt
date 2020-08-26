import com.fasterxml.jackson.core.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.dataformat.yaml.*
import org.snakeyaml.engine.v2.api.*
import org.yaml.snakeyaml.*
import java.io.*

fun main() {
	val jsonMapper = ObjectMapper()
	jsonMapper.enable(SerializationFeature.INDENT_OUTPUT)

	val yamlMapper = YAMLMapper()

	val path = "D:\\My Documents\\My Projects\\Managed\\Idea-Plugins\\stellaris-modding-support\\src\\main\\resources\\yamlSchema"

	File(path).walk().forEach {
		runCatching {
			if(it.isFile && it.extension == "yaml") {
				//snakeYaml将yes/no识别为布尔值，因此这里需要特殊处理
				val yaml = it.readText().replace("""\b(yes|no)\b""".toRegex(),"\"$1\"")
				val data = yamlMapper.readValue(yaml, Any::class.java)
				val json = jsonMapper.writeValueAsString(data)
					.replace(".yaml",".json")
				File(it.path.replace("yamlSchema", "schema").replace(".yaml", ".json")).writeText(json)
			} else {
				it.copyTo(File(it.path.replace("yamlSchema", "schema")), true)
			}
		}
	}
}
