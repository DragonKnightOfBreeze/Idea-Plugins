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
import com.windea.plugin.idea.pdx.script.reference.PdxScriptStringAsPropertyPsiReference;

public class PdxScriptStringImpl extends PdxScriptStringValueImpl implements PdxScriptString {

  public PdxScriptStringImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PdxScriptVisitor visitor) {
    visitor.visitString(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PdxScriptVisitor) accept((PdxScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getQuotedStringToken() {
    return findChildByType(QUOTED_STRING_TOKEN);
  }

  @Override
  @Nullable
  public PsiElement getStringToken() {
    return findChildByType(STRING_TOKEN);
  }

  @Override
  @NotNull
  public String getValue() {
    return PdxScriptPsiImplUtil.getValue(this);
  }

  @Override
  @Nullable
  public PdxScriptStringAsPropertyPsiReference getReference() {
    return PdxScriptPsiImplUtil.getReference(this);
  }

}
