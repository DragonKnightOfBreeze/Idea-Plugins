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

public class StellarisScriptVariableDefinitionImpl extends StellarisScriptNamedElementImpl implements StellarisScriptVariableDefinition {

  public StellarisScriptVariableDefinitionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisScriptVisitor visitor) {
    visitor.visitVariableDefinition(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisScriptVisitor) accept((StellarisScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public StellarisScriptVariableDefinitionSeparator getVariableDefinitionSeparator() {
    return findChildByClass(StellarisScriptVariableDefinitionSeparator.class);
  }

  @Override
  @NotNull
  public StellarisScriptVariableName getVariableName() {
    return findNotNullChildByClass(StellarisScriptVariableName.class);
  }

  @Override
  @Nullable
  public StellarisScriptVariableValue getVariableValue() {
    return findChildByClass(StellarisScriptVariableValue.class);
  }

}
