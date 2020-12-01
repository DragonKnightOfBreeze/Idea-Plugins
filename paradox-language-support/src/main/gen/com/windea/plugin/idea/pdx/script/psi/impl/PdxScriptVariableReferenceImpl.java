// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*;
import com.windea.plugin.idea.stellaris.script.psi.*;
import com.windea.plugin.idea.stellaris.script.reference.StellarisScriptVariablePsiReference;

public class StellarisScriptVariableReferenceImpl extends StellarisScriptValueImpl implements StellarisScriptVariableReference {

  public StellarisScriptVariableReferenceImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisScriptVisitor visitor) {
    visitor.visitVariableReference(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisScriptVisitor) accept((StellarisScriptVisitor)visitor);
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
    return StellarisScriptPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String name) {
    return StellarisScriptPsiImplUtil.setName(this, name);
  }

  @Override
  @NotNull
  public PsiElement getNameIdentifier() {
    return StellarisScriptPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  @NotNull
  public StellarisScriptVariablePsiReference getReference() {
    return StellarisScriptPsiImplUtil.getReference(this);
  }

}
