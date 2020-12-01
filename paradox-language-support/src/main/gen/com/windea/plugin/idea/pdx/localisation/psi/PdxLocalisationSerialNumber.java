// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.localisation.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.pdx.PdxSerialNumber;

public interface PdxLocalisationSerialNumber extends PdxLocalisationRichText, PdxLocalisationNamedElement {

  @Nullable
  PsiElement getSerialNumberId();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  int getTextOffset();

  @Nullable
  PdxSerialNumber getPdxSerialNumber();

  //WARNING: getReference(...) is skipped
  //matching getReference(PdxLocalisationSerialNumber, ...)
  //methods are not found in PdxLocalisationPsiImplUtil

}
