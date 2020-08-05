@file:Suppress("UNUSED_PARAMETER")

package com.windea.plugin.idea.stellaris

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.lang.*
import com.intellij.lang.documentation.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.editor.colors.*
import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.intellij.psi.search.*
import com.intellij.psi.search.GlobalSearchScope.*
import com.intellij.psi.util.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptParserDefinition.Companion.COMMENTS
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*
import java.net.*
import java.util.*

//region Logging
//const val doPrint = true
//
//fun <T> T.andPrint(name: String? = null): T {
//	if(doPrint){
//		println(name?.let { "$it: " }.orEmpty() + toStringSmartly())
//	}
//	return this
//}
//
//private fun Any?.toStringSmartly() = if(this is Array<*>) this.contentDeepToString() else this.toString()
//endregion

//region Stdlib
fun Boolean.toInt() = if(this) 1 else 0

inline fun <reified T : Any> String.toClassPathResource(): URL = T::class.java.getResource(this)


@Suppress("UNCHECKED_CAST")
fun <T> Array<out T?>.cast() = this as Array<T>

inline fun <T, reified R> List<T>.mapArray(block: (T) -> R): Array< R> {
	return Array<R>(size) { block(this[it]) }
}

inline fun <T, reified R> Array<out T>.mapArray(block: (T) -> R): Array< R> {
	return Array<R>(size) { block(this[it]) }
}

inline fun <T, reified R> Sequence<T>.mapArray(block: (T) -> R): Array< R> {
	return this.toList().mapArray(block)
}


fun String.quote() = if(startsWith('"') && endsWith('"')) this else "\"$this\""

fun String.quoteIfNecessary() = if(contains("\\s".toRegex())) quote() else this

fun String.unquote() = if(startsWith('"') && endsWith('"')) substring(1, length - 1) else this


fun String.truncate(limit:Int) = if(this.length <= limit) this else this.take(limit) + "..."


fun CharSequence.indicesOf(char: Char, ignoreCase: Boolean = false): MutableList<Int> {
	val indices = mutableListOf<Int>()
	var lastIndex = indexOf(char, 0, ignoreCase)
	while(lastIndex != -1) {
		indices += lastIndex
		lastIndex = indexOf(char, lastIndex + 1, ignoreCase)
	}
	return indices
}

inline fun <reified T> T.toSingletonArray():Array<T>{
	return arrayOf(this)
}

inline fun <reified T> Sequence<T>.toArray():Array<T>{
	return this.toList().toTypedArray()
}

fun <T> T.toSingletonList():List<T>{
	return Collections.singletonList(this)
}

fun <T:Any> T?.toSingletonOrEmpty(): MutableCollection<T> {
	return if(this == null) Collections.emptySet() else Collections.singleton(this)
}
//endregion

//region Psi
/**得到所有非空白节点的子节点*/
fun ASTNode.nodes(): List<ASTNode> {
	val result = mutableListOf<ASTNode>()
	var child = this.firstChildNode
	while(child != null) {
		if(child.elementType !== TokenType.WHITE_SPACE) {
			result += child
		}
		child = child.treeNext
	}
	return result
}

@Suppress("UNCHECKED_CAST")
fun <T:PsiFile> Project.findFiles(type: LanguageFileType,globalSearchScope: GlobalSearchScope = projectScope(this)): Sequence<T> {
	return FileTypeIndex.getFiles(type, globalSearchScope).asSequence().mapNotNull {
		PsiManager.getInstance(this).findFile(it)
	} as Sequence<T>
}
//endregion

//region Generic
fun String.toDefinitionText(): String {
	return buildString {
		append(DocumentationMarkup.DEFINITION_START)
		append(this@toDefinitionText) //我草！！！
		append(DocumentationMarkup.DEFINITION_END)
	}
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
			} else{
				if(containsBlankLine(text)) break
			}
			prevElement = prevElement.prevSibling
		}
	}
}

fun isPreviousComment(element: PsiElement): Boolean {
	return element.elementType == StellarisLocalizationTypes.COMMENT || element.elementType == StellarisScriptTypes.COMMENT
}

