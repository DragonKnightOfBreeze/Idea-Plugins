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

%state WAITING_PROPERTY_HEADER_COLON
%state WAITING_PROPERTY_HEADER_EOL
%state WAITING_PROPERTY_KEY
%state WAITING_PROPERTY_COLON
%state WAITING_PROPERTY_NUMBER
%state WAITING_PROPERTY_SPACE
%state WAITING_PROPERTY_VALUE
%state WAITING_PROPERTY_EOL

%state WAITING_RICH_TEXT
%state WAITING_PROPERTY_REFERENCE
%state WAITING_CODE
%state WAITING_ICON
%state WAITING_SERIAL_NUMBER
%state WAITING_COLORFUL_TEXT_CODE
%state WAITING_COLORFUL_TEXT

%{
  int textDepth = 0;

  public int textState(){
  	if(textDepth <= 0)
  		return WAITING_RICH_TEXT;
  	else
  		return WAITING_COLORFUL_TEXT;
  }
%}

EOL=\s*\R
WHITE_SPACE=\s+
SPACE=[ \t]+

COMMENT=#[^\r\n]*
ROOT_COMMENT=#[^\r\n]*
NUMBER=\d
HEADER_TOKEN=[a-z_]+
KEY_TOKEN=[a-zA-Z][a-zA-Z0-9_.]*
VALUE_TOKEN=([^\"(\[$£§%\r\n\\]|\\.)+
LEFT_QUOTE=\"
RIGHT_QUOTE=\"
PROPERTY_REFERENCE_START="$"
PROPERTY_REFERENCE_END="$"
CODE_START="["
CODE_TEXT=[^\[\]\R]+
CODE_END=]
ICON_START=£
ICON_TEXT=[a-z_]+
ICON_END=£
SERIAL_NUMBER_START=%
SERIAL_NUMBER_CODE=[A-Z]
SERIAL_NUMBER_END=%
COLORFUL_TEXT_START=§
COLORFUL_TEXT_CODE=[A-Z]
COLORFUL_TEXT_END=§

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
  {LEFT_QUOTE} { yybegin(WAITING_RICH_TEXT); return LEFT_QUOTE; }
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
}
<WAITING_RICH_TEXT>{
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;}
  {PROPERTY_REFERENCE_START} { yybegin(WAITING_PROPERTY_REFERENCE); return PROPERTY_REFERENCE_START;}
  {CODE_START} { yybegin(WAITING_CODE); return CODE_START;}
  {ICON_START} { yybegin(WAITING_ICON); return ICON_START;}
  {SERIAL_NUMBER_START} { yybegin(WAITING_SERIAL_NUMBER); return SERIAL_NUMBER_START;}
  {COLORFUL_TEXT_START} { textDepth++; yybegin(WAITING_COLORFUL_TEXT_CODE); return COLORFUL_TEXT_START;}
  {VALUE_TOKEN} {  return VALUE_TOKEN;}
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
}
<WAITING_PROPERTY_REFERENCE>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;} //跳过非法字符
  {PROPERTY_REFERENCE_END} {yybegin(textState()); return PROPERTY_REFERENCE_END;}
  {KEY_TOKEN} {return KEY_TOKEN;}
}
<WAITING_CODE>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;} //跳过非法字符
  {CODE_END} {yybegin(textState()); return CODE_END;}
  {CODE_TEXT} {return CODE_TEXT;}
}
<WAITING_ICON>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;} //跳过非法字符
  {ICON_END} {yybegin(textState()); return ICON_END;}
  {ICON_TEXT} {return ICON_TEXT;}
}
<WAITING_SERIAL_NUMBER>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;} //跳过非法字符
  {SERIAL_NUMBER_END} {yybegin(textState()); return SERIAL_NUMBER_END;}
  {SERIAL_NUMBER_CODE} {return SERIAL_NUMBER_CODE;}
}
<WAITING_COLORFUL_TEXT_CODE>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;} //跳过非法字符
  {COLORFUL_TEXT_END} {textDepth--; yybegin(textState()); return COLORFUL_TEXT_END;} //跳过非法字符
  {COLORFUL_TEXT_CODE} {yybegin(WAITING_COLORFUL_TEXT); return COLORFUL_TEXT_CODE;}
}
<WAITING_COLORFUL_TEXT>{
  {COLORFUL_TEXT_END} {textDepth--;  ;yybegin(textState()); return COLORFUL_TEXT_END;} //跳过非法字符
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;}
  {PROPERTY_REFERENCE_START} { yybegin(WAITING_PROPERTY_REFERENCE); return PROPERTY_REFERENCE_START;}
  {CODE_START} { yybegin(WAITING_CODE); return CODE_START;}
  {ICON_START} { yybegin(WAITING_CODE); return ICON_START;}
  {SERIAL_NUMBER_START} { yybegin(WAITING_CODE); return SERIAL_NUMBER_START;}
  {VALUE_TOKEN} {  return VALUE_TOKEN;}
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
}
<WAITING_PROPERTY_EOL>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  {SPACE} { return WHITE_SPACE; } //继续解析
  //{COMMENT} { yybegin(WAITING_PROPERTY_EOL); return COMMENT; }
}

[^] { return TokenType.BAD_CHARACTER; }
