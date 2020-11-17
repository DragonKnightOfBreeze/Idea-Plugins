package com.windea.plugin.idea.stellaris

import com.intellij.icons.*
import com.intellij.openapi.project.*
import com.intellij.openapi.util.*
import com.intellij.openapi.vfs.*
import com.intellij.psi.search.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.settings.*
import java.nio.file.*
import java.util.*
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
const val rootBlockFolder = "<root block>"
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
//endregion

//region Resources
val stellarisLocalizationFileIcon = IconLoader.findIcon("/icons/stellaris_localization.png")
val stellarisLocalizationLocaleIcon = AllIcons.FileTypes.Properties
val stellarisLocalizationPropertyIcon = AllIcons.Nodes.PropertyRead

val stellarisScriptFileIcon = IconLoader.findIcon("/icons/stellaris_script.png")
val stellarisScriptVariableIcon = AllIcons.Nodes.Variable
val stellarisScriptArrayIcon = AllIcons.Json.Array
val stellarisScriptObjectIcon = AllIcons.Json.Object
val stellarisScriptPropertyIcon = AllIcons.Nodes.Property
val stellarisScriptValueIcon = AllIcons.Nodes.Constant

val localizationPropertyGutterIcon = IconUtil.toSize(stellarisLocalizationPropertyIcon, 12, 12)
val scriptPropertyGutterIcon = IconUtil.toSize(stellarisScriptPropertyIcon, 12, 12)
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
