package com.windea.plugin.idea.stellaris.script.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.*;
import static com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*;

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

%state WAITING_PROPERTY
%state WAITING_PROPERTY_KEY
%state WATIING_PROPERTY_SEPARATOR
%state WAITING_PROPERTY_VALUE
%state WAITING_PROPERTY_EOL

%state WAITING_COLOR
%state WAITING_COLOR_START
%state WAITING_COLOR_PARAMETER
%state WAITING_COLOR_END


%{
  int depth = 0;

  public int nextState(){
  	if(depth <= 0)
  		return YYINITIAL;
  	else
  		return WAITING_PROPERTY_KEY;
  }
%}

//不要使用\R：可能不合法

EOL=\s*\R
WHITE_SPACE=\s+
SPACE=[ \t]+

IS_PROEPRTY=[^\"@\r\n][^\r\n]*[=><]

COMMENT =#[^\r\n]*
END_OF_LINE_COMMENT=#[^\r\n]*
VARIABLE_NAME_ID=@[a-zA-Z0-9_\-]+
PROPERTY_KEY_ID=[a-zA-Z0-9_\-:]+
VARIABLE_REFERENCE_ID=@[a-zA-Z0-9_\-]+
COLOR_TOKEN=(rgb|rgba|hsv)[ \t]*\{[^\r\n]*?}
BOOLEAN=(yes)|(no)
NUMBER=-?[0-9]+(\.[0-9]+)?
STRING=\"([^\"(\r\n\\]|\\.)*?\"
UNQUOTED_STRING=[^@\s\[\]\{\}=\"][^\s\[\]\{\}=\"]*

%%
<YYINITIAL> {
  "}" {depth--;  yybegin(nextState()); return RIGHT_BRACE;}
  "{" {depth++;  yybegin(nextState()); return LEFT_BRACE;}
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
  {COMMENT} {return COMMENT; }
  {VARIABLE_NAME_ID} { yybegin(WAITING_VARIABLE_EQUAL_SIGN); return VARIABLE_NAME_ID; }
  //在这里根据后面是否有"="判断是否是property
  {IS_PROEPRTY} {yypushback(yylength()); yybegin(WAITING_PROPERTY);}
  {COLOR_TOKEN} {yybegin(WAITING_PROPERTY_EOL); return COLOR_TOKEN;}
  {BOOLEAN} { yybegin(WAITING_PROPERTY_EOL); return BOOLEAN_TOKEN; }
  {NUMBER} { yybegin(WAITING_PROPERTY_EOL); return NUMBER_TOKEN; }
  {UNQUOTED_STRING} {yybegin(WAITING_PROPERTY_EOL); return UNQUOTED_STRING_TOKEN;}
  {STRING} {yybegin(WAITING_PROPERTY_EOL); return STRING_TOKEN;}
}
<WAITING_VARIABLE_EQUAL_SIGN> {
  "}" {depth--;  yybegin(nextState()); return RIGHT_BRACE;}
  "{" {depth++;  yybegin(nextState()); return LEFT_BRACE;}
  "=" {yybegin(WAITING_VARIABLE_VALUE); return EQUAL_SIGN;}
  {EOL} { yybegin(YYINITIAL); return WHITE_SPACE; } //跳过非法字符
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
  {END_OF_LINE_COMMENT} {  return END_OF_LINE_COMMENT; }
}
<WAITING_VARIABLE_VALUE> {
  "}" {depth--;  yybegin(nextState()); return RIGHT_BRACE;}
  "{" {depth++;  yybegin(nextState()); return LEFT_BRACE;}
  {BOOLEAN} { yybegin(WAITING_PROPERTY_EOL); return BOOLEAN_TOKEN; }
  {NUMBER} {yybegin(WAITING_VARIABLE_EOL); return NUMBER_TOKEN; }
  {UNQUOTED_STRING} {yybegin(WAITING_PROPERTY_EOL); return UNQUOTED_STRING_TOKEN;}
  {STRING} {yybegin(WAITING_PROPERTY_EOL); return STRING_TOKEN;}
  {EOL} { yybegin(YYINITIAL); return WHITE_SPACE; } //跳过非法字符
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
  {END_OF_LINE_COMMENT} {  return END_OF_LINE_COMMENT; }
}
<WAITING_VARIABLE_EOL> {
  "}" {depth--;  yybegin(nextState()); return RIGHT_BRACE;}
  "{" {depth++;  yybegin(nextState()); return LEFT_BRACE;}
  {EOL} { yybegin(YYINITIAL); return WHITE_SPACE; }
  {SPACE} { return WHITE_SPACE; } //继续解析
  {END_OF_LINE_COMMENT} { return END_OF_LINE_COMMENT; }
}

<WAITING_PROPERTY>{
  "}" {depth--;  yybegin(nextState()); return RIGHT_BRACE;}
  "{" {depth++;  yybegin(nextState()); return LEFT_BRACE;}
  {PROPERTY_KEY_ID} {yybegin(WATIING_PROPERTY_SEPARATOR); return PROPERTY_KEY_ID;}
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
  {COMMENT} {  return COMMENT; }
}
<WAITING_PROPERTY_KEY> {
  "}" {depth--;  yybegin(nextState()); return RIGHT_BRACE;}
  "{" {depth++;  yybegin(nextState()); return LEFT_BRACE;}
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
  {COMMENT} {  return COMMENT; }
  //在这里根据后面是否有"="判断是否是property
  {IS_PROEPRTY} {yypushback(yylength()); yybegin(WAITING_PROPERTY);}
  {COLOR_TOKEN} {yybegin(WAITING_PROPERTY_EOL); return COLOR_TOKEN;}
  {BOOLEAN} { yybegin(WAITING_PROPERTY_EOL); return BOOLEAN_TOKEN; }
  {NUMBER} { yybegin(WAITING_PROPERTY_EOL); return NUMBER_TOKEN; }
  {UNQUOTED_STRING} {yybegin(WAITING_PROPERTY_EOL); return UNQUOTED_STRING_TOKEN;}
  {STRING} {yybegin(WAITING_PROPERTY_EOL); return STRING_TOKEN;}
}
<WATIING_PROPERTY_SEPARATOR> {
  "}" {depth--;  yybegin(nextState()); return RIGHT_BRACE;}
  "{" {depth++;  yybegin(nextState()); return LEFT_BRACE;}
  "=" {yybegin(WAITING_PROPERTY_VALUE); return EQUAL_SIGN;}
  "<" {yybegin(WAITING_PROPERTY_VALUE); return LT_SIGN;}
  ">" {yybegin(WAITING_PROPERTY_VALUE); return GT_SIGN;}
  "<=" {yybegin(WAITING_PROPERTY_VALUE); return LE_SIGN;}
  ">=" {yybegin(WAITING_PROPERTY_VALUE); return GE_SIGN;}
  {EOL} {  yybegin(nextState()); return WHITE_SPACE; } //跳过非法字符
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
  {END_OF_LINE_COMMENT} {  return END_OF_LINE_COMMENT; }
}
<WAITING_PROPERTY_VALUE>{
  "}" {depth--;  yybegin(nextState()); return RIGHT_BRACE;}
  "{" {depth++;  yybegin(nextState()); return LEFT_BRACE;}
  {EOL} {  yybegin(nextState()); return WHITE_SPACE; } //跳过非法字符
  {SPACE} { return WHITE_SPACE; }
  {END_OF_LINE_COMMENT} {  return END_OF_LINE_COMMENT; }
  {VARIABLE_REFERENCE_ID} {yybegin(WAITING_PROPERTY_EOL); return VARIABLE_REFERENCE_ID;}
  {COLOR_TOKEN} {yybegin(WAITING_PROPERTY_EOL); return COLOR_TOKEN;}
  {BOOLEAN} { yybegin(WAITING_PROPERTY_EOL); return BOOLEAN_TOKEN; }
  {NUMBER} { yybegin(WAITING_PROPERTY_EOL); return NUMBER_TOKEN; }
  {STRING} { yybegin(WAITING_PROPERTY_EOL); return STRING_TOKEN; }
  {UNQUOTED_STRING} { yybegin(WAITING_PROPERTY_EOL); return UNQUOTED_STRING_TOKEN; }
}
<WAITING_PROPERTY_EOL> {
  "}" {depth--;  yybegin(nextState()); return RIGHT_BRACE;}
  "{" {depth++;  yybegin(nextState()); return LEFT_BRACE;}
  {EOL} {  yybegin(nextState()); return WHITE_SPACE; }
  {SPACE} { return WHITE_SPACE; }
  {END_OF_LINE_COMMENT} {  return END_OF_LINE_COMMENT; }
  //可以在同一行
  //在这里根据后面是否有"="判断是否是property
  {IS_PROEPRTY} {yypushback(yylength()); yybegin(WAITING_PROPERTY);}
  {COLOR_TOKEN} {yybegin(WAITING_PROPERTY_EOL); return COLOR_TOKEN;}
  {BOOLEAN} { return BOOLEAN_TOKEN; }
  {NUMBER} { return NUMBER_TOKEN; }
  {STRING} { return STRING_TOKEN;}
  {UNQUOTED_STRING} {return UNQUOTED_STRING_TOKEN;}
}

[^] { return BAD_CHARACTER; }
