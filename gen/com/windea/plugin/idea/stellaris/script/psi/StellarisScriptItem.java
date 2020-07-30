// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;

public interface StellarisScriptItem extends PsiLiteralValue {

  @Nullable
  StellarisScriptBoolean getBoolean();

  @Nullable
  StellarisScriptColor getColor();

  @Nullable
  StellarisScriptNumber getNumber();

  @Nullable
  StellarisScriptString getString();

  //WARNING: getIcon(...) is skipped
  //matching getIcon(StellarisScriptItem, ...)
  //methods are not found in StellarisScriptPsiImplUtil

  //WARNING: getValue(...) is skipped
  //matching getValue(StellarisScriptItem, ...)
  //methods are not found in StellarisScriptPsiImplUtil

}
