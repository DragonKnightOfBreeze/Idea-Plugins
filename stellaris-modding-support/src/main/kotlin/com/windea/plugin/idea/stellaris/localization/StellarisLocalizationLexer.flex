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

%state WAITING_LOCALE_COLON
%state WAITING_LOCALE_EOL
%state WAITING_PROPERTY_KEY
%state WAITING_PROPERTY_COLON
%state WAITING_PROPERTY_NUMBER
%state WAITING_PROPERTY_SPACE
%state WAITING_PROPERTY_VALUE
%state WAITING_PROPERTY_EOL
%state WAITING_RICH_TEXT
%state WAITING_PROPERTY_REFERENCE
%state WAITING_PROPERTY_REFERENCE_PARAMETER
%state WAITING_ICON
%state WAITING_ICON_PARAMETER
%state WAITING_SERIAL_NUMBER
%state WAITING_CODE
%state WAITING_COLOR_CODE
%state WAITING_COLORFUL_TEXT

%state WAITING_CHECK_RIGHT_QUOTE
%state WAITING_CHECK_PERCENT
%state WAITING_SERIAL_NUMEBR_START

%{
  int depth = 0;
  boolean inIconName = false;
  boolean isColorfulText = false;

  public int nextState(){
	  return depth <= 0 ? WAITING_RICH_TEXT : WAITING_COLORFUL_TEXT;
  }

  public int nextStateInIconName(){
  	return inIconName? WAITING_ICON: nextState();
  }

  public int nextStateForText(){
  	return isColorfulText?WAITING_COLORFUL_TEXT:WAITING_RICH_TEXT;
  }
%}

//不要使用\R：可能不合法

EOL=\s*\R
WHITE_SPACE=\s+
SPACE=[ \t]+

