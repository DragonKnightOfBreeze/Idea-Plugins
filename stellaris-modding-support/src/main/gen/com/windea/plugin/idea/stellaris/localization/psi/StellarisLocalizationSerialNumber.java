// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.stellaris.StellarisSerialNumber;

public interface StellarisLocalizationSerialNumber extends StellarisLocalizationRichText, StellarisLocalizationNamedElement {

  @Nullable
  PsiElement getSerialNumberId();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  int getTextOffset();

  @Nullable
  StellarisSerialNumber getStellarisSerialNumber();

  //WARNING: getReference(...) is skipped
  //matching getReference(StellarisLocalizationSerialNumber, ...)
  //methods are not found in StellarisLocalizationPsiImplUtil

}
