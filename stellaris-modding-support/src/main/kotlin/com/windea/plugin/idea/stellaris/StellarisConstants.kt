package com.windea.plugin.idea.stellaris

import com.intellij.icons.*
import com.intellij.openapi.project.*
import com.intellij.openapi.util.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.domain.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import java.util.concurrent.*

//Strings

const val fatalError = "<fatal error>"
const val foldedBlock = "<folded block>"
const val breadCrumbsElement = "<bread crumbs element>"
const val anonymousElement = "<anonymous element>"

const val stellarisLocalizationName = "Stellaris Localization"
const val stellarisLocalizationNamePc = "StellarisLocalization"
const val stellarisLocalizationNameSsc = "STELLARIS_LOCALIZATION"
const val stellarisLocalizationLanguageName = "$stellarisLocalizationName Language"
const val stellarisLocalizationFileTypeName = "$stellarisLocalizationName File"
const val stellarisLocalizationFileTypeDescription = "$stellarisLocalizationName Language"
const val stellarisLocalizationExtension = "yml"

val stellarisLocalizationDummyText = "/example.yml".toClassPathResource<StellarisBundle>().readText()
val stellarisLocalizationLocaleRegex = "[a-z_]+".toRegex()
val stellarisLocalizationPropertyKeyRegex = "[a-zA-Z][a-zA-Z0-9_.]*".toRegex()

const val stellarisScriptName = "Stellaris Script"
const val stellarisScriptNamePc = "StellarisScript"
const val stellarisScriptNameSsc = "STELLARIS_SCRIPT"
const val stellarisScriptLanguageName = "$stellarisScriptName Language"
const val stellarisScriptFileTypeName = "$stellarisScriptName File"
const val stellarisScriptFileTypeDescription = "$stellarisScriptName Language"
const val stellarisScriptExtension = "txt"

val stellarisScriptDummyText = "/example.txt".toClassPathResource<StellarisBundle>().readText()
val stellarisScriptVariableRegex = "@[a-zA-Z0-9_-]+".toRegex()
val stellarisScriptPropertyRegex = "[a-z0-9_-]+".toRegex()

//IO

//0xef 0xbb 0xbf
val utf8Bom = byteArrayOf(0xef.toByte(), 0xbb.toByte(), 0xbf.toByte())

//Paths

const val stellarisBundleName = "messages.StellarisBundle"

//Resources

val localizationFileIcon = IconLoader.getIcon("/icons/stellaris_localization.png")
val localizationLocaleIcon = AllIcons.FileTypes.Properties
val localizationPropertyIcon = AllIcons.Nodes.PropertyRead

val scriptFileIcon = IconLoader.getIcon("/icons/stellaris_script.png")
val scriptVariableIcon = AllIcons.Nodes.Variable
val scriptPropertyIcon = AllIcons.Nodes.Property
val scriptItemIcon = AllIcons.Nodes.Constant

val externalLocalizationPropertyGutterIcon = IconUtil.toSize(localizationLocaleIcon, 12, 12)
val externalScriptPropertyGutterIcon = IconUtil.toSize(scriptPropertyIcon, 12, 12)
val eventIdGutterIcon = IconUtil.toSize(AllIcons.Nodes.Protected,12,12)
val gfxKeyGutterIcon = IconUtil.toSize(AllIcons.Nodes.Related,12,12)
val assetKeyGutterIcon = IconUtil.toSize(AllIcons.Nodes.Related,12,12)
val actionFieldGutterIcon = IconUtil.toSize(AllIcons.Nodes.Field,12,12)

//Caches
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
