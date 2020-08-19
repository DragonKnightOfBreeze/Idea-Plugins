// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.bbcode.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.bbcode.psi.BBCodeTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.windea.plugin.idea.bbcode.psi.*;

public class BBCodeTextImpl extends ASTWrapperPsiElement implements BBCodeText {

  public BBCodeTextImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BBCodeVisitor visitor) {
    visitor.visitText(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BBCodeVisitor) accept((BBCodeVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getTextToken() {
    return findNotNullChildByType(TEXT_TOKEN);
  }

  @Override
  @Nullable
  public String getValue() {
    return BBCodePsiImplUtil.getValue(this);
  }

}