COMMENT=#[^\r\n]*
ROOT_COMMENT=#[^\r\n]*
//行尾注释不能包含双引号，否则会有解析冲突
END_OF_LINE_COMMENT=#[^\"\r\n]*
NUMBER=\d+
LOCALE_ID=[a-z_]+
PROPERTY_KEY_ID=[a-zA-Z0-9_.\-']+
VALID_ESCAPE_TOKEN=\\[\"rn$£§%\[]
INVALID_ESCAPE_TOKEN=\\.
PROPERTY_REFERENCE_ID=[a-zA-Z0-9_.\-']+
PROPERTY_REFERENCE_PARAMETER=[^$\"\r\n]+
ICON_ID=[a-zA-Z\-_\\/]+
ICON_PARAMETER=[^£\"\r\n]+
SERIAL_NUMBER_ID=[a-zA-Z]
CODE_TEXT=[^\"\[\]\r\n]+
COLOR_CODE=[a-zA-Z]
//双引号和百分号实际上不需要转义
STRING_TOKEN=[^\"%$£§\[\r\n\\]+

CHECK_RIGHT_QUOTE=\"[^\"\r\n]*\"?
CHECK_PERCENT=%.?.?
IS_SERIAL_NUMBER=%.%

%%

//同一状态下的规则无法保证顺序

<YYINITIAL> {
  {ROOT_COMMENT} {  return ROOT_COMMENT; }
  {LOCALE_ID} { yybegin(WAITING_LOCALE_COLON); return LOCALE_ID; }
  {WHITE_SPACE} {  return WHITE_SPACE; } //继续解析
}

<WAITING_LOCALE_COLON>{
  ":" { yybegin(WAITING_LOCALE_EOL); return COLON; }
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
}
<WAITING_LOCALE_EOL>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  {SPACE} { return WHITE_SPACE; }
  {END_OF_LINE_COMMENT} {  return END_OF_LINE_COMMENT; }
}

<WAITING_PROPERTY_KEY> {
  {COMMENT} { return COMMENT; }
  {WHITE_SPACE} { return WHITE_SPACE; } //继续解析
  {PROPERTY_KEY_ID} { yybegin(WAITING_PROPERTY_COLON); return PROPERTY_KEY_ID; }
}
<WAITING_PROPERTY_COLON>{
  ":" {yybegin(WAITING_PROPERTY_NUMBER); return COLON; }
  {SPACE} { return WHITE_SPACE;}
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
}
<WAITING_PROPERTY_NUMBER>{
  {NUMBER} {yybegin(WAITING_PROPERTY_SPACE); return NUMBER;}
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {SPACE} {yybegin(WAITING_PROPERTY_VALUE); return WHITE_SPACE;}
}
<WAITING_PROPERTY_SPACE>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {SPACE} {yybegin(WAITING_PROPERTY_VALUE); return WHITE_SPACE;}
}
<WAITING_PROPERTY_VALUE> {
  \" { yybegin(WAITING_RICH_TEXT); return LEFT_QUOTE; }
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
}
<WAITING_RICH_TEXT>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  \" { isColorfulText=false; yypushback(yylength()); yybegin(WAITING_CHECK_RIGHT_QUOTE);}
  "$" { yybegin(WAITING_PROPERTY_REFERENCE); return PROPERTY_REFERENCE_START;}
  "[" { yybegin(WAITING_CODE); return CODE_START;}
  "£" { yybegin(WAITING_ICON); return ICON_START;}
  "%" { isColorfulText=false; yypushback(yylength()); yybegin(WAITING_CHECK_PERCENT);}
  "§" { depth++; yybegin(WAITING_COLOR_CODE); return COLORFUL_TEXT_START;}
  {VALID_ESCAPE_TOKEN} {return VALID_ESCAPE_TOKEN;}
  {INVALID_ESCAPE_TOKEN} {return INVALID_ESCAPE_TOKEN;}
  {STRING_TOKEN} {  return STRING_TOKEN;}
}
<WAITING_PROPERTY_REFERENCE>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  "$" {yybegin(nextStateInIconName()); inIconName=false; return PROPERTY_REFERENCE_END;}
  \" { yybegin(WAITING_PROPERTY_EOL); return RIGHT_QUOTE;}
  "|" { yybegin(WAITING_PROPERTY_REFERENCE_PARAMETER); return PARAMETER_SEPARATOR;}
  {PROPERTY_REFERENCE_ID} {return PROPERTY_REFERENCE_ID;}
}
<WAITING_PROPERTY_REFERENCE_PARAMETER>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  "$" {yybegin(nextState()); return PROPERTY_REFERENCE_END;}
  \" { yybegin(WAITING_PROPERTY_EOL); return RIGHT_QUOTE;}
  {PROPERTY_REFERENCE_PARAMETER} {return PROPERTY_REFERENCE_PARAMETER;}
}
<WAITING_ICON>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  "£" {yybegin(nextState()); return ICON_END;}
  "$" {yybegin(WAITING_PROPERTY_REFERENCE); inIconName=true; return PROPERTY_REFERENCE_START;}
  "|" { yybegin(WAITING_ICON_PARAMETER); return PARAMETER_SEPARATOR;}
  \" { yybegin(WAITING_PROPERTY_EOL); return RIGHT_QUOTE;}
  {ICON_ID} {return ICON_ID;}
}
<WAITING_ICON_PARAMETER>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  "£" {yybegin(nextState()); return ICON_END;}
  \" { yybegin(WAITING_PROPERTY_EOL); return RIGHT_QUOTE;}
  {ICON_PARAMETER} {return ICON_PARAMETER;}
}
<WAITING_SERIAL_NUMEBR_START>{
  "%" { yybegin(WAITING_SERIAL_NUMBER); return SERIAL_NUMBER_START;}
}
<WAITING_SERIAL_NUMBER>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  "%" {yybegin(nextState()); return SERIAL_NUMBER_END;}
  \" { yybegin(WAITING_PROPERTY_EOL); return RIGHT_QUOTE;}
  {SERIAL_NUMBER_ID} {return SERIAL_NUMBER_ID;}
}
<WAITING_CODE>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  "]" {yybegin(nextState()); return CODE_END;}
  \" { yybegin(WAITING_PROPERTY_EOL); return RIGHT_QUOTE;}
  {CODE_TEXT} {return CODE_TEXT;}
}
<WAITING_COLOR_CODE>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  "§!" {depth--; yybegin(nextState()); return COLORFUL_TEXT_END;} //跳过非法字符
  \" { yybegin(WAITING_PROPERTY_EOL); return RIGHT_QUOTE;}
  {COLOR_CODE} {yybegin(WAITING_COLORFUL_TEXT); return COLOR_CODE;}
}
<WAITING_COLORFUL_TEXT>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  \" { isColorfulText=true; yypushback(yylength()); yybegin(WAITING_CHECK_RIGHT_QUOTE);}
  "$" { yybegin(WAITING_PROPERTY_REFERENCE); return PROPERTY_REFERENCE_START;}
  "[" { yybegin(WAITING_CODE); return CODE_START;}
  "£" { yybegin(WAITING_ICON); return ICON_START;}
  "%" { isColorfulText=true; yypushback(yylength()); yybegin(WAITING_CHECK_PERCENT);}
  "§" { depth++; yybegin(WAITING_COLOR_CODE); return COLORFUL_TEXT_START;}
  "§!" {depth--; yybegin(nextState()); return COLORFUL_TEXT_END;} //跳过非法字符
  {VALID_ESCAPE_TOKEN} {return VALID_ESCAPE_TOKEN;}
  {INVALID_ESCAPE_TOKEN} {return INVALID_ESCAPE_TOKEN;}
  {STRING_TOKEN} {  return STRING_TOKEN;}
}
<WAITING_PROPERTY_EOL>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  {SPACE} { return WHITE_SPACE; } //继续解析
  {END_OF_LINE_COMMENT} {  return END_OF_LINE_COMMENT; }
}

<WAITING_CHECK_RIGHT_QUOTE>{
  {CHECK_RIGHT_QUOTE} {
      	//特殊处理
      	//如果匹配到的字符串不是仅包含双引号，且最后一个字符是双引号，则表示开始的双引号不代表字符串的结束
      	//否则认为是常规字符串
      	boolean isRightQuote = yylength() == 1 || yycharat(yylength()-1) != '"';
      	yypushback(yylength()-1);
      	if(isRightQuote){
      	    yybegin(WAITING_PROPERTY_EOL);
      	    return RIGHT_QUOTE;
      	}else{
      		  yybegin(nextStateForText());
            return STRING_TOKEN;
      	}
  }
}

<WAITING_CHECK_PERCENT>{
  {CHECK_PERCENT} {
        //特殊处理
        //如果匹配的字符串的第3个字符存在且为百分号，则认为整个字符串代表一个编号
        //否则认为是常规字符串
        boolean isSerialNumber = yylength() == 3 && yycharat(2) == '%';
        if(isSerialNumber){
            yybegin(WAITING_SERIAL_NUMBER);
            return SERIAL_NUMBER_START;
        }else{
            yybegin(nextStateForText());
            return STRING_TOKEN;
        }
  }
}

[^] { return TokenType.BAD_CHARACTER; }
