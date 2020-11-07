import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.dataformat.yaml.*
import java.io.*

val jsonMapper = ObjectMapper().apply{
	enable(SerializationFeature.INDENT_OUTPUT)
}

val yamlMapper = YAMLMapper()

fun main() {
	convertSchemaFiles("stellaris-modding-support\\src\\test\\resources\\schema")
}

private fun convertSchemaFiles(schemaPath: String) {
	File(schemaPath).walk().forEach {
		runCatching {
			if(it.isFile && it.extension == "yml") {
				val yaml = it.readText()
				val data = readYamlSchema(yaml)
				val json = writeYamlSchema(data)
				File(it.path.replace("\\test", "\\main").replace(".yml", ".json")).writeText(json)
			} else {
				it.copyTo(File(it.path.replace("\\test", "\\main")), true)
			}
		}
	}
}

@Suppress("UNCHECKED_CAST")
private fun readYamlSchema(yaml: String): Map<String, Any?> {
	//SnakeYaml将yes/no识别为布尔值，因此这里可能需要特殊处理
	return yamlMapper.readValue(yaml,Map::class.java) as Map<String, Any?>
}

private fun writeYamlSchema(data: Map<String, Any?>): String {
	//将$ref属性值中的".yml"替换为".json"
	//将$ref属性值从jsonPointer替换为对应的json值（因为jar中无法解析正确解析跨文件的$ref属性的值）
	return jsonMapper.writeValueAsString(data).replace(".yml", ".json")
}


