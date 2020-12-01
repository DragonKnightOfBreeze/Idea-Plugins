// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.localisation.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.pdx.localisation.psi.PdxLocalisationTypes.*;
import com.windea.plugin.idea.pdx.localisation.psi.*;
import com.windea.plugin.idea.pdx.PdxColor;

public class PdxLocalisationColorfulTextImpl extends PdxLocalisationNamedElementImpl implements PdxLocalisationColorfulText {

  public PdxLocalisationColorfulTextImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PdxLocalisationVisitor visitor) {
    visitor.visitColorfulText(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PdxLocalisationVisitor) accept((PdxLocalisationVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<PdxLocalisationRichText> getRichTextList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PdxLocalisationRichText.class);
  }

  @Override
  @Nullable
  public PsiElement getColorCode() {
    return findChildByType(COLOR_CODE);
  }

  @Override
  @Nullable
  public String getName() {
    return PdxLocalisationPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String name) {
    return PdxLocalisationPsiImplUtil.setName(this, name);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return PdxLocalisationPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public int getTextOffset() {
    return PdxLocalisationPsiImplUtil.getTextOffset(this);
  }

  @Override
  @Nullable
  public PdxColor getPdxColor() {
    return PdxLocalisationPsiImplUtil.getPdxColor(this);
  }

}
