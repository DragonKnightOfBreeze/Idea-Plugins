// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.windea.plugin.idea.stellaris.localization.psi.impl.*;

public interface StellarisLocalizationTypes {

  IElementType CODE = new StellarisLocalizationElementType("CODE");
  IElementType COLORFUL_TEXT = new StellarisLocalizationElementType("COLORFUL_TEXT");
  IElementType COLOR_CODE = new StellarisLocalizationElementType("COLOR_CODE");
  IElementType ICON = new StellarisLocalizationElementType("ICON");
  IElementType PLAIN_TEXT = new StellarisLocalizationElementType("PLAIN_TEXT");
  IElementType PROPERTY = new StellarisLocalizationElementType("PROPERTY");
  IElementType PROPERTY_HEADER = new StellarisLocalizationElementType("PROPERTY_HEADER");
  IElementType PROPERTY_KEY = new StellarisLocalizationElementType("PROPERTY_KEY");
  IElementType PROPERTY_REFERENCE = new StellarisLocalizationElementType("PROPERTY_REFERENCE");
  IElementType PROPERTY_VALUE = new StellarisLocalizationElementType("PROPERTY_VALUE");
  IElementType RICH_TEXT = new StellarisLocalizationElementType("RICH_TEXT");
  IElementType SERIAL_NUMBER = new StellarisLocalizationElementType("SERIAL_NUMBER");

  IElementType CODE_END = new StellarisLocalizationTokenType("]");
  IElementType CODE_START = new StellarisLocalizationTokenType("[");
  IElementType CODE_TEXT = new StellarisLocalizationTokenType("CODE_TEXT");
  IElementType COLON = new StellarisLocalizationTokenType(":");
  IElementType COLORFUL_TEXT_CODE = new StellarisLocalizationTokenType("COLORFUL_TEXT_CODE");
  IElementType COLORFUL_TEXT_END = new StellarisLocalizationTokenType("§");
  IElementType COLORFUL_TEXT_START = new StellarisLocalizationTokenType("COLORFUL_TEXT_START");
  IElementType COMMENT = new StellarisLocalizationTokenType("COMMENT");
  IElementType HEADER_TOKEN = new StellarisLocalizationTokenType("HEADER_TOKEN");
  IElementType ICON_END = new StellarisLocalizationTokenType("£");
  IElementType ICON_START = new StellarisLocalizationTokenType("ICON_START");
  IElementType ICON_TEXT = new StellarisLocalizationTokenType("ICON_TEXT");
  IElementType KEY_TOKEN = new StellarisLocalizationTokenType("KEY_TOKEN");
  IElementType LEFT_QUOTE = new StellarisLocalizationTokenType("LEFT_QUOTE");
  IElementType NUMBER = new StellarisLocalizationTokenType("NUMBER");
  IElementType PROPERTY_REFERENCE_END = new StellarisLocalizationTokenType("$");
  IElementType PROPERTY_REFERENCE_START = new StellarisLocalizationTokenType("PROPERTY_REFERENCE_START");
  IElementType RIGHT_QUOTE = new StellarisLocalizationTokenType("\"");
  IElementType ROOT_COMMENT = new StellarisLocalizationTokenType("ROOT_COMMENT");
  IElementType SERIAL_NUMBER_CODE = new StellarisLocalizationTokenType("SERIAL_NUMBER_CODE");
  IElementType SERIAL_NUMBER_END = new StellarisLocalizationTokenType("%");
  IElementType SERIAL_NUMBER_START = new StellarisLocalizationTokenType("SERIAL_NUMBER_START");
  IElementType VALUE_TOKEN = new StellarisLocalizationTokenType("VALUE_TOKEN");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == CODE) {
        return new StellarisLocalizationCodeImpl(node);
      }
      else if (type == COLORFUL_TEXT) {
        return new StellarisLocalizationColorfulTextImpl(node);
      }
      else if (type == COLOR_CODE) {
        return new StellarisLocalizationColorCodeImpl(node);
      }
      else if (type == ICON) {
        return new StellarisLocalizationIconImpl(node);
      }
      else if (type == PLAIN_TEXT) {
        return new StellarisLocalizationPlainTextImpl(node);
      }
      else if (type == PROPERTY) {
        return new StellarisLocalizationPropertyImpl(node);
      }
      else if (type == PROPERTY_HEADER) {
        return new StellarisLocalizationPropertyHeaderImpl(node);
      }
      else if (type == PROPERTY_KEY) {
        return new StellarisLocalizationPropertyKeyImpl(node);
      }
      else if (type == PROPERTY_REFERENCE) {
        return new StellarisLocalizationPropertyReferenceImpl(node);
      }
      else if (type == PROPERTY_VALUE) {
        return new StellarisLocalizationPropertyValueImpl(node);
      }
      else if (type == RICH_TEXT) {
        return new StellarisLocalizationRichTextImpl(node);
      }
      else if (type == SERIAL_NUMBER) {
        return new StellarisLocalizationSerialNumberImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
