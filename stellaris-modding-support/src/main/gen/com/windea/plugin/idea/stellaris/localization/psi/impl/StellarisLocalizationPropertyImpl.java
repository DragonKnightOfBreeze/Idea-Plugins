// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*;
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationPropertyStub;
import com.windea.plugin.idea.stellaris.localization.psi.*;
import com.intellij.openapi.util.Iconable.IconFlags;
import com.windea.plugin.idea.stellaris.StellarisLocale;
import javax.swing.Icon;
import com.intellij.psi.stubs.IStubElementType;

public class StellarisLocalizationPropertyImpl extends StellarisLocalizationStubElementImpl<StellarisLocalizationPropertyStub> implements StellarisLocalizationProperty {

  public StellarisLocalizationPropertyImpl(@NotNull StellarisLocalizationPropertyStub stub, @Nullable IStubElementType<?, ?> nodeType) {
    super(stub, nodeType);
  }

  public StellarisLocalizationPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisLocalizationVisitor visitor) {
    visitor.visitProperty(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisLocalizationVisitor) accept((StellarisLocalizationVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public StellarisLocalizationPropertyKey getPropertyKey() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, StellarisLocalizationPropertyKey.class));
  }

  @Override
  @Nullable
  public StellarisLocalizationPropertyValue getPropertyValue() {
    return PsiTreeUtil.getChildOfType(this, StellarisLocalizationPropertyValue.class);
  }

  @Override
  @Nullable
  public PsiElement getNumber() {
    return findChildByType(NUMBER);
  }

  @Override
  @Nullable
  public String getName() {
    return StellarisLocalizationPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String name) {
    return StellarisLocalizationPsiImplUtil.setName(this, name);
  }

  @Override
  @Nullable
  public Icon getIcon(@IconFlags int flags) {
    return StellarisLocalizationPsiImplUtil.getIcon(this, flags);
  }

  @Override
  @NotNull
  public String getKey() {
    return StellarisLocalizationPsiImplUtil.getKey(this);
  }

  @Override
  @NotNull
  public String getValue() {
    return StellarisLocalizationPsiImplUtil.getValue(this);
  }

  @Override
  @Nullable
  public StellarisLocale getStellarisLocale() {
    return StellarisLocalizationPsiImplUtil.getStellarisLocale(this);
  }

}
