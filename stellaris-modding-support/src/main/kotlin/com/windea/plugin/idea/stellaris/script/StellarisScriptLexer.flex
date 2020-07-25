package com.windea.plugin.idea.stellaris.script.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.*;
import static com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*;

%%

%public
%class StellarisScriptLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%state WAITING_VARIABLE_EQUAL_SIGN
%state WAITING_VARIABLE_VALUE
%state WAITING_VARIABLE_EOL

%state WAITING_PROPERTY_KEY
%state WATIING_PROPERTY_SEPARATOR
%state WAITING_PROPERTY_VALUE
%state WAITING_PROPERTY_EOL

%{
  int depth=0;
%}

//不要使用\R：可能不合法

EOL=\s*\R
WHITE_SPACE=\s+
SPACE=[ \t]+

COMMENT=#[^\r\n]*
END_OF_LINE_COMMENT=#[^\r\n]*
BOOLEAN=(yes)|(no)
NUMBER=[0-9]+(\.[0-9]+)?
VARIABLE_NAME_ID=@[a-zA-Z0-9_]+
VARIABLE_REFERENCE_ID=@[a-zA-Z0-9_]+
PROPERTY_KEY_ID=[a-z0-9_\-]+
STRING=\"([^\"(\r\n\\]|\\.)*?\"
UNQUOTED_STRING=[^\s]+
UPPER_STRING=[A-Z0-9_\-]+

%%
<YYINITIAL> {
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
  {COMMENT} {return COMMENT; }
  {VARIABLE_NAME_ID} { yybegin(WAITING_VARIABLE_EQUAL_SIGN); return VARIABLE_NAME_ID; }
  {PROPERTY_KEY_ID} {yybegin(WATIING_PROPERTY_SEPARATOR); return PROPERTY_KEY_ID;}
}
<WAITING_VARIABLE_EQUAL_SIGN> {
  "=" {yybegin(WAITING_VARIABLE_VALUE); return EQUAL_SIGN;}
  {EOL} { yybegin(YYINITIAL); return WHITE_SPACE; } //跳过非法字符
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
}
<WAITING_VARIABLE_VALUE> {
  {NUMBER} {yybegin(WAITING_VARIABLE_EOL); return NUMBER_TOKEN; }
  {EOL} { yybegin(YYINITIAL); return WHITE_SPACE; } //跳过非法字符
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
}
<WAITING_VARIABLE_EOL> {
  {EOL} { yybegin(YYINITIAL); return WHITE_SPACE; }
  {SPACE} { return WHITE_SPACE; } //继续解析
  {END_OF_LINE_COMMENT} { yybegin(YYINITIAL); return END_OF_LINE_COMMENT; }
}

<WAITING_PROPERTY_KEY> {
  "}" {depth--; return RIGHT_BRACE;}
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
  {COMMENT} {  return COMMENT; }
  "OR"|"AND"|"NOR"|"NOT" {yybegin(WATIING_PROPERTY_SEPARATOR); return PROPERTY_KEY_ID;}
  {UPPER_STRING} {
        if(depth<=0){ yybegin(WATIING_PROPERTY_SEPARATOR); return PROPERTY_KEY_ID;}
        else{ yybegin(WAITING_PROPERTY_EOL); return UNQUOTED_STRING_TOKEN;}
      }
  {PROPERTY_KEY_ID} {yybegin(WATIING_PROPERTY_SEPARATOR); return PROPERTY_KEY_ID;}
  {STRING} {yybegin(WAITING_PROPERTY_EOL); return STRING_TOKEN;}
  {UNQUOTED_STRING} {yybegin(WAITING_PROPERTY_EOL); return UNQUOTED_STRING_TOKEN;}
}
<WATIING_PROPERTY_SEPARATOR> {
  "=" {yybegin(WAITING_PROPERTY_VALUE); return EQUAL_SIGN;}
  "<" {yybegin(WAITING_PROPERTY_VALUE); return LT_SIGN;}
  ">" {yybegin(WAITING_PROPERTY_VALUE); return GT_SIGN;}
  "<=" {yybegin(WAITING_PROPERTY_VALUE); return LE_SIGN;}
  ">=" {yybegin(WAITING_PROPERTY_VALUE); return GE_SIGN;}
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
}
<WAITING_PROPERTY_VALUE>{
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
  {COMMENT} {  return COMMENT; }
  "{" {depth++; yybegin(WAITING_PROPERTY_KEY); return LEFT_BRACE;}
  {BOOLEAN} { yybegin(WAITING_PROPERTY_EOL); return BOOLEAN_TOKEN; }
  {NUMBER} { yybegin(WAITING_PROPERTY_EOL); return NUMBER_TOKEN; }
  {VARIABLE_REFERENCE_ID} {yybegin(WAITING_PROPERTY_EOL); return VARIABLE_REFERENCE_ID;}
  {STRING} { yybegin(WAITING_PROPERTY_EOL); return STRING_TOKEN; }
  {UNQUOTED_STRING} { yybegin(WAITING_PROPERTY_EOL); return UNQUOTED_STRING_TOKEN; }
}
<WAITING_PROPERTY_EOL> {
  "}" {depth--; yybegin(WAITING_PROPERTY_KEY); return RIGHT_BRACE;}
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  {SPACE} { return WHITE_SPACE; }
  {END_OF_LINE_COMMENT} {  return END_OF_LINE_COMMENT; }
  {STRING} { return STRING_TOKEN;}
  {UNQUOTED_STRING} {return UNQUOTED_STRING_TOKEN;}
}

[^] { return BAD_CHARACTER; }
