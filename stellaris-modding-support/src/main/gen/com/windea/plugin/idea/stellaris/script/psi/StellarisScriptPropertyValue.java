// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface StellarisScriptPropertyValue extends PsiElement {

  @Nullable
  StellarisScriptArray getArray();

  @Nullable
  StellarisScriptBoolean getBoolean();

  @Nullable
  StellarisScriptNumber getNumber();

  @Nullable
  StellarisScriptObject getObject();

  @Nullable
  StellarisScriptString getString();

  @Nullable
  StellarisScriptVariableReference getVariableReference();

}
