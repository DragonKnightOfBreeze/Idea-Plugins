@file:Suppress("UNUSED_PARAMETER")

package com.windea.plugin.idea.stellaris

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.lang.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.editor.colors.*
import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.project.*
import com.intellij.openapi.util.*
import com.intellij.openapi.vfs.*
import com.intellij.psi.*
import com.intellij.psi.search.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.psi.*
import java.io.*
import java.net.*
import java.util.*
import java.util.concurrent.*
import java.util.function.Function

//region Stdlib
inline fun writeString(block:StringWriter.()->Unit):String{
	return StringWriter().apply(block).toString()
}

fun Boolean.toInt() = if(this) 1 else 0

val workDirectory: File = File("").absoluteFile

private val classPathLocationClass = StellarisBundle.javaClass

private val defaultClassLoader = runCatching {
	classPathLocationClass.classLoader
}.recoverCatching {
	ClassLoader.getSystemClassLoader()
}.recoverCatching {
	ClassLoader.getPlatformClassLoader()
}.getOrThrow()

fun String.toClassPathResource(): URL? = defaultClassLoader.getResource(this)

@Suppress("UNCHECKED_CAST")
fun <T> Array<out T?>.cast() = this as Array<T>

inline fun <T, reified R> List<T>.mapArray(block: (T) -> R): Array<R> {
	return Array<R>(size) { block(this[it]) }
}

inline fun <T, reified R> Array<out T>.mapArray(block: (T) -> R): Array<R> {
	return Array<R>(size) { block(this[it]) }
}

inline fun <T, reified R> Sequence<T>.mapArray(block: (T) -> R): Array<R> {
	return this.toList().mapArray(block)
}

fun String.isBoolean() = this == "yes" || this == "no"

private val numberRegex = """-?[0-9]+(\.[0-9]+)?""".toRegex()
fun String.isNumber() = this.matches(numberRegex)

fun Boolean.toStringYesNo() = if(this) "yes" else "no"

fun String.containsBlank() = this.any { it.isWhitespace() }

fun String.containsBlankLine(): Boolean {
	var newLine = 0
	val chars = toCharArray()
	for(i in chars.indices) {
		val char = chars[i]
		if((char == '\r' && chars[i + 1] != '\n') || char == '\n') newLine++
		if(newLine >= 2) return true
	}
	forEach {
		if(it == '\r' || it == '\n') newLine++
	}
	return false
}

fun String.quote() = if(startsWith('"') && endsWith('"')) this else "\"$this\""

fun String.quoteIfNecessary() = if(this.containsBlank()) quote() else this

fun String.onlyQuoteIfNecessary() = this.unquote().quoteIfNecessary()

fun String.unquote() = if(length >= 2 && startsWith('"') && endsWith('"')) substring(1, length - 1) else this

fun String.truncate(limit: Int) = if(this.length <= limit) this else this.take(limit) + "..."

fun CharSequence.indicesOf(char: Char, ignoreCase: Boolean = false): MutableList<Int> {
	val indices = mutableListOf<Int>()
	var lastIndex = indexOf(char, 0, ignoreCase)
	while(lastIndex != -1) {
		indices += lastIndex
		lastIndex = indexOf(char, lastIndex + 1, ignoreCase)
	}
	return indices
}

inline fun <reified T> T.toSingletonArray(): Array<T> {
	return arrayOf(this)
}

inline fun <reified T> Sequence<T>.toArray(): Array<T> {
	return this.toList().toTypedArray()
}

fun <T> T.toSingletonList(): List<T> {
	return Collections.singletonList(this)
}

fun <T : Any> T?.toSingletonOrEmpty(): List<T> {
	return if(this == null) Collections.emptyList() else Collections.singletonList(this)
}

//为了提高性能而自定义的扩展

inline fun <T, R> Iterable<T>.flatMapNotNullFind(transform: (T) -> Iterable<R>?, predicate: (R) -> Boolean): R? {
	this.forEach {
		transform(it)?.find(predicate)?.let { r -> return r }
	}
	return null
}

inline fun <T, R> Iterable<T>.flatMapNotNullFilter(transform: (T) -> Iterable<R>?, predicate: (R) -> Boolean): List<R> {
	val result = mutableListOf<R>()
	this.forEach {
		transform(it)?.filterTo(result, predicate)
	}
	return result
}

