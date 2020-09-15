// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.sbtext.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.sbtext.psi.SbTextTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.windea.plugin.idea.sbtext.psi.*;

public class SbTextEscapeImpl extends ASTWrapperPsiElement implements SbTextEscape {

  public SbTextEscapeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SbTextVisitor visitor) {
    visitor.visitEscape(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SbTextVisitor) accept((SbTextVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getInvalidEscapeToken() {
    return findChildByType(INVALID_ESCAPE_TOKEN);
  }

  @Override
  @Nullable
  public PsiElement getValidEscapeToken() {
    return findChildByType(VALID_ESCAPE_TOKEN);
  }

}
