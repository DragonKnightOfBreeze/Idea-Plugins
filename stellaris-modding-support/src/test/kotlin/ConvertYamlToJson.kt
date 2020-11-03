import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.dataformat.yaml.*
import java.io.*

fun main() {
	val jsonMapper = ObjectMapper()
	jsonMapper.enable(SerializationFeature.INDENT_OUTPUT)

	val yamlMapper = YAMLMapper()

	val yamlSchemaPath = "stellaris-modding-support\\src\\test\\resources\\schema"
	val jsonSchemaPath = "stellaris-modding-support\\src\\main\\resources\\schema"

	File(yamlSchemaPath).walk().forEach {
		runCatching {
			if(it.isFile && it.extension == "yaml") {
				//snakeYaml将yes/no识别为布尔值，因此这里需要特殊处理
				val yaml = it.readText().replace("""\b(yes|no)\b""".toRegex(),"\"$1\"")
				val data = yamlMapper.readValue(yaml, Any::class.java)
				val json = jsonMapper.writeValueAsString(data)
					.replace(".yaml",".json")
				File("$jsonSchemaPath//${it.nameWithoutExtension}.yaml").writeText(json)
			} else {
				it.copyTo(File(it.path.replace("yamlSchema", "jsonSchema")), true)
			}
		}
	}
}
