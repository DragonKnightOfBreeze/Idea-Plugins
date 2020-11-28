package com.windea.plugin.idea.stellaris

import com.intellij.codeInsight.documentation.*
import com.intellij.icons.*
import com.intellij.openapi.project.*
import com.intellij.openapi.util.*
import com.intellij.openapi.vfs.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import java.util.concurrent.*

//region Strings
const val stellarisLocalizationName = "Stellaris Localization"
const val stellarisLocalizationNamePc = "StellarisLocalization"
const val stellarisLocalizationNameSsc = "STELLARIS_LOCALIZATION"
const val stellarisLocalizationLanguageName = "$stellarisLocalizationName Language"
const val stellarisLocalizationFileTypeName = "$stellarisLocalizationName File"
const val stellarisLocalizationFileTypeDescription = "$stellarisLocalizationName Language"
const val stellarisLocalizationExtension = "yml"

val stellarisLocalizationDummyText = "dummyText/StellarisLocalization.txt".toClassPathResource()?.readText().orEmpty()
val stellarisLocalizationLocaleRegex = "[a-z_]+".toRegex()
val stellarisLocalizationPropertyKeyRegex = "[a-zA-Z][a-zA-Z0-9_.]*".toRegex()

const val stellarisScriptName = "Stellaris Script"
const val stellarisScriptNamePc = "StellarisScript"
const val stellarisScriptNameSsc = "STELLARIS_SCRIPT"
const val stellarisScriptLanguageName = "$stellarisScriptName Language"
const val stellarisScriptFileTypeName = "$stellarisScriptName File"
const val stellarisScriptFileTypeDescription = "$stellarisScriptName Language"
const val stellarisScriptExtension = "txt"

val stellarisScriptDummyText = "dummyText/StellarisScript.txt".toClassPathResource()?.readText().orEmpty()
val stellarisScriptVariableRegex = "@[a-zA-Z0-9_-]+".toRegex()
val stellarisScriptPropertyRegex = "[a-z0-9_-]+".toRegex()

const val commentFolder = "#..."
const val blockFolder = "{...}"
const val defaultFolder = "<folder>"

const val syntaxError = "(syntax error)"

val utf8Bom = byteArrayOf(0xef.toByte(), 0xbb.toByte(), 0xbf.toByte())

val booleanValues = arrayOf("yes", "no")

const val stellarisBundleName = "messages.StellarisBundle"

const val stellarisExeFileName = "stellaris.exe"
const val descriptorModFileName = "descriptor.mod"
const val anonymous =  "(anonymous)"

val localizationDirectories = arrayOf("localisation","localisation_synced")

val fileExtensions = arrayOf("yml", "txt", "mod", "gui", "gfx", "asset")
val localizationFileExtensions = arrayOf("yml", "yaml")
val scriptFileExtensions = arrayOf("txt", "mod", "gfx", "gui", "asset")

val inferedStellarisLocale = when(System.getProperty("user.language")){
	"zh"-> StellarisLocale.SIMP_CHINESE
	"en" -> StellarisLocale.ENGLISH
	"pt" -> StellarisLocale.BRAZ_POR
	"fr" -> StellarisLocale.FRENCH
	"de" -> StellarisLocale.GERMAN
	"pl" -> StellarisLocale.PONISH
	"ru" -> StellarisLocale.RUSSIAN
	"es" -> StellarisLocale.SPANISH
	else -> StellarisLocale.ENGLISH
}

val iconSize get() = DocumentationComponent.getQuickDocFontSize().size

fun iconTag(url:String,size:Int = iconSize):String{
	return "<img src=\"$url\" width=\"$size\" height=\"$size\"/>"
}

//https://qunxing.huijiwiki.com/
//https://stellaris.paradoxwikis.com/
//https://qunxing.huijiwiki.com/wiki/%E6%96%87%E4%BB%B6:Xxx.png
//https://stellaris.paradoxwikis.com/File:Xxx.png

const val paradoxwikisUrl="https://stellaris.paradoxwikis.com"
const val huijiwikiUrl = "https://qunxing.huijiwiki.com"
//endregion

//region Resources
val stellarisLocalizationFileIcon = IconLoader.findIcon("/icons/stellarisLocalizationFile.png")!!
val stellarisLocalizationLocaleIcon = IconLoader.findIcon("/icons/stellarisLocalizationLocale.svg")!!
val stellarisLocalizationPropertyIcon = IconLoader.findIcon("/icons/stellarisLocalizationProperty.svg")!!

val stellarisScriptFileIcon = IconLoader.findIcon("/icons/stellarisScriptFile.png")!!
val stellarisScriptVariableIcon = IconLoader.findIcon("/icons/stellarisScriptVariable.svg")!!
val stellarisScriptPropertyIcon = IconLoader.findIcon("/icons/stellarisScriptProperty.svg")!!
val stellarisScriptValueIcon = IconLoader.findIcon("/icons/stellarisScriptValue.svg")!!

val localizationPropertyGutterIcon = stellarisLocalizationPropertyIcon.resize(12, 12)
val scriptPropertyGutterIcon = stellarisScriptPropertyIcon.resize(12, 12)
//val eventIdGutterIcon = IconUtil.toSize(AllIcons.Nodes.Protected, 12, 12)
//val gfxKeyGutterIcon = IconUtil.toSize(AllIcons.Nodes.Related, 12, 12)
//val assetKeyGutterIcon = IconUtil.toSize(AllIcons.Nodes.Related, 12, 12)
//endregion

//region Caches
val rootDirectoryCache = ConcurrentHashMap<String,VirtualFile>()
val stellarisPathCache = ConcurrentHashMap<VirtualFile,String>()

val localizationLocaleCache = ConcurrentHashMap<Project, Array<StellarisLocalizationLocale>>()
fun MutableMap<Project, Array<StellarisLocalizationLocale>>.register(project: Project) = this.getOrPut(project) {
	StellarisLocale.keys.mapArray { e -> StellarisLocalizationElementFactory.createLocale(project, e) }
}

val localizationSerialNumberCache = ConcurrentHashMap<Project, Array<StellarisLocalizationSerialNumber>>()
fun MutableMap<Project, Array<StellarisLocalizationSerialNumber>>.register(project: Project) = this.getOrPut(project) {
	StellarisSerialNumber.keys.mapArray { e -> StellarisLocalizationElementFactory.createSerialNumber(project, e) }
}

val localizationColorfulTextCache = ConcurrentHashMap<Project, Array<StellarisLocalizationColorfulText>>()
fun MutableMap<Project, Array<StellarisLocalizationColorfulText>>.register(project: Project) = this.getOrPut(project) {
	StellarisColor.keys.mapArray { e -> StellarisLocalizationElementFactory.createColorfulText(project, e) }
}
//endregion
