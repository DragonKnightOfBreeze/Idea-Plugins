@file:Suppress("UNCHECKED_CAST", "UNUSED_PARAMETER")

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
import com.intellij.psi.util.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptParserDefinition.Companion.COMMENTS
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*
import com.windea.plugin.idea.stellaris.script.psi.impl.*
import java.net.*
import javax.swing.*

//region Logging
const val doPrint = true

fun <T> T.andPrint(name: String? = null): T {
	if(doPrint){
		println(name?.let { "$it: " }.orEmpty() + toStringSmartly())
	}
	return this
}
//endregion

//region Stdlib
fun Any?.toStringSmartly() = if(this is Array<*>) this.contentDeepToString() else this.toString()

fun Boolean.toInt() = if(this) 1 else 0

inline fun <reified T : Any> String.toClassPathResource(): URL = T::class.java.getResource(this)


fun <T> Array<out T?>.cast() = this as Array<T>

inline fun <T, reified R> List<T>.mapArray(block: (T) -> R): Array<R> {
	return Array<R>(size) { block(this[it]) }
}

inline fun <T, reified R> Array<out T>.mapArray(block: (T) -> R): Array<R> {
	return Array<R>(size) { block(this[it]) }
}


fun String.quote() = if(startsWith('"') && endsWith('"')) this else "\"$this\""

fun String.quoteIfNecessary() = if(contains("\\s".toRegex())) quote() else this

fun String.unquote() = if(startsWith('"') && endsWith('"')) substring(1, length - 1) else this


