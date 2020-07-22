// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class StellarisScriptParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return root(b, l + 1);
  }

  /* ********************************************************** */
  // BOOLEAN
  public static boolean boolean_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "boolean_literal")) return false;
    if (!nextTokenIs(b, BOOLEAN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BOOLEAN);
    exit_section_(b, m, BOOLEAN_LITERAL, r);
    return r;
  }

  /* ********************************************************** */
  // END_OF_LINE_COMMENT | COMMENT | text | property {
  //   //recoverWhile=element_item_recover
  // }
  static boolean element_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "element_item")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, END_OF_LINE_COMMENT);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = text(b, l + 1);
    if (!r) r = element_item_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // property {
  //   //recoverWhile=element_item_recover
  // }
  private static boolean element_item_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "element_item_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = property(b, l + 1);
    r = r && element_item_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // {
  //   //recoverWhile=element_item_recover
  // }
  private static boolean element_item_3_1(PsiBuilder b, int l) {
    return true;
  }

  /* ********************************************************** */
  // element_item*
  static boolean element_items(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "element_items")) return false;
    while (true) {
      int c = current_position_(b);
      if (!element_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "element_items", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // "{" element_items "}"
  public static boolean list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list")) return false;
    if (!nextTokenIs(b, LEFT_BRACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LIST, null);
    r = consumeToken(b, LEFT_BRACE);
    p = r; // pin = 1
    r = r && report_error_(b, element_items(b, l + 1));
    r = p && consumeToken(b, RIGHT_BRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // NUMBER
  public static boolean number_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "number_literal")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    exit_section_(b, m, NUMBER_LITERAL, r);
    return r;
  }

  /* ********************************************************** */
  // property_key property_separator property_value
  public static boolean property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property")) return false;
    if (!nextTokenIs(b, KEY_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = property_key(b, l + 1);
    r = r && property_separator(b, l + 1);
    r = r && property_value(b, l + 1);
    exit_section_(b, m, PROPERTY, r);
    return r;
  }

  /* ********************************************************** */
  // KEY_TOKEN
  public static boolean property_key(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_key")) return false;
    if (!nextTokenIs(b, KEY_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEY_TOKEN);
    exit_section_(b, m, PROPERTY_KEY, r);
    return r;
  }

  /* ********************************************************** */
  // "=" | "<" | ">" | "<=" | ">="
  public static boolean property_separator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_separator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROPERTY_SEPARATOR, "<property separator>");
    r = consumeToken(b, EQUAL_SIGN);
    if (!r) r = consumeToken(b, LT_SIGN);
    if (!r) r = consumeToken(b, GT_SIGN);
    if (!r) r = consumeToken(b, LE_SIGN);
    if (!r) r = consumeToken(b, GE_SIGN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // boolean_literal | number_literal | variable_reference | string_literal | list
  public static boolean property_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROPERTY_VALUE, "<property value>");
    r = boolean_literal(b, l + 1);
    if (!r) r = number_literal(b, l + 1);
    if (!r) r = variable_reference(b, l + 1);
    if (!r) r = string_literal(b, l + 1);
    if (!r) r = list(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // root_item *
  static boolean root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root")) return false;
    while (true) {
      int c = current_position_(b);
      if (!root_item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "root", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // END_OF_LINE_COMMENT | COMMENT | variable_definition | property
  static boolean root_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_item")) return false;
    boolean r;
    r = consumeToken(b, END_OF_LINE_COMMENT);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = variable_definition(b, l + 1);
    if (!r) r = property(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // STRING | UNQUOTED_STRING
  public static boolean string_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_literal")) return false;
    if (!nextTokenIs(b, "<string literal>", STRING, UNQUOTED_STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRING_LITERAL, "<string literal>");
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, UNQUOTED_STRING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // string_literal
  public static boolean text(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "text")) return false;
    if (!nextTokenIs(b, "<text>", STRING, UNQUOTED_STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TEXT, "<text>");
    r = string_literal(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // variable_name variable_definition_separator variable_value
  public static boolean variable_definition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_definition")) return false;
    if (!nextTokenIs(b, VARIABLE_NAME_TOKEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_DEFINITION, null);
    r = variable_name(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, variable_definition_separator(b, l + 1));
    r = p && variable_value(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // "="
  public static boolean variable_definition_separator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_definition_separator")) return false;
    if (!nextTokenIs(b, EQUAL_SIGN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUAL_SIGN);
    exit_section_(b, m, VARIABLE_DEFINITION_SEPARATOR, r);
    return r;
  }

  /* ********************************************************** */
  // VARIABLE_NAME_TOKEN
  public static boolean variable_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_name")) return false;
    if (!nextTokenIs(b, VARIABLE_NAME_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VARIABLE_NAME_TOKEN);
    exit_section_(b, m, VARIABLE_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // VARIABLE_REFERENCE_TOKEN
  public static boolean variable_reference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference")) return false;
    if (!nextTokenIs(b, VARIABLE_REFERENCE_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VARIABLE_REFERENCE_TOKEN);
    exit_section_(b, m, VARIABLE_REFERENCE, r);
    return r;
  }

  /* ********************************************************** */
  // number_literal
  public static boolean variable_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_value")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = number_literal(b, l + 1);
    exit_section_(b, m, VARIABLE_VALUE, r);
    return r;
  }

}
