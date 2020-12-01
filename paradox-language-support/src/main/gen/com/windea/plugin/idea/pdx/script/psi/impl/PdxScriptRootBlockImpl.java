// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.script.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.pdx.script.psi.PdxScriptTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.windea.plugin.idea.pdx.script.psi.*;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;

public class PdxScriptRootBlockImpl extends ASTWrapperPsiElement implements PdxScriptRootBlock {

  public PdxScriptRootBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PdxScriptVisitor visitor) {
    visitor.visitRootBlock(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PdxScriptVisitor) accept((PdxScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<PdxScriptProperty> getPropertyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PdxScriptProperty.class);
  }

  @Override
  @NotNull
  public List<PdxScriptValue> getValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PdxScriptValue.class);
  }

  @Override
  @NotNull
  public List<PdxScriptVariable> getVariableList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PdxScriptVariable.class);
  }

  @Override
  public boolean isEmpty() {
    return PdxScriptPsiImplUtil.isEmpty(this);
  }

  @Override
  public boolean isNotEmpty() {
    return PdxScriptPsiImplUtil.isNotEmpty(this);
  }

  @Override
  public boolean isObject() {
    return PdxScriptPsiImplUtil.isObject(this);
  }

  @Override
  public boolean isArray() {
    return PdxScriptPsiImplUtil.isArray(this);
  }

  @Override
  @NotNull
  public List<PsiElement> getComponents() {
    return PdxScriptPsiImplUtil.getComponents(this);
  }

  @Override
  @NotNull
  public Icon getIcon(@IconFlags int flags) {
    return PdxScriptPsiImplUtil.getIcon(this, flags);
  }

}
