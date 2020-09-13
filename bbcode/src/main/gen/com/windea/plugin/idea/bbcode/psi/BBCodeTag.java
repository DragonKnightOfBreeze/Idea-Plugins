// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.bbcode.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;

public interface BBCodeTag extends BBCodeNamedElement {

  @NotNull
  List<BBCodeTag> getTagList();

  @NotNull
  BBCodeTagPrefix getTagPrefix();

  @Nullable
  BBCodeTagSuffix getTagSuffix();

  @NotNull
  List<BBCodeText> getTextList();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  int getTextOffset();

  @Nullable
  Icon getIcon(@IconFlags int flags);

}
