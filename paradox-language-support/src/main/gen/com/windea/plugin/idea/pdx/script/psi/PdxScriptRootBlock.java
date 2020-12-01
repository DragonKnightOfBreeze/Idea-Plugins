// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;

public interface PdxScriptRootBlock extends PdxScriptBlock {

  @NotNull
  List<PdxScriptProperty> getPropertyList();

  @NotNull
  List<PdxScriptValue> getValueList();

  @NotNull
  List<PdxScriptVariable> getVariableList();

  boolean isEmpty();

  boolean isNotEmpty();

  boolean isObject();

  boolean isArray();

  @NotNull
  List<PsiElement> getComponents();

  @NotNull
  Icon getIcon(@IconFlags int flags);

}
