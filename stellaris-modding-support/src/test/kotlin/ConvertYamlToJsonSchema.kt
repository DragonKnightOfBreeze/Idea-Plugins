@file:Suppress("UNCHECKED_CAST")

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.dataformat.yaml.*
import com.fasterxml.jackson.module.kotlin.*
import java.io.*

fun main() {
	convertSchemaFiles("stellaris-modding-support\\src\\test\\resources\\schema")
}

private val jsonMapper = ObjectMapper().apply {
	enable(SerializationFeature.INDENT_OUTPUT)
}
private val yamlMapper = YAMLMapper()

private val coreSchemaFile = File("stellaris-modding-support\\src\\test\\resources\\schema\\core.yml")
private val coreSchema = yamlMapper.readValue<Map<String, Any?>>(coreSchemaFile)
private val coreSchemaDefinitions = coreSchema["definitions"] as Map<String, Any?>
//private val ignoredSchemaFileNames = arrayOf("core.yml","type.yml")

private fun convertSchemaFiles(schemaPath: String) {
	File(schemaPath).walk().forEach {
		try {
			if(it.isFile && it.extension == "yml") {
				//if(it.name in ignoredSchemaFileNames) return@forEach
				val yaml = it.readText()
				if(yaml.isBlank()) return@forEach
				val data = readYamlSchema(yaml)
				val json = writeJsonSchema(data)
				File(it.path.replace("\\test", "\\main").replace(".yml", ".json")).writeText(json)
			}
		}catch(e:Exception){
			println(it.path)
			e.printStackTrace()
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
	val schemaDefinitions = schema["definitions"]
	if(schemaDefinitions != null) {
		schemaDefinitions as MutableMap<String, Any?>
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
	}
	return jsonMapper.writeValueAsString(schema).replace("""(\.\./)*core\.yml""".toRegex(), "")
}

private fun resolveCoreDefinitionNames(schema: Map<String, Any?>): List<String> {
	return coreSchemaDefinitionRegex.findAll(jsonMapper.writeValueAsString(schema)).map { it.groupValues[1] }.toList()
}

private fun resolveDefinitionNames(schema: Map<String, Any?>): List<String> {
	return schemaDefinitionRegex.findAll(jsonMapper.writeValueAsString(schema)).map { it.groupValues[1] }.toList()
}



