package com.windea.plugin.idea.paradox

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.documentation.*
import com.intellij.codeInsight.lookup.*
import com.intellij.lang.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.project.*
import com.intellij.openapi.util.*
import com.intellij.openapi.vfs.*
import com.intellij.psi.*
import com.intellij.psi.search.*
import com.intellij.psi.util.*
import java.util.function.Function

val iconSize get() = DocumentationComponent.getQuickDocFontSize().size

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

/**将VirtualFile转化为指定类型的PsiFile。*/
inline fun <reified T : PsiFile> VirtualFile.toPsiFile(project: Project): T? {
	return PsiManager.getInstance(project).findFile(this) as? T
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

fun LookupElement.withPriority(priority: Double): LookupElement {
	return PrioritizedLookupElement.withPriority(this, priority)
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

//使用CachedValue以提高性能
//这个过程中避免使用匿名lambda，因为需要检查可相等性
 fun <F : PsiFile, T> getCachedValue(file: F, key: Key<CachedValue<T>>, block: Function<F, T>): T {
	return CachedValuesManager.getCachedValue(file, key) {
		CachedValueProvider.Result.create(block.apply(file), file)
	}
}

/**
 * 得到处理后的[VirtualFile]，以便查看它的子节点。
 *
 * 如果当前的[VirtualFile]是一个压缩文件，则进行特殊处理，否则返回自身。
 */
fun VirtualFile.optimized():VirtualFile{
	val extension = this.extension
	return when{
		extension == "jar" || extension == "zip" -> JarFileSystem.getInstance().getRootByLocal(this)?:this
		else -> this
	}
}