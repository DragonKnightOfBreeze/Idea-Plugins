package com.windea.plugin.idea.sbtext

import com.intellij.icons.*

//region Strings
const val sbTextName = "Starbound Text"
const val sbTextNamePc = "StarboundText"
const val sbTextNameSsc = "STARBOUND_TEXT"
const val sbTextLanguageName = "$sbTextName Language"
const val sbTextFileTypeName = "$sbTextName File"
const val sbTextFileTypeDescription = "$sbTextName Language"
const val sbTextExtension = "sbtxt"

val sbTextDummyText = "/example.txt".toClassPathResource<SbTextBundle>().readText()

const val sbTextBundleName = "messages.SbTextBundle"
//endregion

//region Resources
val sbTextFileIcon = AllIcons.FileTypes.Text
//endregion
