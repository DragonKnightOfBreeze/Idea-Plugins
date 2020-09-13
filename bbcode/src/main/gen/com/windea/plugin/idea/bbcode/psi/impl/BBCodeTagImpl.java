// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.bbcode.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.bbcode.psi.BBCodeTypes.*;
import com.windea.plugin.idea.bbcode.psi.*;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;

public class BBCodeTagImpl extends BBCodeNamedElementImpl implements BBCodeTag {

  public BBCodeTagImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BBCodeVisitor visitor) {
    visitor.visitTag(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BBCodeVisitor) accept((BBCodeVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<BBCodeTag> getTagList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, BBCodeTag.class);
  }

  @Override
  @NotNull
  public BBCodeTagPrefix getTagPrefix() {
    return findNotNullChildByClass(BBCodeTagPrefix.class);
  }

  @Override
  @Nullable
  public BBCodeTagSuffix getTagSuffix() {
    return findChildByClass(BBCodeTagSuffix.class);
  }

  @Override
  @NotNull
  public List<BBCodeText> getTextList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, BBCodeText.class);
  }

  @Override
  @Nullable
  public String getName() {
    return BBCodePsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String name) {
    return BBCodePsiImplUtil.setName(this, name);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return BBCodePsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public int getTextOffset() {
    return BBCodePsiImplUtil.getTextOffset(this);
  }

  @Override
  @Nullable
  public Icon getIcon(@IconFlags int flags) {
    return BBCodePsiImplUtil.getIcon(this, flags);
  }

}
