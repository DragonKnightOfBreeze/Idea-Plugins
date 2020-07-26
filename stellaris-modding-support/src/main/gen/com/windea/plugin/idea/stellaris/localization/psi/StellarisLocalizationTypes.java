// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.windea.plugin.idea.stellaris.localization.psi.impl.*;

public interface StellarisLocalizationTypes {

  IElementType CODE = new StellarisLocalizationElementType("CODE");
  IElementType COLORFUL_TEXT = new StellarisLocalizationElementType("COLORFUL_TEXT");
  IElementType ESCAPE = new StellarisLocalizationElementType("ESCAPE");
  IElementType ICON = new StellarisLocalizationElementType("ICON");
  IElementType ICON_NAME = new StellarisLocalizationElementType("ICON_NAME");
  IElementType LOCALE = new StellarisLocalizationElementType("LOCALE");
  IElementType PROPERTY = new StellarisLocalizationElementType("PROPERTY");
  IElementType PROPERTY_KEY = new StellarisLocalizationElementType("PROPERTY_KEY");
  IElementType PROPERTY_REFERENCE = new StellarisLocalizationElementType("PROPERTY_REFERENCE");
  IElementType PROPERTY_VALUE = new StellarisLocalizationElementType("PROPERTY_VALUE");
  IElementType RICH_TEXT = new StellarisLocalizationElementType("RICH_TEXT");
  IElementType SERIAL_NUMBER = new StellarisLocalizationElementType("SERIAL_NUMBER");
  IElementType STRING = new StellarisLocalizationElementType("STRING");

  IElementType CODE_END = new StellarisLocalizationTokenType("]");
  IElementType CODE_START = new StellarisLocalizationTokenType("[");
  IElementType CODE_TEXT = new StellarisLocalizationTokenType("CODE_TEXT");
  IElementType COLON = new StellarisLocalizationTokenType(":");
  IElementType COLORFUL_TEXT_END = new StellarisLocalizationTokenType("§!");
  IElementType COLORFUL_TEXT_ID = new StellarisLocalizationTokenType("COLORFUL_TEXT_ID");
  IElementType COLORFUL_TEXT_START = new StellarisLocalizationTokenType("§");
  IElementType COMMENT = new StellarisLocalizationTokenType("COMMENT");
  IElementType END_OF_LINE_COMMENT = new StellarisLocalizationTokenType("END_OF_LINE_COMMENT");
  IElementType ICON_END = new StellarisLocalizationTokenType("£");
  IElementType ICON_START = new StellarisLocalizationTokenType("ICON_START");
  IElementType INVALID_ESCAPE_TOKEN = new StellarisLocalizationTokenType("INVALID_ESCAPE_TOKEN");
  IElementType LEFT_QUOTE = new StellarisLocalizationTokenType("LEFT_QUOTE");
  IElementType LOCALE_ID = new StellarisLocalizationTokenType("LOCALE_ID");
  IElementType NUMBER = new StellarisLocalizationTokenType("NUMBER");
  IElementType PROPERTY_KEY_ID = new StellarisLocalizationTokenType("PROPERTY_KEY_ID");
  IElementType PROPERTY_REFERENCE_END = new StellarisLocalizationTokenType("$");
  IElementType PROPERTY_REFERENCE_PARAMETER = new StellarisLocalizationTokenType("PROPERTY_REFERENCE_PARAMETER");
  IElementType PROPERTY_REFERENCE_SEPARATOR = new StellarisLocalizationTokenType("|");
  IElementType PROPERTY_REFERENCE_START = new StellarisLocalizationTokenType("PROPERTY_REFERENCE_START");
  IElementType RIGHT_QUOTE = new StellarisLocalizationTokenType("\"");
  IElementType ROOT_COMMENT = new StellarisLocalizationTokenType("ROOT_COMMENT");
  IElementType SERIAL_NUMBER_END = new StellarisLocalizationTokenType("%");
  IElementType SERIAL_NUMBER_ID = new StellarisLocalizationTokenType("SERIAL_NUMBER_ID");
  IElementType SERIAL_NUMBER_START = new StellarisLocalizationTokenType("SERIAL_NUMBER_START");
  IElementType STRING_TOKEN = new StellarisLocalizationTokenType("STRING_TOKEN");
  IElementType VALID_ESCAPE_TOKEN = new StellarisLocalizationTokenType("VALID_ESCAPE_TOKEN");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == CODE) {
        return new StellarisLocalizationCodeImpl(node);
      }
      else if (type == COLORFUL_TEXT) {
        return new StellarisLocalizationColorfulTextImpl(node);
      }
      else if (type == ESCAPE) {
        return new StellarisLocalizationEscapeImpl(node);
      }
      else if (type == ICON) {
        return new StellarisLocalizationIconImpl(node);
      }
      else if (type == ICON_NAME) {
        return new StellarisLocalizationIconNameImpl(node);
      }
      else if (type == LOCALE) {
        return new StellarisLocalizationLocaleImpl(node);
      }
      else if (type == PROPERTY) {
        return new StellarisLocalizationPropertyImpl(node);
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
      else if (type == STRING) {
        return new StellarisLocalizationStringImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
