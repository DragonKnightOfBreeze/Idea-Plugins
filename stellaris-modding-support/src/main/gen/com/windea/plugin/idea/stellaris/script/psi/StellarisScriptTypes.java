// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.windea.plugin.idea.stellaris.script.psi.impl.*;

public interface StellarisScriptTypes {

  IElementType BOOLEAN_LITERAL = new StellarisScriptElementType("BOOLEAN_LITERAL");
  IElementType LIST = new StellarisScriptElementType("LIST");
  IElementType NUMBER_LITERAL = new StellarisScriptElementType("NUMBER_LITERAL");
  IElementType PROPERTY = new StellarisScriptElementType("PROPERTY");
  IElementType PROPERTY_KEY = new StellarisScriptElementType("PROPERTY_KEY");
  IElementType PROPERTY_SEPARATOR = new StellarisScriptElementType("PROPERTY_SEPARATOR");
  IElementType PROPERTY_VALUE = new StellarisScriptElementType("PROPERTY_VALUE");
  IElementType STRING_LITERAL = new StellarisScriptElementType("STRING_LITERAL");
  IElementType TEXT = new StellarisScriptElementType("TEXT");
  IElementType VARIABLE_DEFINITION = new StellarisScriptElementType("VARIABLE_DEFINITION");
  IElementType VARIABLE_DEFINITION_SEPARATOR = new StellarisScriptElementType("VARIABLE_DEFINITION_SEPARATOR");
  IElementType VARIABLE_NAME = new StellarisScriptElementType("VARIABLE_NAME");
  IElementType VARIABLE_REFERENCE = new StellarisScriptElementType("VARIABLE_REFERENCE");
  IElementType VARIABLE_VALUE = new StellarisScriptElementType("VARIABLE_VALUE");

  IElementType BOOLEAN = new StellarisScriptTokenType("BOOLEAN");
  IElementType COMMENT = new StellarisScriptTokenType("COMMENT");
  IElementType END_OF_LINE_COMMENT = new StellarisScriptTokenType("END_OF_LINE_COMMENT");
  IElementType EQUAL_SIGN = new StellarisScriptTokenType("=");
  IElementType GE_SIGN = new StellarisScriptTokenType(">=");
  IElementType GT_SIGN = new StellarisScriptTokenType(">");
  IElementType KEY_TOKEN = new StellarisScriptTokenType("KEY_TOKEN");
  IElementType LEFT_BRACE = new StellarisScriptTokenType("{");
  IElementType LE_SIGN = new StellarisScriptTokenType("<=");
  IElementType LT_SIGN = new StellarisScriptTokenType("<");
  IElementType NUMBER = new StellarisScriptTokenType("NUMBER");
  IElementType RIGHT_BRACE = new StellarisScriptTokenType("}");
  IElementType STRING = new StellarisScriptTokenType("STRING");
  IElementType UNQUOTED_STRING = new StellarisScriptTokenType("UNQUOTED_STRING");
  IElementType VARIABLE_NAME_TOKEN = new StellarisScriptTokenType("VARIABLE_NAME_TOKEN");
  IElementType VARIABLE_REFERENCE_TOKEN = new StellarisScriptTokenType("VARIABLE_REFERENCE_TOKEN");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == BOOLEAN_LITERAL) {
        return new StellarisScriptBooleanLiteralImpl(node);
      }
      else if (type == LIST) {
        return new StellarisScriptListImpl(node);
      }
      else if (type == NUMBER_LITERAL) {
        return new StellarisScriptNumberLiteralImpl(node);
      }
      else if (type == PROPERTY) {
        return new StellarisScriptPropertyImpl(node);
      }
      else if (type == PROPERTY_KEY) {
        return new StellarisScriptPropertyKeyImpl(node);
      }
      else if (type == PROPERTY_SEPARATOR) {
        return new StellarisScriptPropertySeparatorImpl(node);
      }
      else if (type == PROPERTY_VALUE) {
        return new StellarisScriptPropertyValueImpl(node);
      }
      else if (type == STRING_LITERAL) {
        return new StellarisScriptStringLiteralImpl(node);
      }
      else if (type == TEXT) {
        return new StellarisScriptTextImpl(node);
      }
      else if (type == VARIABLE_DEFINITION) {
        return new StellarisScriptVariableDefinitionImpl(node);
      }
      else if (type == VARIABLE_DEFINITION_SEPARATOR) {
        return new StellarisScriptVariableDefinitionSeparatorImpl(node);
      }
      else if (type == VARIABLE_NAME) {
        return new StellarisScriptVariableNameImpl(node);
      }
      else if (type == VARIABLE_REFERENCE) {
        return new StellarisScriptVariableReferenceImpl(node);
      }
      else if (type == VARIABLE_VALUE) {
        return new StellarisScriptVariableValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
