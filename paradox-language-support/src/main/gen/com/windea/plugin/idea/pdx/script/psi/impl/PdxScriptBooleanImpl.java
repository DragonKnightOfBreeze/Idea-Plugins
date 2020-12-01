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

public class PdxScriptBooleanImpl extends PdxScriptValueImpl implements PdxScriptBoolean {

  public PdxScriptBooleanImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PdxScriptVisitor visitor) {
    visitor.visitBoolean(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PdxScriptVisitor) accept((PdxScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getBooleanToken() {
    return notNullChild(findChildByType(BOOLEAN_TOKEN));
  }

  @Override
  @NotNull
  public String getValue() {
    return PdxScriptPsiImplUtil.getValue(this);
  }

}
