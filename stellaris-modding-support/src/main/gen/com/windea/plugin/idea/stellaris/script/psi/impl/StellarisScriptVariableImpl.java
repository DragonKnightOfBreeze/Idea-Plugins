// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*;
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptVariableStub;
import com.windea.plugin.idea.stellaris.script.psi.*;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;
import com.intellij.psi.stubs.IStubElementType;

public class StellarisScriptVariableImpl extends StellarisScriptStubElementImpl<StellarisScriptVariableStub> implements StellarisScriptVariable {

  public StellarisScriptVariableImpl(@NotNull StellarisScriptVariableStub stub, @Nullable IStubElementType<?, ?> nodeType) {
    super(stub, nodeType);
  }

  public StellarisScriptVariableImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisScriptVisitor visitor) {
    visitor.visitVariable(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisScriptVisitor) accept((StellarisScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public StellarisScriptVariableName getVariableName() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, StellarisScriptVariableName.class));
  }

  @Override
  @Nullable
  public StellarisScriptVariableSeparator getVariableSeparator() {
    return PsiTreeUtil.getChildOfType(this, StellarisScriptVariableSeparator.class);
  }

  @Override
  @Nullable
  public StellarisScriptVariableValue getVariableValue() {
    return PsiTreeUtil.getChildOfType(this, StellarisScriptVariableValue.class);
  }

  @Override
  @Nullable
  public String getName() {
    return StellarisScriptPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String name) {
    return StellarisScriptPsiImplUtil.setName(this, name);
  }

  @Override
  @NotNull
  public Icon getIcon(@IconFlags int flags) {
    return StellarisScriptPsiImplUtil.getIcon(this, flags);
  }

  @Override
  @Nullable
  public String getValue() {
    return StellarisScriptPsiImplUtil.getValue(this);
  }

}
