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
import com.windea.plugin.idea.stellaris.script.reference.StellarisScriptStringAsPropertyPsiReference;

public class StellarisScriptStringImpl extends StellarisScriptStringValueImpl implements StellarisScriptString {

  public StellarisScriptStringImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisScriptVisitor visitor) {
    visitor.visitString(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisScriptVisitor) accept((StellarisScriptVisitor)visitor);
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
    return StellarisScriptPsiImplUtil.getValue(this);
  }

  @Override
  @Nullable
  public StellarisScriptStringAsPropertyPsiReference getReference() {
    return StellarisScriptPsiImplUtil.getReference(this);
  }

}