fun containsBlankLine(text:String):Boolean{
	var newLine = 0
	text.forEach {
		if(it == '\r' || it == '\n') newLine ++
		if(newLine >= 2) return true
	}
	return false
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


/**查找最远的相同类型的兄弟节点。*/
fun findFurthestSiblingOfSameType(element: PsiElement, after: Boolean): PsiElement? {
	var node = element.node
	val expectedType = node.elementType
	var lastSeen = node
	loop@ while(node != null) {
		val elementType = node.elementType
		when {
			elementType === expectedType -> lastSeen = node
			elementType === TokenType.WHITE_SPACE -> if(expectedType === COMMENT && node.text.indexOf(10.toChar(), 1) != -1) break@loop
			!COMMENTS.contains(elementType) || COMMENTS.contains(expectedType) -> break@loop
		}
		node = if(after) node.treeNext else node.treePrev
	}
	return lastSeen.psi
}


fun LookupElement.withPriority(priority: Double): LookupElement = PrioritizedLookupElement.withPriority(this,priority)


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
//endregion

//NOTE 如果最终只需要一个结果而需要在多个文件中查找，那么使用Sequence而非List以提高性能

//region Stellaris Script
fun findScriptVariableDefinitionInFile(name: String, psiFile: PsiFile?): StellarisScriptVariableDefinition? {
	if( psiFile !is StellarisScriptFile) return null
	return psiFile.variableDefinitions.find { it.name == name }
}

fun findScriptVariableDefinition(name: String, project: Project, globalSearchScope: GlobalSearchScope=GlobalSearchScope.projectScope(project)): StellarisScriptVariableDefinition? {
	val files = project.findFiles<StellarisScriptFile>(StellarisScriptFileType,globalSearchScope)
	return files.flatMap { it.variableDefinitions.asSequence() }.find { it.name == name }
}

fun findScriptVariableDefinitions(name: String, project: Project, globalSearchScope: GlobalSearchScope=GlobalSearchScope.projectScope(project)): Sequence< StellarisScriptVariableDefinition> {
	val files = project.findFiles<StellarisScriptFile>(StellarisScriptFileType,globalSearchScope)
	return files.flatMap { it.variableDefinitions.asSequence() }.filter { it.name == name }
}

fun findAllScriptVariableDefinitions(project: Project, globalSearchScope: GlobalSearchScope=GlobalSearchScope.projectScope(project)): Sequence< StellarisScriptVariableDefinition> {
	val files = project.findFiles<StellarisScriptFile>(StellarisScriptFileType,globalSearchScope)
	return files.flatMap { it.variableDefinitions.asSequence() }.filterNot { it.name.isNullOrEmpty() }
}


fun findScriptPropertyInFile(name: String,  file: PsiFile): StellarisScriptProperty? {
	if(file !is StellarisScriptFile) return null
	return file.properties.find { it.name == name }
}

fun findScriptProperty(name: String, project: Project, globalSearchScope: GlobalSearchScope=GlobalSearchScope.projectScope(project)): StellarisScriptProperty? {
	val files = project.findFiles<StellarisScriptFile>(StellarisScriptFileType,globalSearchScope)
	return files.flatMap { it.properties.asSequence() }.find { it.name == name }
}

fun findScriptProperties(name: String, project: Project, globalSearchScope: GlobalSearchScope=GlobalSearchScope.projectScope(project)): Sequence<StellarisScriptProperty> {
	val files = project.findFiles<StellarisScriptFile>(StellarisScriptFileType,globalSearchScope)
	return files.flatMap { it.properties.asSequence() }.filter { it.name == name }
}

fun findScriptProperties(project: Project, globalSearchScope: GlobalSearchScope=GlobalSearchScope.projectScope(project)): Sequence<StellarisScriptProperty> {
	val files = project.findFiles<StellarisScriptFile>(StellarisScriptFileType,globalSearchScope)
	return files.flatMap { it.properties.asSequence() }.filterNot { it.name.isNullOrEmpty() }
}
//endregion

//region Stellaris Localization
fun findLocalizationPropertyInFile(name: String, file: PsiFile): StellarisLocalizationProperty? {
	if(file !is StellarisLocalizationFile) return null
	return file.properties.find { it.name == name }
}

fun findLocalizationProperty(name: String, project: Project, globalSearchScope: GlobalSearchScope=GlobalSearchScope.projectScope(project)): StellarisLocalizationProperty? {
	val files = project.findFiles<StellarisLocalizationFile>(StellarisLocalizationFileType,globalSearchScope)
	return files.flatMap { it.properties.asSequence() }.find { it.name == name }
}

fun findLocalizationProperties(name: String, project: Project, globalSearchScope: GlobalSearchScope=GlobalSearchScope.projectScope(project)): Sequence<StellarisLocalizationProperty> {
	val files = project.findFiles<StellarisLocalizationFile>(StellarisLocalizationFileType,globalSearchScope)
	return files.flatMap { it.properties.asSequence() }.filter { it.name == name }
}

fun findLocalizationProperties(project:Project, globalSearchScope: GlobalSearchScope=GlobalSearchScope.projectScope(project)): Sequence<StellarisLocalizationProperty> {
	val files = project.findFiles<StellarisLocalizationFile>(StellarisLocalizationFileType,globalSearchScope)
	return files.flatMap { it.properties.asSequence() }.filterNot { it.name.isNullOrEmpty() }
}
//endregion