fun CharSequence.indicesOf(char: Char, ignoreCase: Boolean = false): MutableList<Int> {
	val indices = mutableListOf<Int>()
	var lastIndex = indexOf(char, 0, ignoreCase)
	while(lastIndex != -1) {
		indices += lastIndex
		lastIndex = indexOf(char, lastIndex + 1, ignoreCase)
	}
	return indices
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

fun Project.findPsiFiles(type: LanguageFileType,globalSearchScope: GlobalSearchScope = GlobalSearchScope.projectScope(this)): List<PsiFile> {
	return FileTypeIndex.getFiles(type, globalSearchScope).mapNotNull {
		PsiManager.getInstance(this).findFile(it)
	}
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
	return buildString {
		var prevElement = element.prevSibling
		while(prevElement != null) {
			if(prevElement !is PsiWhiteSpace) {
				if(!isPreviousComment(prevElement)) break
				if(length != 0) insert(0, "\n")
				insert(0, prevElement.text)
			}
			prevElement = prevElement.prevSibling
		}
	}
}

fun isPreviousComment(element: PsiElement): Boolean {
	return element.elementType == StellarisLocalizationTypes.COMMENT || element.elementType == StellarisScriptTypes.COMMENT
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
		val html = text.split("\n").joinToString("<br>") { it.trimStart('#').trim() }
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


/**创建查找元素。用于代码补全。*/
fun createLookupElement(keyword: String, icon: Icon? = null, tailText: String? = null, typeText: String? = null): LookupElementBuilder {
	var result = LookupElementBuilder.create(keyword)
	if(icon != null) result = result.withIcon(icon)
	if(tailText != null) result = result.withTailText(tailText)
	if(typeText != null) result = result.withTypeText(typeText)
	return result
}

/**创建关键字查找元素。用于代码补全。*/
fun createKeywordLookupElement(keyword: String, insertHandler: InsertHandler<LookupElement>? = null): LookupElement {
	return PrioritizedLookupElement.withPriority(LookupElementBuilder.create(keyword).bold().withInsertHandler(insertHandler), 70.0)
}

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

//region Stellaris Script
fun findScriptVariableDefinitionInFile(name: String?, psiFile: PsiFile?): StellarisScriptVariableDefinition? {
	if(name == null || psiFile !is StellarisScriptFile) return null
	return psiFile.variableDefinitions.first { it.name == name }
}

fun findScriptVariableDefinitionInProject(name: String?, project: Project): StellarisScriptVariableDefinition? {
	if(name == null) return null
	val files = project.findPsiFiles(StellarisScriptFileType) as List<StellarisScriptFile>
	return files.flatMap { it.variableDefinitions.toList() }.first { it.name == name }
}

fun findScriptVariableDefinitionsInProject(name: String?, project: Project): List<StellarisScriptVariableDefinition>? {
	if(name == null) return null
	val files = project.findPsiFiles(StellarisScriptFileType) as List<StellarisScriptFile>
	return files.flatMap { it.variableDefinitions.toList() }.filter { it.name == name }
}

fun findScriptVariableDefinitions(project: Project, key: String): List<StellarisScriptVariableDefinition> {
	val result = mutableListOf<StellarisScriptVariableDefinition>()
	val virtualFiles = FileTypeIndex.getFiles(StellarisScriptFileType, GlobalSearchScope.projectScope(project))
	for(virtualFile in virtualFiles) {
		val file = PsiManager.getInstance(project).findFile(virtualFile) ?: continue
		val variables0 = PsiTreeUtil.getChildrenOfType(file, StellarisScriptVariableDefinition::class.java)
		val variables = variables0?.cast() ?: continue
		for(variable in variables) {
			if(key == StellarisScriptPsiImplUtil.getName(variable)) result += variable
		}
	}
	return result
}

fun findAllScriptVariableDefinitions(project: Project): List<StellarisScriptVariableDefinition> {
	val result = mutableListOf<StellarisScriptVariableDefinition>()
	val virtualFiles = FileTypeIndex.getFiles(StellarisScriptFileType, GlobalSearchScope.projectScope(project))
	for(virtualFile in virtualFiles) {
		val file = PsiManager.getInstance(project).findFile(virtualFile) ?: continue
		val properties = PsiTreeUtil.getChildrenOfType(file, StellarisScriptVariableDefinition::class.java)?.cast() ?: continue
		result += properties
	}
	return result
}


fun findScriptPropertyInProject(name: String?, project: Project): StellarisScriptProperty? {
	if(name == null) return null
	val files = project.findPsiFiles(StellarisScriptFileType) as List<StellarisScriptFile>
	return files.flatMap { it.properties.toList() }.first { it.name == name }
}

fun findScriptPropertiesInProject(name: String?, project: Project): List<StellarisScriptProperty>? {
	if(name == null) return null
	val files = project.findPsiFiles(StellarisScriptFileType) as List<StellarisScriptFile>
	return files.flatMap { it.properties.toList() }.filter { it.name == name }
}

fun findScriptProperties(project: Project, key: String): List<StellarisScriptProperty> {
	val result = mutableListOf<StellarisScriptProperty>()
	val virtualFiles = FileTypeIndex.getFiles(StellarisScriptFileType, GlobalSearchScope.projectScope(project))
	for(virtualFile in virtualFiles) {
		val file = PsiManager.getInstance(project).findFile(virtualFile) ?: continue
		val properties = PsiTreeUtil.getChildrenOfType(file, StellarisScriptProperty::class.java)?.cast() ?: continue
		for(property in properties) {
			if(key == StellarisScriptPsiImplUtil.getName(property)) result += property
		}
	}
	return result
}

fun findAllScriptProperties(project: Project): List<StellarisScriptProperty> {
	val result = mutableListOf<StellarisScriptProperty>()
	val virtualFiles = FileTypeIndex.getFiles(StellarisScriptFileType, GlobalSearchScope.projectScope(project))
	for(virtualFile in virtualFiles) {
		val file = PsiManager.getInstance(project).findFile(virtualFile) ?: continue
		val properties = PsiTreeUtil.getChildrenOfType(file, StellarisScriptProperty::class.java)?.cast() ?: continue
		result += properties
	}
	return result
}
//endregion

//region Stellaris Localization
fun findLocalizationPropertyInFile(name: String, psiFile: PsiFile): StellarisLocalizationProperty? {
	if(psiFile !is StellarisLocalizationFile) return null
	return psiFile.properties.find { it.name == name }
}

fun findLocalizationPropertiesInFile(name: String, psiFile: PsiFile): List<StellarisLocalizationProperty> {
	if(psiFile !is StellarisLocalizationFile) return listOf()
	return psiFile.properties.filter { it.name == name }
}

fun findLocalizationPropertyInProject(name: String, project: Project): StellarisLocalizationProperty? {
	val files = project.findPsiFiles(StellarisLocalizationFileType) as List<StellarisLocalizationFile>
	return files.flatMap { it.properties.toList() }.firstOrNull { it.name == name }
}

fun findLocalizationPropertiesInProject(name: String, project: Project): List<StellarisLocalizationProperty> {
	val files = project.findPsiFiles(StellarisLocalizationFileType) as List<StellarisLocalizationFile>
	return files.flatMap { it.properties.toList() }.filter { it.name == name }
}

fun findLocalizationPropertyResolvesInFile(name: String, file: PsiFile): MutableList<PsiElement> {
	val result = mutableListOf<PsiElement>()
	file.acceptChildren(object : PsiRecursiveElementWalkingVisitor() {
		override fun visitElement(element: PsiElement) {
			when(element) {
				is StellarisLocalizationProperty -> {
					if(element.name == name) result += element
					super.visitElement(element)
				}
				is StellarisLocalizationPropertyValue -> super.visitElement(element)
				is StellarisLocalizationRichText -> super.visitElement(element)
				is StellarisLocalizationColorfulText -> super.visitElement(element)
				is StellarisLocalizationPropertyReference -> {
					if(element.name == name) result += element
				}
			}
		}
	})
	return result
}

fun findLocalizationPropertyResolvesInProject(name: String, project: Project): List<PsiElement> {
	val files = project.findPsiFiles(StellarisLocalizationFileType) as List<StellarisLocalizationFile>
	val result = files.flatMap { findLocalizationPropertyResolvesInFile(name, it) }
	return result
}

fun findLocalizationPropertyVariantsInProject(project: Project): List<StellarisLocalizationProperty> {
	val files = project.findPsiFiles(StellarisLocalizationFileType) as List<StellarisLocalizationFile>
	return files.flatMap { it.properties.toList()}.filterNot { it.name.isNullOrEmpty() }
}
//endregion
