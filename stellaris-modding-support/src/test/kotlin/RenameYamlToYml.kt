import java.io.*

fun main() {
	rename("stellaris-modding-support/src/test/resources/schema")
}

private fun rename(path:String){
	File(path).walk().forEach{file ->
		if(file.isFile){
			file.renameTo(File(file.path.replace(".yaml",".yml")))
		}
	}
}
