// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.windea.plugin.idea.stellaris.script.psi.*;

public class StellarisScriptArrayImpl extends ASTWrapperPsiElement implements StellarisScriptArray {

  public StellarisScriptArrayImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisScriptVisitor visitor) {
    visitor.visitArray(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisScriptVisitor) accept((StellarisScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<StellarisScriptString> getStringList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StellarisScriptString.class);
  }

  @Override
  @NotNull
  public List<PsiElement> getComponents() {
    return StellarisScriptPsiImplUtil.getComponents(this);
  }

}
