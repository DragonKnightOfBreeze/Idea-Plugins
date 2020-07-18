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
import com.intellij.openapi.util.Iconable.IconFlags;
import com.intellij.psi.PsiReference;
import javax.swing.Icon;

public class StellarisScriptVariableReferenceImpl extends StellarisScriptNamedElementImpl implements StellarisScriptVariableReference {

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
  public PsiElement getVariableReferenceToken() {
    return findNotNullChildByType(VARIABLE_REFERENCE_TOKEN);
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
  @Nullable
  public PsiElement getNameIdentifier() {
    return StellarisScriptPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  @Nullable
  public Icon getIcon(@IconFlags int flags) {
    return StellarisScriptPsiImplUtil.getIcon(this, flags);
  }

  @Override
  @NotNull
  public PsiReference getReference() {
    return StellarisScriptPsiImplUtil.getReference(this);
  }

}
