package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.codeInspection.*
import com.intellij.codeInspection.ui.*
import com.intellij.json.*
import com.intellij.psi.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.ide.*
import com.jetbrains.jsonSchema.impl.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.script.psi.*
import org.jetbrains.annotations.*
import javax.swing.*

//org.jetbrains.yaml.schema.YamlJsonSchemaInspectionBase
//org.jetbrains.yaml.schema.YamlJsonSchemaHighlightingInspection

class StellarisScriptSchemaInspectionTool : LocalInspectionTool() {
	private val walker = StellarisScriptJsonLikePsiWalker

	@JvmField public var caseInsensitiveEnum = true

	override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean, session: LocalInspectionToolSession): PsiElementVisitor {
		val file = holder.file
		if(file is StellarisScriptFile) {
			val roots = walker.getRoots(file)
			if(roots.isNotEmpty()) {
				val service = JsonSchemaService.Impl.get(file.project)
				val virtualFile = file.viewProvider.virtualFile
				if(service.isApplicableToFile(virtualFile)) {
					val rootSchema = service.getSchemaObject(file)
					if(rootSchema != null) {
						return doBuildVisitor(holder, session, roots, rootSchema)
					}
				}
			}
		}
		return PsiElementVisitor.EMPTY_VISITOR
	}

	private fun doBuildVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession, roots: Collection<PsiElement>, schemaObject: JsonSchemaObject): PsiElementVisitor {
		val options = JsonComplianceCheckerOptions(caseInsensitiveEnum)
		return object : StellarisScriptVisitor() {
			override fun visitElement(element: PsiElement) {
				if(element in roots) {
					JsonSchemaComplianceChecker(schemaObject, holder, walker, session, options, "Schema validation: ").annotate(element)
				}
				super.visitElement(element)
			}
		}
	}

	override fun createOptionsPanel(): JComponent? {
		val optionsPanel = MultipleCheckboxOptionsPanel(this)
		optionsPanel.addCheckbox(message("stellaris.script.inspection.schemaValidation.caseInsensitiveEnum"), "caseInsensitiveEnum")
		return optionsPanel
	}
}
