// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.localisation.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.pdx.PdxColor;

public interface PdxLocalisationColorfulText extends PdxLocalisationRichText, PdxLocalisationNamedElement {

  @NotNull
  List<PdxLocalisationRichText> getRichTextList();

  @Nullable
  PsiElement getColorCode();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  int getTextOffset();

  @Nullable
  PdxColor getPdxColor();

  //WARNING: getReference(...) is skipped
  //matching getReference(PdxLocalisationColorfulText, ...)
  //methods are not found in PdxLocalisationPsiImplUtil

}
