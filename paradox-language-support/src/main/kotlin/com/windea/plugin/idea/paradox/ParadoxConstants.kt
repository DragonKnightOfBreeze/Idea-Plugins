package com.windea.plugin.idea.paradox

import com.intellij.openapi.util.*
import com.windea.plugin.idea.paradox.model.*

//Strings

const val paradoxLocalisationName = "Paradox Localisation"
const val paradoxLocalisationNamePc = "ParadoxLocalisation"
const val paradoxLocalisationNameSsc = "PARADOX_LOCALISATION"
const val paradoxLocalisationLanguageName = "$paradoxLocalisationName Language"
const val paradoxLocalisationFileTypeName = "$paradoxLocalisationName File"
const val paradoxLocalisationFileTypeDescription = "$paradoxLocalisationName Language"
const val paradoxLocalisationExtension = "yml"
val paradoxLocalisationSampleText = "sampleText/ParadoxLocalisation.txt".toClassPathResource()!!.readText()

const val paradoxScriptName = "Paradox Script"
const val paradoxScriptNamePc = "ParadoxScript"
const val paradoxScriptNameSsc = "PARADOX_SCRIPT"
const val paradoxScriptLanguageName = "$paradoxScriptName Language"
const val paradoxScriptFileTypeName = "$paradoxScriptName File"
const val paradoxScriptFileTypeDescription = "$paradoxScriptName Language"
const val paradoxScriptExtension = "txt"
val paradoxScriptSampleText = "sampleText/ParadoxScript.txt".toClassPathResource()!!.readText()

const val commentFolder = "#..."
const val blockFolder = "{...}"
const val defaultFolder = "<folder>"

val utf8Bom = byteArrayOf(0xef.toByte(), 0xbb.toByte(), 0xbf.toByte())

val booleanValues = arrayOf("yes", "no")

const val paradoxBundleName = "messages.ParadoxBundle"

val localisationFileExtensions = arrayOf("yml", "yaml")
val scriptFileExtensions = arrayOf("txt", "mod", "gfx", "gui", "asset","cwt") //兼容cwtools的规则文件*.cwt

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

const val descriptorFileName = "descriptor.mod"

const val readmeFileName = "readme.txt"
const val changelogFileName = "changelog.txt"
const val creditsFileName = "credits.txt"
const val licenseFileName = "license.txt"
val ignoredFileNames = arrayOf(readmeFileName,changelogFileName,creditsFileName,licenseFileName)

const val stdlibName = "Stdlib"
const val paradoxName = "Paradox"

const val stellarisName = "Stellaris"
val gameNames = arrayOf(stellarisName)
//TODO Other Games

//Icons

val paradoxLocalisationFileIcon = IconLoader.findIcon("/icons/paradoxLocalisationFile.svg")!!
val paradoxLocalisationLocaleIcon = IconLoader.findIcon("/icons/paradoxLocalisationLocale.svg")!!
val paradoxLocalisationPropertyIcon = IconLoader.findIcon("/icons/paradoxLocalisationProperty.svg")!!

val paradoxScriptFileIcon = IconLoader.findIcon("/icons/paradoxScriptFile.svg")!!
val paradoxScriptVariableIcon = IconLoader.findIcon("/icons/paradoxScriptVariable.svg")!!
val paradoxScriptPropertyIcon = IconLoader.findIcon("/icons/paradoxScriptProperty.svg")!!
val paradoxScriptValueIcon = IconLoader.findIcon("/icons/paradoxScriptValue.svg")!!

val localisationPropertyGutterIcon = paradoxLocalisationPropertyIcon.resize(12, 12)
val scriptPropertyGutterIcon = paradoxScriptPropertyIcon.resize(12, 12)
//val eventIdGutterIcon = IconUtil.toSize(AllIcons.Nodes.Protected, 12, 12)
//val gfxKeyGutterIcon = IconUtil.toSize(AllIcons.Nodes.Related, 12, 12)
//val assetKeyGutterIcon = IconUtil.toSize(AllIcons.Nodes.Related, 12, 12)

val stellarisIcon = IconLoader.findIcon("icons/stellaris.png")!!
//TODO Other Games

//Keys

val paradoxFileTypeKey = Key<ParadoxFileType>("paradoxFileType")
val paradoxRootTypeKey = Key<ParadoxRootType>("paradoxRootType")
val paradoxPathKey = Key<ParadoxPath>("paradoxPath")
val paradoxPropertyPathKey = Key<ParadoxPath>("paradoxPropertyPath")