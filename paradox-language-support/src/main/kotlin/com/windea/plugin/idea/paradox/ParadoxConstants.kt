package com.windea.plugin.idea.paradox

import com.intellij.codeInsight.documentation.*
import com.intellij.icons.*
import com.intellij.openapi.project.*
import com.intellij.openapi.util.*
import com.intellij.openapi.vfs.*
import com.intellij.util.*
import com.windea.plugin.idea.paradox.localisation.psi.*
import java.util.concurrent.*

//region Strings
const val paradoxLocalisationName = "Paradox Localisation"
const val paradoxLocalisationNamePc = "ParadoxLocalisation"
const val paradoxLocalisationNameSsc = "PARADOX_LOCALISATION"
const val paradoxLocalisationLanguageName = "$paradoxLocalisationName Language"
const val paradoxLocalisationFileTypeName = "$paradoxLocalisationName File"
const val paradoxLocalisationFileTypeDescription = "$paradoxLocalisationName Language"
const val paradoxLocalisationExtension = "yml"
val paradoxLocalisationDummyText = "dummyText/ParadoxLocalisation.txt".toClassPathResource().readText()

const val paradoxScriptName = "Paradox Script"
const val paradoxScriptNamePc = "ParadoxScript"
const val paradoxScriptNameSsc = "PARADOX_SCRIPT"
const val paradoxScriptLanguageName = "$paradoxScriptName Language"
const val paradoxScriptFileTypeName = "$paradoxScriptName File"
const val paradoxScriptFileTypeDescription = "$paradoxScriptName Language"
const val paradoxScriptExtension = "txt"
val paradoxScriptDummyText = "dummyText/ParadoxScript.txt".toClassPathResource().readText()

const val commentFolder = "#..."
const val blockFolder = "{...}"
const val defaultFolder = "<folder>"

val utf8Bom = byteArrayOf(0xef.toByte(), 0xbb.toByte(), 0xbf.toByte())

val booleanValues = arrayOf("yes", "no")

const val paradoxBundleName = "messages.ParadoxBundle"
const val paradoxExeFileName = "paradox.exe"
const val descriptorModFileName = "descriptor.mod"

val fileExtensions = arrayOf("yml", "txt", "mod", "gui", "gfx", "asset")
val localisationFileExtensions = arrayOf("yml", "yaml")
val scriptFileExtensions = arrayOf("txt", "mod", "gfx", "gui", "asset")

const val paradoxwikisUrl="https://paradox.paradoxwikis.com"
const val huijiwikiUrl = "https://qunxing.huijiwiki.com"

val inferredParadoxLocale = when(System.getProperty("user.language")){
	"zh"-> ParadoxLocale.SIMP_CHINESE
	"en" -> ParadoxLocale.ENGLISH
	"pt" -> ParadoxLocale.BRAZ_POR
	"fr" -> ParadoxLocale.FRENCH
	"de" -> ParadoxLocale.GERMAN
	"pl" -> ParadoxLocale.PONISH
	"ru" -> ParadoxLocale.RUSSIAN
	"es" -> ParadoxLocale.SPANISH
	else -> ParadoxLocale.ENGLISH
}
//endregion

//region Icons
val paradoxLocalisationFileIcon = IconLoader.findIcon("/icons/paradoxLocalisationFile.png")!!
val paradoxLocalisationLocaleIcon = IconLoader.findIcon("/icons/paradoxLocalisationLocale.svg")!!
val paradoxLocalisationPropertyIcon = IconLoader.findIcon("/icons/paradoxLocalisationProperty.svg")!!

val paradoxScriptFileIcon = IconLoader.findIcon("/icons/paradoxScriptFile.png")!!
val paradoxScriptVariableIcon = IconLoader.findIcon("/icons/paradoxScriptVariable.svg")!!
val paradoxScriptPropertyIcon = IconLoader.findIcon("/icons/paradoxScriptProperty.svg")!!
val paradoxScriptValueIcon = IconLoader.findIcon("/icons/paradoxScriptValue.svg")!!

val localisationPropertyGutterIcon = paradoxLocalisationPropertyIcon.resize(12, 12)
val scriptPropertyGutterIcon = paradoxScriptPropertyIcon.resize(12, 12)
//val eventIdGutterIcon = IconUtil.toSize(AllIcons.Nodes.Protected, 12, 12)
//val gfxKeyGutterIcon = IconUtil.toSize(AllIcons.Nodes.Related, 12, 12)
//val assetKeyGutterIcon = IconUtil.toSize(AllIcons.Nodes.Related, 12, 12)
//endregion

//region Keys
val paradoxPathKey = Key<String>("paradoxPath")
val paradoxParentPathKey = Key<String>("paradoxParentPath")
//endregion

//region Caches
val rootDirectoryCache = ConcurrentHashMap<String,VirtualFile>()

val localisationLocaleCache = ConcurrentHashMap<Project, Array<ParadoxLocalisationLocale>>()
fun MutableMap<Project, Array<ParadoxLocalisationLocale>>.register(project: Project) = this.getOrPut(project) {
	ParadoxLocale.keys.mapArray { e -> ParadoxLocalisationElementFactory.createLocale(project, e) }
}

val localisationSerialNumberCache = ConcurrentHashMap<Project, Array<ParadoxLocalisationSerialNumber>>()
fun MutableMap<Project, Array<ParadoxLocalisationSerialNumber>>.register(project: Project) = this.getOrPut(project) {
	ParadoxSerialNumber.keys.mapArray { e -> ParadoxLocalisationElementFactory.createSerialNumber(project, e) }
}

val localisationColorfulTextCache = ConcurrentHashMap<Project, Array<ParadoxLocalisationColorfulText>>()
fun MutableMap<Project, Array<ParadoxLocalisationColorfulText>>.register(project: Project) = this.getOrPut(project) {
	ParadoxColor.keys.mapArray { e -> ParadoxLocalisationElementFactory.createColorfulText(project, e) }
}
//endregion
