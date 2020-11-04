// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;

public interface StellarisScriptItem extends PsiElement {

  @Nullable
  StellarisScriptBlock getBlock();

  @Nullable
  StellarisScriptBoolean getBoolean();

  @Nullable
  StellarisScriptColor getColor();

  @Nullable
  StellarisScriptNumber getNumber();

  @Nullable
  StellarisScriptString getString();

  @Nullable
  Icon getIcon(@IconFlags int flags);

}
