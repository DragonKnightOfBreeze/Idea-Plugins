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

public class StellarisScriptPropertyImpl extends StellarisScriptNamedElementImpl implements StellarisScriptProperty {

  public StellarisScriptPropertyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisScriptVisitor visitor) {
    visitor.visitProperty(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisScriptVisitor) accept((StellarisScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public StellarisScriptPropertyKey getPropertyKey() {
    return findNotNullChildByClass(StellarisScriptPropertyKey.class);
  }

  @Override
  @Nullable
  public StellarisScriptPropertySeparator getPropertySeparator() {
    return findChildByClass(StellarisScriptPropertySeparator.class);
  }

  @Override
  @Nullable
  public StellarisScriptPropertyValue getPropertyValue() {
    return findChildByClass(StellarisScriptPropertyValue.class);
  }

}
