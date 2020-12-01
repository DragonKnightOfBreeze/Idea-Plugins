// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.script.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.pdx.script.psi.PdxScriptTypes.*;
import com.windea.plugin.idea.pdx.script.psi.*;
import com.windea.plugin.idea.pdx.script.reference.PdxScriptVariablePsiReference;

public class PdxScriptVariableReferenceImpl extends PdxScriptValueImpl implements PdxScriptVariableReference {

  public PdxScriptVariableReferenceImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PdxScriptVisitor visitor) {
    visitor.visitVariableReference(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PdxScriptVisitor) accept((PdxScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getVariableReferenceId() {
    return notNullChild(findChildByType(VARIABLE_REFERENCE_ID));
  }

  @Override
  @Nullable
  public String getName() {
    return PdxScriptPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String name) {
    return PdxScriptPsiImplUtil.setName(this, name);
  }

  @Override
  @NotNull
  public PsiElement getNameIdentifier() {
    return PdxScriptPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  @NotNull
  public PdxScriptVariablePsiReference getReference() {
    return PdxScriptPsiImplUtil.getReference(this);
  }

}
