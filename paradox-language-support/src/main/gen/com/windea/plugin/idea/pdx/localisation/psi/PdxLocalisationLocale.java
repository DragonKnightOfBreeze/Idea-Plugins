// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.localisation.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Iconable.IconFlags;
import com.windea.plugin.idea.pdx.PdxLocale;
import javax.swing.Icon;

public interface PdxLocalisationLocale extends PdxLocalisationNamedElement {

  @NotNull
  PsiElement getLocaleId();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @NotNull
  PsiElement getNameIdentifier();

  @NotNull
  Icon getIcon(@IconFlags int flags);

  //WARNING: getReference(...) is skipped
  //matching getReference(PdxLocalisationLocale, ...)
  //methods are not found in PdxLocalisationPsiImplUtil

  @Nullable
  PdxLocale getPdxLocale();

}
