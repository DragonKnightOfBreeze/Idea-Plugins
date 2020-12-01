package com.windea.plugin.idea.pdx

import com.intellij.codeInsight.documentation.*
import com.intellij.icons.*
import com.intellij.openapi.project.*
import com.intellij.openapi.util.*
import com.intellij.openapi.vfs.*
import com.intellij.util.*
import com.windea.plugin.idea.pdx.localisation.psi.*
import java.util.concurrent.*

//region Strings
const val pdxLocalisationName = "Pdx Localisation"
const val pdxLocalisationNamePc = "PdxLocalisation"
const val pdxLocalisationNameSsc = "PDX_LOCALISATION"
const val pdxLocalisationLanguageName = "$pdxLocalisationName Language"
const val pdxLocalisationFileTypeName = "$pdxLocalisationName File"
const val pdxLocalisationFileTypeDescription = "$pdxLocalisationName Language"
const val pdxLocalisationExtension = "yml"

val pdxLocalisationDummyText = "dummyText/PdxLocalisation.txt".toClassPathResource()?.readText().orEmpty()

const val pdxScriptName = "Pdx Script"
const val pdxScriptNamePc = "PdxScript"
const val pdxScriptNameSsc = "PDX_SCRIPT"
const val pdxScriptLanguageName = "$pdxScriptName Language"
const val pdxScriptFileTypeName = "$pdxScriptName File"
const val pdxScriptFileTypeDescription = "$pdxScriptName Language"
const val pdxScriptExtension = "txt"

val pdxScriptDummyText = "dummyText/PdxScript.txt".toClassPathResource()?.readText().orEmpty()

const val commentFolder = "#..."
const val blockFolder = "{...}"
const val defaultFolder = "<folder>"

val utf8Bom = byteArrayOf(0xef.toByte(), 0xbb.toByte(), 0xbf.toByte())

val booleanValues = arrayOf("yes", "no")

const val pdxBundleName = "messages.PdxBundle"
const val pdxExeFileName = "pdx.exe"
const val descriptorModFileName = "descriptor.mod"
const val anonymous =  "(anonymous)"

val localisationDirectories = arrayOf("localisation","localisation_synced")

val fileExtensions = arrayOf("yml", "txt", "mod", "gui", "gfx", "asset")
val localisationFileExtensions = arrayOf("yml", "yaml")
val scriptFileExtensions = arrayOf("txt", "mod", "gfx", "gui", "asset")

val inferredPdxLocale = when(System.getProperty("user.language")){
	"zh"-> PdxLocale.SIMP_CHINESE
	"en" -> PdxLocale.ENGLISH
	"pt" -> PdxLocale.BRAZ_POR
	"fr" -> PdxLocale.FRENCH
	"de" -> PdxLocale.GERMAN
	"pl" -> PdxLocale.PONISH
	"ru" -> PdxLocale.RUSSIAN
	"es" -> PdxLocale.SPANISH
	else -> PdxLocale.ENGLISH
}

const val paradoxwikisUrl="https://pdx.paradoxwikis.com"
const val huijiwikiUrl = "https://qunxing.huijiwiki.com"
//endregion

//region Icons
val pdxLocalisationFileIcon = IconLoader.findIcon("/icons/pdxLocalisationFile.png")!!
val pdxLocalisationLocaleIcon = IconLoader.findIcon("/icons/pdxLocalisationLocale.svg")!!
val pdxLocalisationPropertyIcon = IconLoader.findIcon("/icons/pdxLocalisationProperty.svg")!!

val pdxScriptFileIcon = IconLoader.findIcon("/icons/pdxScriptFile.png")!!
val pdxScriptVariableIcon = IconLoader.findIcon("/icons/pdxScriptVariable.svg")!!
val pdxScriptPropertyIcon = IconLoader.findIcon("/icons/pdxScriptProperty.svg")!!
val pdxScriptValueIcon = IconLoader.findIcon("/icons/pdxScriptValue.svg")!!

val localisationPropertyGutterIcon = pdxLocalisationPropertyIcon.resize(12, 12)
val scriptPropertyGutterIcon = pdxScriptPropertyIcon.resize(12, 12)
//val eventIdGutterIcon = IconUtil.toSize(AllIcons.Nodes.Protected, 12, 12)
//val gfxKeyGutterIcon = IconUtil.toSize(AllIcons.Nodes.Related, 12, 12)
//val assetKeyGutterIcon = IconUtil.toSize(AllIcons.Nodes.Related, 12, 12)
//endregion

//region Keys
val pdxPathKey = Key<String>("pdxPath")
val pdxParentPathKey = Key<String>("pdxParentPath")
//endregion

//region Caches
val rootDirectoryCache = ConcurrentHashMap<String,VirtualFile>()

val localisationLocaleCache = ConcurrentHashMap<Project, Array<PdxLocalisationLocale>>()
fun MutableMap<Project, Array<PdxLocalisationLocale>>.register(project: Project) = this.getOrPut(project) {
	PdxLocale.keys.mapArray { e -> PdxLocalisationElementFactory.createLocale(project, e) }
}

val localisationSerialNumberCache = ConcurrentHashMap<Project, Array<PdxLocalisationSerialNumber>>()
fun MutableMap<Project, Array<PdxLocalisationSerialNumber>>.register(project: Project) = this.getOrPut(project) {
	PdxSerialNumber.keys.mapArray { e -> PdxLocalisationElementFactory.createSerialNumber(project, e) }
}

val localisationColorfulTextCache = ConcurrentHashMap<Project, Array<PdxLocalisationColorfulText>>()
fun MutableMap<Project, Array<PdxLocalisationColorfulText>>.register(project: Project) = this.getOrPut(project) {
	PdxColor.keys.mapArray { e -> PdxLocalisationElementFactory.createColorfulText(project, e) }
}
//endregion
