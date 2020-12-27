package com.windea.plugin.idea.paradox

import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.intellij.psi.*
import com.intellij.psi.search.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.paradox.localisation.psi.*
import com.windea.plugin.idea.paradox.model.*
import com.windea.plugin.idea.paradox.script.psi.*
import com.windea.plugin.idea.paradox.util.*
import org.jetbrains.annotations.*

fun iconTag(url: String, size: Int = iconSize): String {
	return "<img src='$url' width='$size' height='$size'/>"
}

/**得到指定元素之前的所有直接的注释的文本，作为文档注释，跳过空白。*/
fun getDocCommentTextFromPreviousComment(element: PsiElement): String {
	//我们认为当前元素之前，之间没有空行的非行尾行注释，可以视为文档注释，但这并非文档注释的全部
	val lines = mutableListOf<String>()
	var prevElement = element.prevSibling ?: element.parent?.prevSibling
	while(prevElement != null) {
		val text = prevElement.text
		if(prevElement !is PsiWhiteSpace) {
			if(!isPreviousComment(prevElement)) break
			lines.add(0, text.trimStart('#').trim())
		} else {
			if(text.containsBlankLine()) break
		}
		// 兼容comment在rootBlock之外的特殊情况
		prevElement = prevElement.prevSibling
	}
	return lines.joinToString("<br>")
}

/**判断指定的注释是否可认为是之前的注释。*/
fun isPreviousComment(element: PsiElement): Boolean {
	val elementType = element.elementType
	return elementType == ParadoxLocalisationTypes.COMMENT || elementType == ParadoxScriptTypes.COMMENT
}

/**是否是顶级的脚本属性。*/
fun ParadoxScriptProperty.isRootProperty(): Boolean {
	return this.parent is ParadoxScriptRootBlock && this.paradoxRootType != null
}

/**
 * 是否是非法的属性名。
 */
fun String.isInvalidPropertyName(): Boolean {
	return this.containsBlank() || this.isNumber() || this == "null"
}

val VirtualFile.paradoxFileType: ParadoxFileType? get() = this.getUserData(paradoxFileTypeKey)

val VirtualFile.paradoxRootType: ParadoxRootType? get() = this.getUserData(paradoxRootTypeKey)

val VirtualFile.paradoxPath: ParadoxPath? get() = this.getUserData(paradoxPathKey)

val PsiElement.paradoxFileType: ParadoxFileType? get() = this.virtualFile?.paradoxFileType

val PsiElement.paradoxRootType: ParadoxRootType? get() = this.virtualFile?.paradoxRootType

val PsiElement.paradoxPath: ParadoxPath? get() = this.virtualFile?.paradoxPath

//val PsiElement.paradoxPropertyPath:ParadoxPath?
//	get() {
//		TODO()
//	}

//查找方法

//使用stubIndex以提高性能

fun findScriptVariableInFile(name: String, file: PsiFile): ParadoxScriptVariable? {
	if(file !is ParadoxScriptFile) return null
	return file.variables.find { it.name == name }
}

fun findScriptVariable(name: String, project: Project, scope: GlobalSearchScope = GlobalSearchScope.allScope(project)): ParadoxScriptVariable? {
	return ParadoxScriptVariableKeyIndex.getOne(name, project, scope)
}

fun findScriptVariables(name: String, project: Project, scope: GlobalSearchScope = GlobalSearchScope.allScope(project)): List<ParadoxScriptVariable> {
	return ParadoxScriptVariableKeyIndex.get(name, project, scope)
}

fun findScriptVariables(project: Project, scope: GlobalSearchScope = GlobalSearchScope.allScope(project)): List<ParadoxScriptVariable> {
	return ParadoxScriptVariableKeyIndex.getAll(project, scope)
}

fun findScriptPropertyInFile(name: String, file: PsiFile): ParadoxScriptProperty? {
	if(file !is ParadoxScriptFile) return null
	return file.properties.find { it.name == name }
}

fun findScriptProperty(name: String, project: Project, scope: GlobalSearchScope = GlobalSearchScope.allScope(project)): ParadoxScriptProperty? {
	return ParadoxScriptPropertyKeyIndex.getOne(name, project, scope)
}

fun findScriptProperties(name: String, project: Project, scope: GlobalSearchScope = GlobalSearchScope.allScope(project)): List<ParadoxScriptProperty> {
	return ParadoxScriptPropertyKeyIndex.get(name, project, scope)
}

fun findScriptProperties(project: Project, scope: GlobalSearchScope = GlobalSearchScope.allScope(project)): List<ParadoxScriptProperty> {
	return ParadoxScriptPropertyKeyIndex.getAll(project, scope)
}

fun findLocalisationProperty(name: String, project: Project, locale: ParadoxLocale? = null, scope: GlobalSearchScope = GlobalSearchScope.allScope(project)): ParadoxLocalisationProperty? {
	return ParadoxLocalisationPropertyKeyIndex.getOne(name, locale, project, scope)
}

fun findLocalisationProperties(name: String, project: Project, locale: ParadoxLocale? = null, scope: GlobalSearchScope = GlobalSearchScope.allScope(project)): List<ParadoxLocalisationProperty> {
	return ParadoxLocalisationPropertyKeyIndex.get(name, locale, project, scope)
}

fun findLocalisationProperties(project: Project, locale: ParadoxLocale? = null, scope: GlobalSearchScope = GlobalSearchScope.allScope(project)): List<ParadoxLocalisationProperty> {
	return ParadoxLocalisationPropertyKeyIndex.getAll(locale, project, scope)
}

//将部分特定的查找方法作为扩展方法

fun findRelatedLocalisationProperties(scriptPropertyName: String, project: Project, locale: ParadoxLocale? = null): List<ParadoxLocalisationProperty> {
	return ParadoxLocalisationPropertyKeyIndex.filter(locale, project, GlobalSearchScope.allScope(project)) { name ->
		isRelatedLocalisationPropertyName(name, scriptPropertyName)
	}.sortedBy { it.name }
}

//xxx, xxx_desc, xxx_effect_desc
//pop_job->job_, pop_category->pop_cat_

private fun isRelatedLocalisationPropertyName(name: String, scriptPropertyName: String): Boolean {
	val fqName = name.removePrefix("job_").removePrefix("pop_cat_")
	return fqName == scriptPropertyName
	       || fqName == scriptPropertyName + "_desc"
	       || fqName == scriptPropertyName + "_effect_desc"
}

fun ParadoxLocalisationProperty.getRelatedLocalisationPropertyKey(): String {
	val name = this.name
	return when {
		name.endsWith("_effect_desc") -> "paradox.documentation.effect"
		name.endsWith("desc") -> "paradox.documentation.desc"
		else -> "paradox.documentation.name"
	}
}

//工具扩展

@Suppress("NOTHING_TO_INLINE")
inline fun message(@PropertyKey(resourceBundle = paradoxBundleName) key: String, vararg params: Any): String {
	return ParadoxBundle.getMessage(key, *params)
}

@Suppress("NOTHING_TO_INLINE")
inline fun String.resolveIconUrl(defaultToUnknown: Boolean = true): String {
	return ParadoxIconUrlResolver.resolve(this, defaultToUnknown)
}

@Suppress("NOTHING_TO_INLINE")
inline fun ParadoxLocalisationPropertyValue.renderRichText(): String {
	return ParadoxRichTextRenderer.render(this)
}

@Suppress("NOTHING_TO_INLINE")
inline fun ParadoxLocalisationPropertyValue.renderRichTextTo(buffer: Appendable) {
	ParadoxRichTextRenderer.renderTo(this, buffer)
}