inline fun <T, R> Iterable<T>.flatMapNotNull(transform: (T) -> Iterable<R>?): List<R> {
	val result = mutableListOf<R>()
	this.forEach {
		transform(it)?.let { r -> result.addAll(r) }
	}
	return result
}
//endregion

//region Misc
inline fun PsiElement.forEachChild(block: (PsiElement) -> Unit) {
	var child = this.firstChild
	while(child != null) {
		block(child)
		child = child.nextSibling
	}
}

inline fun <reified T : PsiElement> PsiElement.indexOfChild(element: T): Int {
	var child = firstChild
	var index = 0
	while(child != null) {
		when(child) {
			element -> return index
			is T -> index++
			else -> child = child.nextSibling
		}
	}
	return -1
}

/**得到当前AST节点的除了空白节点之外的所有子节点。*/
fun ASTNode.nodes(): List<ASTNode> {
	val result = mutableListOf<ASTNode>()
	var child = this.firstChildNode
	while(child != null) {
		if(child.elementType !== TokenType.WHITE_SPACE) result += child
		child = child.treeNext
	}
	return result
}

/**查找当前项目中指定语言文件类型和作用域的VirtualFile。*/
fun findVirtualFiles(project: Project, type: LanguageFileType): Collection<VirtualFile> {
	return FileTypeIndex.getFiles(type, GlobalSearchScope.projectScope(project))
}

/**查找当前项目中指定语言文件类型和作用域的PsiFile。*/
inline fun <reified T : PsiFile> findFiles(project: Project, type: LanguageFileType): List<T> {
	return FileTypeIndex.getFiles(type, GlobalSearchScope.projectScope(project)).mapNotNull {
		PsiManager.getInstance(project).findFile(it)
	}.filterIsInstance<T>()
}

/**递归得到当前VirtualFile的所有作为子节点的VirtualFile。*/
fun VirtualFile.getAllChildFiles(destination: MutableList<VirtualFile> = mutableListOf()): List<VirtualFile> {
	for(child in this.children) {
		if(child.isDirectory) child.getAllChildFiles(destination) else destination.add(child)
	}
	return destination
}

fun VirtualFile.isRootDirectory(): Boolean {
	return children.any {
		!it.isDirectory && it.name == descriptorModFileName || it.name == stellarisExeFileName
	}
}

/**将VirtualFile转化为指定类型的PsiFile。*/
inline fun <reified T : PsiFile> VirtualFile.toPsiFile(project: Project): T? {
	return PsiManager.getInstance(project).findFile(this) as? T
}

/**得到指定元素之前的所有直接的注释的文本，作为文档注释，跳过空白。*/
fun getDocCommentTextFromPreviousComment(element: PsiElement): String {
	//我们认为当前元素之前，之间没有空行的非行尾行注释，可以视为文档注释，但这并非文档注释的全部
	return buildString {
		var prevElement = element.prevSibling
		while(prevElement != null) {
			val text = prevElement.text
			if(prevElement !is PsiWhiteSpace) {
				if(!isPreviousComment(prevElement)) break
				if(length != 0) insert(0, "\n")
				insert(0, text.trimStart('#').trim())
			} else {
				if(text.containsBlankLine()) break
			}
			prevElement = prevElement.prevSibling
		}
	}
}

/**判断指定的注释是否可认为是之前的注释*/
fun isPreviousComment(element: PsiElement): Boolean {
	val elementType = element.elementType
	return elementType == StellarisLocalizationTypes.COMMENT || elementType == StellarisLocalizationTypes.ROOT_COMMENT
	       || elementType == StellarisScriptTypes.COMMENT
}

/**得到指定元素之前的所有直接的注释的Html文本，作为文档注释，跳过空白。*/
fun getDocCommentHtmlFromPreviousComment(element: PsiElement, textAttributeKey: TextAttributesKey): String {
	return buildString {
		//val attributes = EditorColorsManager.getInstance().globalScheme.getAttributes(textAttributeKey).clone()
		//val bgColor = attributes.backgroundColor
		//val color = attributes.foregroundColor
		//if(bgColor != null) append("<div bgcolor=#${GuiUtils.colorToHex(bgColor)}>")
		//if(color != null) append("<font color=#${GuiUtils.colorToHex(color)}>")
		val text = getDocCommentTextFromPreviousComment(element)
		val html = text.split("\n").joinToString("<br>") { it }
		append(html)
		//if(color != null) append("</font>")
		//if(bgColor != null) append("</div>")
	}
}

