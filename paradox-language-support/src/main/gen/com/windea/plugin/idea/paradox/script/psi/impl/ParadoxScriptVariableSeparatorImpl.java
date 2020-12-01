// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.paradox.script.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.paradox.script.psi.ParadoxScriptTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.windea.plugin.idea.paradox.script.psi.*;

public class ParadoxScriptVariableSeparatorImpl extends ASTWrapperPsiElement implements ParadoxScriptVariableSeparator {

  public ParadoxScriptVariableSeparatorImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ParadoxScriptVisitor visitor) {
    visitor.visitVariableSeparator(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ParadoxScriptVisitor) accept((ParadoxScriptVisitor)visitor);
    else super.accept(visitor);
  }

}
