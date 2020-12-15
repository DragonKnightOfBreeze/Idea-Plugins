// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.paradox.localisation.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ParadoxLocalisationIcon extends ParadoxLocalisationRichText, ParadoxLocalisationNamedElement {

  @Nullable
  ParadoxLocalisationRichText getRichText();

  @Nullable
  PsiElement getIconId();

  @Nullable
  PsiElement getIconParameter();

  @NotNull
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  int getTextOffset();

  //WARNING: getReference(...) is skipped
  //matching getReference(ParadoxLocalisationIcon, ...)
  //methods are not found in ParadoxLocalisationPsiImplUtil

}
