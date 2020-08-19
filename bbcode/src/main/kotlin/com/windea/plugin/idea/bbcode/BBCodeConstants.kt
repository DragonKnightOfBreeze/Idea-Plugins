package com.windea.plugin.idea.bbcode

import com.intellij.icons.*
import com.intellij.openapi.util.*

//region Strings
const val bbcodeName = "BBCode"
const val bbcodeNamePc = "BBCode"
const val bbcodeNameSsc = "BBCODE"
const val bbcodeLanguageName = "$bbcodeName Language"
const val bbcodeFileTypeName = "$bbcodeName File"
const val bbcodeFileTypeDescription = "$bbcodeName Language"
const val bbcodeExtension = "bbcode"

val bbcodeDummyText = "/example.bbcode".toClassPathResource<BBCodeBundle>().readText()

const val bbcodeBundleName = "messages.BBCode"
//endregion

//region Resources
val bbcodeFileIcon = IconLoader.getIcon("icons/bbcode.png")
val bbcodeTagIcon = AllIcons.Nodes.Tag
//endregion

