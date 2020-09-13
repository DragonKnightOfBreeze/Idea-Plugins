// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.bbcode.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.bbcode.psi.BBCodeTypes.*;
import com.windea.plugin.idea.bbcode.psi.*;

public class BBCodeAttributeImpl extends BBCodeNamedElementImpl implements BBCodeAttribute {

  public BBCodeAttributeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BBCodeVisitor visitor) {
    visitor.visitAttribute(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BBCodeVisitor) accept((BBCodeVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getAttributeName() {
    return findNotNullChildByType(ATTRIBUTE_NAME);
  }

  @Override
  @Nullable
  public PsiElement getAttributeValue() {
    return findChildByType(ATTRIBUTE_VALUE);
  }

  @Override
  @Nullable
  public String getName() {
    return BBCodePsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String name) {
    return BBCodePsiImplUtil.setName(this, name);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return BBCodePsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public int getTextOffset() {
    return BBCodePsiImplUtil.getTextOffset(this);
  }

  @Override
  @Nullable
  public String getValue() {
    return BBCodePsiImplUtil.getValue(this);
  }

}
