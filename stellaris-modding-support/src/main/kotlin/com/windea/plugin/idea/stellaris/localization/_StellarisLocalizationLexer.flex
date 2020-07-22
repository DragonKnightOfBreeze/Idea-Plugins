package com.windea.plugin.idea.stellaris.localization;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*;

%%

%{
  public _StellarisLocalizationLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _StellarisLocalizationLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

NUMBER=[0-9]
BLANK=[ \t\n\x0B\f\r]+
COMMENT=#[^\r\n]*
ROOT_COMMENT=#[^\r\n]*
END_OF_LINE_COMMENT=#[^\r\n]*
LOCALE_ID=[a-z_]+
KEY_TOKEN=[a-zA-Z][a-zA-Z0-9_.]*
VALUE_TOKEN=([^\"(\[$£§%\r\n\\]|\\.)+
PROPERTY_REFERENCE_PARAMETER=[^$\r\n]+
CODE_TEXT=[^\[\]\r\n]+
ICON_TEXT=[a-z_]+
SERIAL_NUMBER_CODE=[A-Z]
COLORFUL_TEXT_CODE=[A-Z]

%%
<YYINITIAL> {
  {WHITE_SPACE}                       { return WHITE_SPACE; }

  ":"                                 { return COLON; }
  "\""                                { return LEFT_QUOTE; }
  "\""                                { return RIGHT_QUOTE; }
  "$"                                 { return PROPERTY_REFERENCE_START; }
  "|"                                 { return PROPERTY_REFERENCE_SEPARATOR; }
  "$"                                 { return PROPERTY_REFERENCE_END; }
  "["                                 { return CODE_START; }
  "]"                                 { return CODE_END; }
  "£"                                 { return ICON_START; }
  "£"                                 { return ICON_END; }
  "%"                                 { return SERIAL_NUMBER_START; }
  "%"                                 { return SERIAL_NUMBER_END; }
  "§"                                 { return COLORFUL_TEXT_START; }
  "§!"                                { return COLORFUL_TEXT_END; }

  {NUMBER}                            { return NUMBER; }
  {BLANK}                             { return BLANK; }
  {COMMENT}                           { return COMMENT; }
  {ROOT_COMMENT}                      { return ROOT_COMMENT; }
  {END_OF_LINE_COMMENT}               { return END_OF_LINE_COMMENT; }
  {LOCALE_ID}                         { return LOCALE_ID; }
  {KEY_TOKEN}                         { return KEY_TOKEN; }
  {VALUE_TOKEN}                       { return VALUE_TOKEN; }
  {PROPERTY_REFERENCE_PARAMETER}      { return PROPERTY_REFERENCE_PARAMETER; }
  {CODE_TEXT}                         { return CODE_TEXT; }
  {ICON_TEXT}                         { return ICON_TEXT; }
  {SERIAL_NUMBER_CODE}                { return SERIAL_NUMBER_CODE; }
  {COLORFUL_TEXT_CODE}                { return COLORFUL_TEXT_CODE; }

}

[^] { return BAD_CHARACTER; }
