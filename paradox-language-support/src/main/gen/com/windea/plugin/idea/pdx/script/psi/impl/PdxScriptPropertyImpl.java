// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.script.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.pdx.script.psi.PdxScriptTypes.*;
import com.windea.plugin.idea.pdx.script.psi.PdxScriptPropertyStub;
import com.windea.plugin.idea.pdx.script.psi.*;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;
import com.intellij.psi.stubs.IStubElementType;

public class PdxScriptPropertyImpl extends PdxScriptStubElementImpl<PdxScriptPropertyStub> implements PdxScriptProperty {

  public PdxScriptPropertyImpl(@NotNull PdxScriptPropertyStub stub, @Nullable IStubElementType<?, ?> nodeType) {
    super(stub, nodeType);
  }

  public PdxScriptPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PdxScriptVisitor visitor) {
    visitor.visitProperty(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PdxScriptVisitor) accept((PdxScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PdxScriptPropertyKey getPropertyKey() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, PdxScriptPropertyKey.class));
  }

  @Override
  @Nullable
  public PdxScriptPropertySeparator getPropertySeparator() {
    return PsiTreeUtil.getChildOfType(this, PdxScriptPropertySeparator.class);
  }

  @Override
  @Nullable
  public PdxScriptPropertyValue getPropertyValue() {
    return PsiTreeUtil.getChildOfType(this, PdxScriptPropertyValue.class);
  }

  @Override
  @NotNull
  public String getName() {
    return PdxScriptPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String name) {
    return PdxScriptPsiImplUtil.setName(this, name);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return PdxScriptPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  @NotNull
  public Icon getIcon(@IconFlags int flags) {
    return PdxScriptPsiImplUtil.getIcon(this, flags);
  }

  @Override
  @Nullable
  public String getValue() {
    return PdxScriptPsiImplUtil.getValue(this);
  }

}
