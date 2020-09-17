// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.sbtext.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.windea.plugin.idea.sbtext.psi.impl.*;

public interface SbTextTypes {

  IElementType COLORFUL_TEXT = new SbTextElementType("COLORFUL_TEXT");
  IElementType COLOR_MARKER = new SbTextElementType("COLOR_MARKER");
  IElementType COLOR_RESET_MARKER = new SbTextElementType("COLOR_RESET_MARKER");
  IElementType ESCAPE = new SbTextElementType("ESCAPE");
  IElementType RICH_TEXT = new SbTextElementType("RICH_TEXT");
  IElementType STRING = new SbTextElementType("STRING");

  IElementType COLOR_CODE = new SbTextTokenType("COLOR_CODE");
  IElementType COLOR_MARKER_END = new SbTextTokenType(";");
  IElementType COLOR_MARKER_START = new SbTextTokenType("^");
  IElementType COLOR_RESET_MARKER_TOKEN = new SbTextTokenType("^reset;");
  IElementType INVALID_ESCAPE_TOKEN = new SbTextTokenType("INVALID_ESCAPE_TOKEN");
  IElementType TEXT_TOKEN = new SbTextTokenType("TEXT_TOKEN");
  IElementType VALID_ESCAPE_TOKEN = new SbTextTokenType("VALID_ESCAPE_TOKEN");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == COLORFUL_TEXT) {
        return new SbTextColorfulTextImpl(node);
      }
      else if (type == COLOR_MARKER) {
        return new SbTextColorMarkerImpl(node);
      }
      else if (type == COLOR_RESET_MARKER) {
        return new SbTextColorResetMarkerImpl(node);
      }
      else if (type == ESCAPE) {
        return new SbTextEscapeImpl(node);
      }
      else if (type == RICH_TEXT) {
        return new SbTextRichTextImpl(node);
      }
      else if (type == STRING) {
        return new SbTextStringImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
