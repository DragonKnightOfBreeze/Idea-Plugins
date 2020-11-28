
import com.intellij.util.io.*
import java.io.*
import java.util.concurrent.*
import java.util.concurrent.atomic.*
import java.util.zip.*
import kotlin.system.*

fun main() {
	val rootPath = "D:\\Programs\\Steam\\steamapps\\workshop\\content\\281990"
	packageMods(rootPath)
}

private fun packageMods(rootPath:String,asyncCount:Int=16){
	//得到mod目录列表
	val dirs = File(rootPath).listFiles()?:return
	//并发打包mod
	val executor = Executors.newWorkStealingPool(asyncCount)
	val total = dirs.size
	val done = AtomicInteger(0)
	val countDownLatch = CountDownLatch(total)
	println("packaging mods ... (total: $total)")
	for(dir in dirs) {
		executor.execute {
			println("packaging mod '${dir.path}' ... (total: $total, done: $done)")
			val time = measureTimeMillis {
				packageMod(dir)
			}.let{ "${it/1000}s"}
			countDownLatch.countDown()
			done.incrementAndGet()
			println("packaging mod '${dir.path}' finished. (total: $total, done: $done, cost: $time)")
		}
	}
	//等待所有的mod打包完毕（可能需要很长时间）
	countDownLatch.await()
	println("packaging mods finished. (total: $total)")
}

private fun packageMod(dir: File,pkgName:String = "mod.zip") {
	if(dir.isDirectory) {
		val children = dir.listFiles() ?: return
		//删除已存在的压缩包，跳过本身已打包的mod目录
		for(child in children) {
			if(child.name == pkgName) {
				child.delete()
			} else if(child.extension == "zip") {
				return
			}
		}
		//打包mod目录中的所有文件和目录，除了zip文件，到mod目录中的mod.zip压缩包
		val zip = dir.resolve(pkgName)
		val fos = FileOutputStream(zip)
		val zos = ZipOutputStream(fos)
		for(child in children) {
			if(child.extension != "zip") {
				ZipUtil.addFileOrDirRecursively(zos, null, child, child.name, null, null)
			}
		}
		zos.close()
	}
}
