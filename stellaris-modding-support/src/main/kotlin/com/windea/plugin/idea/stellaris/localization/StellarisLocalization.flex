package com.windea.plugin.idea.stellaris.localization.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.*;
import static com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*;

%%

%public
%class StellarisLocalizationLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\s*\R
WHITE_SPACE=\s+
SPACE=[ \t]+

COMMENT=#[^\r\n]*
ROOT_COMMENT=#[^\r\n]*
NUMBER=\d
HEADER_TOKEN=[a-z_]+
KEY_TOKEN=[a-zA-Z][a-zA-Z0-9_.]*
VALUE_TOKEN=\"([^\"\r\n\\]|\\.)*?\"

%state WAITING_PROPERTY_HEADER_COLON
%state WAITING_PROPERTY_HEADER_EOL
%state WAITING_PROPERTY_KEY
%state WAITING_PROPERTY_COLON
%state WAITING_PROPERTY_NUMBER
%state WAITING_PROPERTY_SPACE
%state WAITING_PROPERTY_VALUE
%state WAITING_PROPERTY_EOL

%%

<YYINITIAL> {
  {ROOT_COMMENT} {  return ROOT_COMMENT; }
  {HEADER_TOKEN} { yybegin(WAITING_PROPERTY_HEADER_COLON); return HEADER_TOKEN; }
  {WHITE_SPACE} {  return WHITE_SPACE; } //继续解析
}

<WAITING_PROPERTY_HEADER_COLON>{
  ":" { yybegin(WAITING_PROPERTY_HEADER_EOL); return COLON; }
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
}
<WAITING_PROPERTY_HEADER_EOL>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  //{COMMENT} { yybegin(WAITING_PROPERTY_HEADER_EOL); return COMMENT; }
  {SPACE} { return WHITE_SPACE; } //继续解析
}

<WAITING_PROPERTY_KEY> {
  {COMMENT} { return COMMENT; }
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
  {KEY_TOKEN} { yybegin(WAITING_PROPERTY_COLON); return KEY_TOKEN; }
}
<WAITING_PROPERTY_COLON>{
  ":" {yybegin(WAITING_PROPERTY_NUMBER); return COLON; }
  {SPACE} { return WHITE_SPACE;}
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
}
<WAITING_PROPERTY_NUMBER>{
  {NUMBER} {yybegin(WAITING_PROPERTY_SPACE); return NUMBER;}
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
}
<WAITING_PROPERTY_SPACE>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {SPACE} {yybegin(WAITING_PROPERTY_VALUE); return WHITE_SPACE;}
}
<WAITING_PROPERTY_VALUE> {
  {VALUE_TOKEN} { yybegin(WAITING_PROPERTY_EOL); return VALUE_TOKEN; }
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
}
<WAITING_PROPERTY_EOL>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  {SPACE} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //继续解析
  //{COMMENT} { yybegin(WAITING_PROPERTY_EOL); return COMMENT; }
}

[^] { return TokenType.BAD_CHARACTER; }
