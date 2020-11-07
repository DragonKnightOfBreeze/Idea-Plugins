import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.dataformat.yaml.*
import java.io.*

fun main() {
	val jsonMapper = ObjectMapper()
	jsonMapper.enable(SerializationFeature.INDENT_OUTPUT)

	val yamlMapper = YAMLMapper()

	convertSchemaFiles("stellaris-modding-support\\src\\test\\resources\\schema", yamlMapper, jsonMapper)
}

private fun convertSchemaFiles(schemaPath: String, yamlMapper: YAMLMapper, jsonMapper: ObjectMapper) {
	File(schemaPath).walk().forEach {
		runCatching {
			if(it.isFile && it.extension == "yml") {
				//SnakeYaml将yes/no识别为布尔值，因此这里可能需要特殊处理
				val yaml = it.readText()
				//val yaml = it.readText().replace("""\b(yes|no)\b""".toRegex(), "\"$1\"")
				val data = yamlMapper.readValue(yaml, Any::class.java)
				val json = jsonMapper.writeValueAsString(data).replace(".yml", ".json")
				File(it.path.replace("\\test", "\\main").replace(".yml", ".json")).writeText(json)
			} else {
				it.copyTo(File(it.path.replace("\\test", "\\main")), true)
			}
		}
	}
}
