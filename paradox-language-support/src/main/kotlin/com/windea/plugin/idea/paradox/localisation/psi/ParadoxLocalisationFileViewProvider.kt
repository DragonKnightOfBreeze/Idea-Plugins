package com.windea.plugin.idea.paradox.localisation.psi

import com.intellij.lang.*
import com.intellij.openapi.vfs.*
import com.intellij.psi.*
import com.intellij.testFramework.*
import com.windea.plugin.idea.paradox.*

class ParadoxLocalisationFileViewProvider(
	manager: PsiManager,
	virtualFile: VirtualFile,
	eventSystemEnabled: Boolean
): SingleRootFileViewProvider(manager,virtualFile,eventSystemEnabled) {
	//virtualFile可能是LightVirtualFile
	val fileInfo = when(virtualFile) {
		is LightVirtualFile -> virtualFile.originalFile?.paradoxFileInfo
		else -> virtualFile.paradoxFileInfo
	}
	
	override fun getPsiInner(target: Language): PsiFile? {
		val psi =  super.getPsiInner(target)
		//传递fileInfo
		psi?.putUserData(paradoxFileInfoKey,fileInfo)
		return psi
	}
}