/**查找最远的相同类型的兄弟节点。可指定是否向后查找，以及是否在空行处中断。*/
fun findFurthestSiblingOfSameType(element: PsiElement, findAfter: Boolean, stopOnBlankLine: Boolean = true): PsiElement? {
	var node = element.node
	val expectedType = node.elementType
	var lastSeen = node
	while(node != null) {
		val elementType = node.elementType
		when {
			elementType == expectedType -> lastSeen = node
			elementType == TokenType.WHITE_SPACE -> {
				if(stopOnBlankLine && node.text.containsBlankLine()) break
			}
			else -> break
		}
		node = if(findAfter) node.treeNext else node.treePrev
	}
	return lastSeen.psi
}

fun LookupElement.withPriority(priority: Double): LookupElement = PrioritizedLookupElement.withPriority(this, priority)

/**导航到指定元素的位置*/
fun navigateToElement(editor: Editor, element: PsiElement?) {
	val offset = element?.textOffset ?: return
	editor.caretModel.moveToOffset(offset)
	editor.scrollingModel.scrollToCaret(ScrollType.MAKE_VISIBLE)
}

/**导航到指定元素的位置并且光标选择该元素*/
fun selectElement(editor: Editor, element: PsiElement?) {
	val range = element?.textRange ?: return
	editor.selectionModel.setSelection(range.startOffset, range.endOffset)
}

/**相对于游戏目录或模组目录的文件路径*/
val PsiElement.filePath: String
	get() {
		val file = this.containingFile?.virtualFile ?: anonymous
		return stellarisPathCache[file] ?: anonymous
	}
//endregion

//region Find Extensions
//TODO 使用stub提高性能 - 可能不好实现，存在需要部分匹配和过滤的情况

//仅作为标记，在值改为true时要清空缓存
internal var shouldRebuildScriptFileCache = true
internal val scriptFileCache = CopyOnWriteArrayList<StellarisScriptFile>()

//仅作为标记，在值改为true时要清空缓存
internal var shouldRebuildLocalizationFileCache = true
internal val localizationFileCache = CopyOnWriteArrayList<StellarisLocalizationFile>()

//当缓存为空时，也重新构建缓存，因为这是不期望的

@Synchronized
fun findScriptFiles(project: Project): List<StellarisScriptFile> {
	//如果需要清空缓存，则重建缓存，否则直接返回缓存
	if(shouldRebuildScriptFileCache || scriptFileCache.isEmpty()) {
			for(dir in rootDirectoryCache.values) {
				//在查找之前是否需要检查是否是游戏或模组目录？
				//if(!stellarisDirectory.isStellarisDirectory()) continue
				for(child in dir.getAllChildFiles()) {
					val file = child.toPsiFile<StellarisScriptFile>(project)
					if(file != null) scriptFileCache.add(file)
				}
			}
			shouldRebuildScriptFileCache = false
	}
	return scriptFileCache
}

fun findLocalizationFiles(project: Project): List<StellarisLocalizationFile> {
	//如果需要清空缓存，则重建缓存，否则直接返回缓存
	if(shouldRebuildLocalizationFileCache || localizationFileCache.isEmpty()) {
			for(dir in rootDirectoryCache.values) {
				//在查找之前是否需要检查是否是游戏或模组目录？
				//if(!stellarisDirectory.isStellarisDirectory()) continue
				for(child in dir.getAllChildFiles()) {
					val file = child.toPsiFile<StellarisLocalizationFile>(project)
					if(file != null) localizationFileCache.add(file)
				}
			}
			shouldRebuildLocalizationFileCache = false
	}
	return localizationFileCache
}


//使用CachedValue以提高性能
//这个过程中避免使用匿名lambda，因为需要检查可相等性
private fun <F : PsiFile, T> getCachedValue(file: F, key: Key<CachedValue<T>>, block: Function<F, T>): T {
	return CachedValuesManager.getCachedValue(file, key) {
		CachedValueProvider.Result.create(block.apply(file), file)
	}
}

