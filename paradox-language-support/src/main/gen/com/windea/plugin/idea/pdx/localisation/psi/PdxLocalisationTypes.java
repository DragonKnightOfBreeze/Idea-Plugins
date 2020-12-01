// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.localisation.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.windea.plugin.idea.pdx.localisation.psi.impl.*;

public interface PdxLocalisationTypes {

  IElementType CODE = new PdxLocalisationElementType("CODE");
  IElementType COLORFUL_TEXT = new PdxLocalisationElementType("COLORFUL_TEXT");
  IElementType ESCAPE = new PdxLocalisationElementType("ESCAPE");
  IElementType ICON = new PdxLocalisationElementType("ICON");
  IElementType LOCALE = new PdxLocalisationElementType("LOCALE");
  IElementType PROPERTY = PdxLocalisationStubElementTypes.getPropertyType("PROPERTY");
  IElementType PROPERTY_KEY = new PdxLocalisationElementType("PROPERTY_KEY");
  IElementType PROPERTY_REFERENCE = new PdxLocalisationElementType("PROPERTY_REFERENCE");
  IElementType PROPERTY_VALUE = new PdxLocalisationElementType("PROPERTY_VALUE");
  IElementType RICH_TEXT = new PdxLocalisationElementType("RICH_TEXT");
  IElementType SERIAL_NUMBER = new PdxLocalisationElementType("SERIAL_NUMBER");
  IElementType STRING = new PdxLocalisationElementType("STRING");

  IElementType BLANK = new PdxLocalisationTokenType("wregexp:\\s+");
  IElementType CODE_END = new PdxLocalisationTokenType("]");
  IElementType CODE_START = new PdxLocalisationTokenType("[");
  IElementType CODE_TEXT_TOKEN = new PdxLocalisationTokenType("CODE_TEXT_TOKEN");
  IElementType COLON = new PdxLocalisationTokenType(":");
  IElementType COLORFUL_TEXT_END = new PdxLocalisationTokenType("§!");
  IElementType COLORFUL_TEXT_START = new PdxLocalisationTokenType("§");
  IElementType COLOR_CODE = new PdxLocalisationTokenType("COLOR_CODE");
  IElementType COMMENT = new PdxLocalisationTokenType("COMMENT");
  IElementType END_OF_LINE_COMMENT = new PdxLocalisationTokenType("END_OF_LINE_COMMENT");
  IElementType ICON_END = new PdxLocalisationTokenType("£");
  IElementType ICON_ID = new PdxLocalisationTokenType("ICON_ID");
  IElementType ICON_PARAMETER = new PdxLocalisationTokenType("ICON_PARAMETER");
  IElementType ICON_START = new PdxLocalisationTokenType("ICON_START");
  IElementType INVALID_ESCAPE_TOKEN = new PdxLocalisationTokenType("INVALID_ESCAPE_TOKEN");
  IElementType LEFT_QUOTE = new PdxLocalisationTokenType("LEFT_QUOTE");
  IElementType LOCALE_ID = new PdxLocalisationTokenType("LOCALE_ID");
  IElementType NUMBER = new PdxLocalisationTokenType("NUMBER");
  IElementType PARAMETER_SEPARATOR = new PdxLocalisationTokenType("|");
  IElementType PROPERTY_KEY_ID = new PdxLocalisationTokenType("PROPERTY_KEY_ID");
  IElementType PROPERTY_REFERENCE_END = new PdxLocalisationTokenType("$");
  IElementType PROPERTY_REFERENCE_ID = new PdxLocalisationTokenType("PROPERTY_REFERENCE_ID");
  IElementType PROPERTY_REFERENCE_PARAMETER = new PdxLocalisationTokenType("PROPERTY_REFERENCE_PARAMETER");
  IElementType PROPERTY_REFERENCE_START = new PdxLocalisationTokenType("PROPERTY_REFERENCE_START");
  IElementType RIGHT_QUOTE = new PdxLocalisationTokenType("\"");
  IElementType ROOT_COMMENT = new PdxLocalisationTokenType("ROOT_COMMENT");
  IElementType SERIAL_NUMBER_END = new PdxLocalisationTokenType("%");
  IElementType SERIAL_NUMBER_ID = new PdxLocalisationTokenType("SERIAL_NUMBER_ID");
  IElementType SERIAL_NUMBER_START = new PdxLocalisationTokenType("SERIAL_NUMBER_START");
  IElementType STRING_TOKEN = new PdxLocalisationTokenType("STRING_TOKEN");
  IElementType VALID_ESCAPE_TOKEN = new PdxLocalisationTokenType("VALID_ESCAPE_TOKEN");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == CODE) {
        return new PdxLocalisationCodeImpl(node);
      }
      else if (type == COLORFUL_TEXT) {
        return new PdxLocalisationColorfulTextImpl(node);
      }
      else if (type == ESCAPE) {
        return new PdxLocalisationEscapeImpl(node);
      }
      else if (type == ICON) {
        return new PdxLocalisationIconImpl(node);
      }
      else if (type == LOCALE) {
        return new PdxLocalisationLocaleImpl(node);
      }
      else if (type == PROPERTY) {
        return new PdxLocalisationPropertyImpl(node);
      }
      else if (type == PROPERTY_KEY) {
        return new PdxLocalisationPropertyKeyImpl(node);
      }
      else if (type == PROPERTY_REFERENCE) {
        return new PdxLocalisationPropertyReferenceImpl(node);
      }
      else if (type == PROPERTY_VALUE) {
        return new PdxLocalisationPropertyValueImpl(node);
      }
      else if (type == SERIAL_NUMBER) {
        return new PdxLocalisationSerialNumberImpl(node);
      }
      else if (type == STRING) {
        return new PdxLocalisationStringImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
