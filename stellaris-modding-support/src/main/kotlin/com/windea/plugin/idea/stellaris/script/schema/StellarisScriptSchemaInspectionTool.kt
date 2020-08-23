package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.codeInspection.*
import com.intellij.codeInspection.ui.*
import com.intellij.json.*
import com.intellij.psi.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.ide.*
import com.jetbrains.jsonSchema.impl.*
import com.windea.plugin.idea.stellaris.script.psi.*
import org.jetbrains.annotations.*
import javax.swing.*

//org.jetbrains.yaml.schema.YamlJsonSchemaInspectionBase
//org.jetbrains.yaml.schema.YamlJsonSchemaHighlightingInspection

class StellarisScriptSchemaInspectionTool : LocalInspectionTool() {
	@JvmField
	public var caseInsensitiveEnum = false

	override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean, session: LocalInspectionToolSession): PsiElementVisitor {
		val file = holder.file
		if(file !is StellarisScriptFile) return PsiElementVisitor.EMPTY_VISITOR

		val roots: Collection<PsiElement> = StellarisScriptJsonLikePsiWalker.getRoots(file)
		if(roots.isEmpty()) return PsiElementVisitor.EMPTY_VISITOR

		val service = JsonSchemaService.Impl.get(file.project)
		val virtualFile = file.viewProvider.virtualFile
		if(!service.isApplicableToFile(virtualFile)) return PsiElementVisitor.EMPTY_VISITOR
		val rootSchema = service.getSchemaObject(file) ?: return PsiElementVisitor.EMPTY_VISITOR
		return doBuildVisitor(holder, session, roots, rootSchema)
	}

	private fun doBuildVisitor(holder: ProblemsHolder, session: LocalInspectionToolSession, roots: Collection<PsiElement>, schemaObject: JsonSchemaObject): PsiElementVisitor {
		val options = JsonComplianceCheckerOptions(caseInsensitiveEnum)
		return object : StellarisScriptVisitor() {
			override fun visitElement(element: PsiElement) {
				if(roots.contains(element)) {
					val walker = JsonLikePsiWalker.getWalker(element, schemaObject) ?: return
					JsonSchemaComplianceChecker(schemaObject, holder, walker, session, options, "Schema validation: ").annotate(element)
				}
			}
		}
	}

	override fun createOptionsPanel(): JComponent? {
		val optionsPanel = MultipleCheckboxOptionsPanel(this)
		optionsPanel.addCheckbox(JsonBundle.message("json.schema.inspection.case.insensitive.enum"), "caseInsensitiveEnum")
		return optionsPanel
	}
}
