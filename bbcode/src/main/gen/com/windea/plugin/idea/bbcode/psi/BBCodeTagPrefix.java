// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.bbcode.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BBCodeTagPrefix extends PsiElement {

  @NotNull
  List<BBCodeAttribute> getAttributeList();

  @Nullable
  PsiElement getAttributeValue();

  @Nullable
  PsiElement getTagName();

}
