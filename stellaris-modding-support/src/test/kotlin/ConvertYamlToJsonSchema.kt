@file:Suppress("UNCHECKED_CAST")

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.dataformat.yaml.*
import com.fasterxml.jackson.module.kotlin.*
import java.io.*

val jsonMapper = ObjectMapper().apply {
	enable(SerializationFeature.INDENT_OUTPUT)
}
val yamlMapper = YAMLMapper()

val coreSchemaFile = File("stellaris-modding-support\\src\\test\\resources\\schema\\core.yml")
val coreSchema = yamlMapper.readValue<Map<String, Any?>>(coreSchemaFile)
val coreSchemaDefinitions = coreSchema["definitions"] as Map<String, Any?>

fun main() {
	convertSchemaFiles("stellaris-modding-support\\src\\test\\resources\\schema")
}

private fun convertSchemaFiles(schemaPath: String) {
	File(schemaPath).walk().forEach {
		runCatching {
			if(it.isFile && it.extension == "yml") {
				if(it.name == "core.yml") return@forEach
				val yaml = it.readText()
				val data = readYamlSchema(yaml)
				val json = writeJsonSchema(data)
				File(it.path.replace("\\test", "\\main").replace(".yml", ".json")).writeText(json)
			} else {
				it.copyTo(File(it.path.replace("\\test", "\\main")), true)
			}
		}
	}
}

private fun readYamlSchema(schemaString: String): Map<String, Any?> {
	//SnakeYaml将yes/no识别为布尔值，因此这里可能需要特殊处理
	return yamlMapper.readValue<Map<String, Any?>>(schemaString)
}

private val coreSchemaDefinitionRegex = """/core\.yml#/definitions/(\w+)""".toRegex()
private val schemaDefinitionRegex = """#/definitions/(\w+)""".toRegex()

private fun writeJsonSchema(schema: Map<String, Any?>): String {
	val schemaDefinitions = schema["definitions"] as MutableMap<String, Any?>
	//内联跨文件到coreSchema的$ref属性，将对应的值加入到当前schema的definitions下（因为jar中无法正确解析跨文件的$ref属性的值）
	val coreSchemaDefintionNames = resolveCoreDefinitionNames(schema)
	val addedCoreSchemaDefinitions = coreSchemaDefinitions.filterKeys { it in coreSchemaDefintionNames }
	schemaDefinitions.putAll(addedCoreSchemaDefinitions)
	//如果coreSchemaDefinitions中还有级联的definitions，需要递归解析并加入
	var extraSchemaNames = resolveDefinitionNames(addedCoreSchemaDefinitions) - schemaDefinitions.keys
	while(extraSchemaNames.isNotEmpty()) {
		val extraSchemaDefinitions = coreSchemaDefinitions.filterKeys { it in extraSchemaNames }
		schemaDefinitions.putAll(extraSchemaDefinitions)
		extraSchemaNames = resolveDefinitionNames(extraSchemaDefinitions) - schemaDefinitions.keys
	}
	return jsonMapper.writeValueAsString(schema).replace("../core.yml", "")
}

private fun resolveCoreDefinitionNames(schema: Map<String, Any?>): List<String> {
	return coreSchemaDefinitionRegex.findAll(jsonMapper.writeValueAsString(schema)).map { it.groupValues[1] }.toList()
}

private fun resolveDefinitionNames(schema: Map<String, Any?>): List<String> {
	return schemaDefinitionRegex.findAll(jsonMapper.writeValueAsString(schema)).map { it.groupValues[1] }.toList()
}



