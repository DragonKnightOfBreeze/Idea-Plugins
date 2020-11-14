package test

import com.windea.plugin.idea.stellaris.*
import org.junit.*
import java.util.concurrent.*
import java.util.stream.*
import kotlin.system.*

class PerformanceTest {
	//1>>2>3<<4<5
	//102172900
	//3166000
	//268700
	//38566100
	//40598000
	@Test
	fun flatMapFilterToArrayTest(){
		val list = (0..10000).toList().chunked(100)
		val executor = Executors.newCachedThreadPool()
		val future1 = executor.submit(Callable {
			measureNanoTime {
				val r = list.asSequence().flatMap { it }.filter { it / 3 == 0 }.toArray()
				println(r.size)
			}
		})
		val future2 = executor.submit(Callable {
			measureNanoTime {
				val r = list.flatMap { it }.filter { it / 3 == 0 }.toTypedArray()
				println(r.size)
			}
		})
		val future3 = executor.submit(Callable {
			measureNanoTime {
				val r = list.flatMapNotNullFilter({it},{it/3 == 0}).toTypedArray()
				println(r.size)
			}
		})
		val future4 = executor.submit(Callable {
			measureNanoTime {
				val r = list.flatMapFilterByStream({it},{it/3 == 0}).toTypedArray()
				println(r.size)
			}
		})
		val future5 = executor.submit(Callable {
			measureNanoTime {
				val r = list.flatMapFilterByPStream({it},{it/3 == 0}).toTypedArray()
				println(r.size)
			}
		})
		val result1 = future1.get()
		val result2 = future2.get()
		val result3 = future3.get()
		val result4 = future4.get()
		val result5 = future5.get()
		println(result1)
		println(result2)
		println(result3)
		println(result4)
		println(result5)
	}
	
	fun <T,R> List<T>.flatMapFilterByStream(transform:(T)->List<R>,predicate: (R) -> Boolean):List<R>{
		return this.stream().flatMap{ transform(it).stream() }.filter(predicate).collect(Collectors.toList())
	}
	
	fun <T,R> List<T>.flatMapFilterByPStream(transform:(T)->List<R>,predicate: (R) -> Boolean):List<R>{
		return this.parallelStream().flatMap{ transform(it).stream() }.filter(predicate).collect(Collectors.toList())
	}
}
