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
%state WAITING_CODE
%state WAITING_ICON
%state WAITING_SERIAL_NUMBER
%state WAITING_COLORFUL_TEXT_ID
%state WAITING_COLORFUL_TEXT

%{
  int depth = 0;

  public int nextState(){
    if(depth <= 0)
      return WAITING_RICH_TEXT;
    else
      return WAITING_COLORFUL_TEXT;
  }
%}

//不要使用\R：可能不合法

EOL=\s*\R
WHITE_SPACE=\s+
SPACE=[ \t]+

COMMENT=#[^\r\n]*
END_OF_LINE_COMMENT=#[^\r\n]*
ROOT_COMMENT=#[^\r\n]*
NUMBER=\d
LOCALE_ID=[a-z_]+
PROPERTY_KEY_ID=[a-zA-Z][a-zA-Z0-9_.\-]*
STRING_TOKEN=[^\"\[$£§%\r\n\\]+
VALID_ESCAPE_TOKEN=\\[\"rn$£§%\[]
INVALID_ESCAPE_TOKEN=\\.
LEFT_QUOTE="\""
RIGHT_QUOTE="\""
PROPERTY_REFERENCE_START="$"
PROPERTY_REFERENCE_SEPARATOR="|"
PROPERTY_REFERENCE_PARAMETER=[^$\r\n]+
PROPERTY_REFERENCE_END="$"
CODE_START="["
CODE_TEXT=[^\[\]\r\n]+
CODE_END="]"
ICON_START="£"
ICON_ID=[a-z_]+
ICON_END="£"
SERIAL_NUMBER_START="%"
SERIAL_NUMBER_ID=[A-Z]
SERIAL_NUMBER_END="%"
COLORFUL_TEXT_START="§"
COLORFUL_TEXT_ID=[A-Z]
COLORFUL_TEXT_END="§!"

%%

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
  //{COMMENT} { yybegin(WAITING_LOCALE_EOL); return COMMENT; }
  {SPACE} { return WHITE_SPACE; } //继续解析
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
  {LEFT_QUOTE} { yybegin(WAITING_RICH_TEXT); return LEFT_QUOTE; }
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
}
<WAITING_RICH_TEXT>{
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;}
  {PROPERTY_REFERENCE_START} { yybegin(WAITING_PROPERTY_REFERENCE); return PROPERTY_REFERENCE_START;}
  {CODE_START} { yybegin(WAITING_CODE); return CODE_START;}
  {ICON_START} { yybegin(WAITING_ICON); return ICON_START;}
  {SERIAL_NUMBER_START} { yybegin(WAITING_SERIAL_NUMBER); return SERIAL_NUMBER_START;}
  {COLORFUL_TEXT_START} { depth++; yybegin(WAITING_COLORFUL_TEXT_ID); return COLORFUL_TEXT_START;}
  {VALID_ESCAPE_TOKEN} {return VALID_ESCAPE_TOKEN;}
  {INVALID_ESCAPE_TOKEN} {return INVALID_ESCAPE_TOKEN;}
  {STRING_TOKEN} {  return STRING_TOKEN;}
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
}
<WAITING_PROPERTY_REFERENCE>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;} //跳过非法字符
  {PROPERTY_REFERENCE_END} {yybegin(nextState()); return PROPERTY_REFERENCE_END;}
  {PROPERTY_KEY_ID} {return PROPERTY_KEY_ID;}
  {PROPERTY_REFERENCE_SEPARATOR} { yybegin(WAITING_PROPERTY_REFERENCE_PARAMETER); return PROPERTY_REFERENCE_SEPARATOR;}
}
<WAITING_PROPERTY_REFERENCE_PARAMETER>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;} //跳过非法字符
  {PROPERTY_REFERENCE_END} {yybegin(nextState()); return PROPERTY_REFERENCE_END;}
  {PROPERTY_REFERENCE_PARAMETER} {return PROPERTY_REFERENCE_PARAMETER;}
}
<WAITING_CODE>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;} //跳过非法字符
  {CODE_END} {yybegin(nextState()); return CODE_END;}
  {CODE_TEXT} {return CODE_TEXT;}
}
<WAITING_ICON>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;} //跳过非法字符
  {ICON_END} {yybegin(nextState()); return ICON_END;}
  {ICON_ID} {return ICON_ID;}
}
<WAITING_SERIAL_NUMBER>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;} //跳过非法字符
  {SERIAL_NUMBER_END} {yybegin(nextState()); return SERIAL_NUMBER_END;}
  {SERIAL_NUMBER_ID} {return SERIAL_NUMBER_ID;}
}
<WAITING_COLORFUL_TEXT_ID>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;} //跳过非法字符
  {COLORFUL_TEXT_END} {depth--; yybegin(nextState()); return COLORFUL_TEXT_END;} //跳过非法字符
  {COLORFUL_TEXT_ID} {yybegin(WAITING_COLORFUL_TEXT); return COLORFUL_TEXT_ID;}
}
<WAITING_COLORFUL_TEXT>{
  {COLORFUL_TEXT_END} {depth--; yybegin(nextState()); return COLORFUL_TEXT_END;} //跳过非法字符
  {RIGHT_QUOTE} { yybegin(WAITING_PROPERTY_KEY); return RIGHT_QUOTE;}
  {PROPERTY_REFERENCE_START} { yybegin(WAITING_PROPERTY_REFERENCE); return PROPERTY_REFERENCE_START;}
  {CODE_START} { yybegin(WAITING_CODE); return CODE_START;}
  {ICON_START} { yybegin(WAITING_ICON); return ICON_START;}
  {SERIAL_NUMBER_START} { yybegin(WAITING_SERIAL_NUMBER); return SERIAL_NUMBER_START;}
  {VALID_ESCAPE_TOKEN} {return VALID_ESCAPE_TOKEN;}
  {INVALID_ESCAPE_TOKEN} {return INVALID_ESCAPE_TOKEN;}
  {STRING_TOKEN} {  return STRING_TOKEN;}
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; } //跳过非法字符
}
<WAITING_PROPERTY_EOL>{
  {EOL} { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE; }
  {SPACE} { return WHITE_SPACE; } //继续解析
  {END_OF_LINE_COMMENT} {  return END_OF_LINE_COMMENT; }
}

[^] { return TokenType.BAD_CHARACTER; }