private val scriptVariableCachedKey = Key<CachedValue<List<StellarisScriptVariable>>>("ScriptVariableCache")

fun findScriptVariableInFile(name: String, file: PsiFile): StellarisScriptVariable? {
	if(file !is StellarisScriptFile) return null
	return getCachedValue(file, scriptVariableCachedKey) { it.variables }.find { it.name == name }
}

fun findScriptVariable(name: String, project: Project): StellarisScriptVariable? {
	return findScriptFiles(project).flatMapNotNullFind({ file ->
		getCachedValue(file, scriptVariableCachedKey) { it.variables }
	}, { it.name == name })
}

fun findScriptVariables(name: String, project: Project): List<StellarisScriptVariable> {
	return findScriptFiles(project).flatMapNotNullFilter({ file ->
		getCachedValue(file, scriptVariableCachedKey) { it.variables }
	}, { it.name == name })
}

fun findScriptVariables(project: Project): List<StellarisScriptVariable> {
	return findScriptFiles(project).flatMapNotNull { file ->
		getCachedValue(file, scriptVariableCachedKey) { it.variables }
	}
}

private val scriptPropertyCachedKey = Key<CachedValue<List<StellarisScriptProperty>>>("ScriptPropertyCache")

fun findScriptPropertyInFile(name: String, file: PsiFile): StellarisScriptProperty? {
	if(file !is StellarisScriptFile) return null
	return getCachedValue(file, scriptPropertyCachedKey) { it.properties }.find { it.name == name }
}

fun findScriptProperty(name: String, project: Project): StellarisScriptProperty? {
	return findScriptFiles(project).flatMapNotNullFind({ file ->
		getCachedValue(file, scriptPropertyCachedKey) { it.properties }
	}, { it.name == name })
}

fun findScriptProperties(name: String, project: Project): List<StellarisScriptProperty> {
	return findScriptFiles(project).flatMapNotNullFilter({ file ->
		getCachedValue(file, scriptPropertyCachedKey) { it.properties }
	}, { it.name == name })
}

fun findScriptProperties(project: Project): List<StellarisScriptProperty> {
	return findScriptFiles(project).flatMapNotNull { file ->
		getCachedValue(file, scriptPropertyCachedKey) { it.properties }
	}
}

private val localizationPropertyCachedKey = Key<CachedValue<List<StellarisLocalizationProperty>>>("LocalizationPropertyCache")

fun findLocalizationPropertyInFile(name: String, file: PsiFile): StellarisLocalizationProperty? {
	if(file !is StellarisLocalizationFile) return null
	return getCachedValue(file, localizationPropertyCachedKey) { it.properties }.find { it.name == name }
}

fun findLocalizationProperty(name: String, project: Project, locale: StellarisLocale? = null): StellarisLocalizationProperty? {
	return findLocalizationFiles(project).flatMapNotNullFind({ file ->
		if(locale != null && locale != file.stellarisLocale) return@flatMapNotNullFind null
		getCachedValue(file, localizationPropertyCachedKey) { it.properties }
	}, { it.name == name })
}

fun findLocalizationProperties(name: String, project: Project, locale: StellarisLocale? = null): List<StellarisLocalizationProperty> {
	return findLocalizationFiles(project).flatMapNotNullFilter({ file ->
		if(locale != null && locale != file.stellarisLocale) return@flatMapNotNullFilter null
		getCachedValue(file, localizationPropertyCachedKey) { it.properties }
	}, { it.name == name })
}

fun findLocalizationProperties(project: Project, locale: StellarisLocale? = null): List<StellarisLocalizationProperty> {
	return findLocalizationFiles(project).flatMapNotNull { file ->
		if(locale != null && locale != file.stellarisLocale) return@flatMapNotNull null
		getCachedValue(file, localizationPropertyCachedKey) { it.properties }
	}
}

/**将推断的用户语言区域对应的localizationProperty放到列表的最前面。*/
fun List<StellarisLocalizationProperty>.pin(): List<StellarisLocalizationProperty> {
	val result = mutableListOf<StellarisLocalizationProperty>()
	var index = 0
	for(property in this) {
		if(property.stellarisLocale == inferedStellarisLocale) {
			result.add(index, property)
			index++
		} else {
			result.add(property)
		}
	}
	return result
}
//endregion
