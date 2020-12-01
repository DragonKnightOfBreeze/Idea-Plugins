// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.localisation.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface PdxLocalisationIcon extends PdxLocalisationRichText, PdxLocalisationNamedElement {

  @Nullable
  PdxLocalisationRichText getRichText();

  @Nullable
  PsiElement getIconId();

  @Nullable
  PsiElement getIconParameter();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  int getTextOffset();

  //WARNING: getReference(...) is skipped
  //matching getReference(PdxLocalisationIcon, ...)
  //methods are not found in PdxLocalisationPsiImplUtil

}
