/* The following code was generated by JFlex 1.7.0 tweaked for IntelliJ platform */

package com.windea.plugin.idea.stellaris.script.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.*;
import static com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>StellarisScriptLexer.flex</tt>
 */
public class StellarisScriptLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int WAITING_VARIABLE_EQUAL_SIGN = 2;
  public static final int WAITING_VARIABLE_VALUE = 4;
  public static final int WAITING_VARIABLE_EOL = 6;
  public static final int WAITING_PROPERTY_KEY = 8;
  public static final int WATIING_PROPERTY_SEPARATOR = 10;
  public static final int WAITING_PROPERTY_VALUE = 12;
  public static final int WAITING_PROPERTY_EOL = 14;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3,  3,  4,  4,  5,  5,  6,  6,  7, 7
  };

  /** 
   * Translates characters to character classes
   * Chosen bits are [7, 7, 7]
   * Total runtime size is 1928 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[(ZZ_CMAP_Y[ZZ_CMAP_Z[ch>>14]|((ch>>7)&0x7f)]<<7)|(ch&0x7f)];
  }

  /* The ZZ_CMAP_Z table has 68 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\103\200");

  /* The ZZ_CMAP_Y table has 256 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\1\1\53\2\1\3\22\2\1\4\37\2\1\3\237\2");

  /* The ZZ_CMAP_A table has 640 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\4\1\3\2\2\1\3\22\0\1\4\1\0\1\21\1\5\4\0\1\22\4\0\1\17\1\14\1\0\12"+
    "\13\2\0\1\35\1\25\1\36\1\0\1\15\1\31\2\16\1\33\11\16\1\32\1\27\2\16\1\30\1"+
    "\16\1\34\6\16\1\0\1\23\2\0\1\24\1\0\4\20\1\7\10\20\1\11\1\12\3\20\1\10\5\20"+
    "\1\6\1\20\1\37\1\0\1\26\7\0\1\2\32\0\1\1\337\0\1\1\177\0\13\1\35\0\2\2\5\0"+
    "\1\1\57\0\1\1\40\0");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\10\0\1\1\1\2\1\3\1\4\1\1\1\2\1\5"+
    "\1\6\1\7\1\1\1\5\1\2\1\10\1\11\1\4"+
    "\2\12\1\11\1\13\3\12\1\2\1\14\1\15\1\16"+
    "\1\17\2\11\1\20\1\11\1\21\1\22\1\1\1\14"+
    "\1\2\1\23\1\22\1\24\1\25\3\0\1\26\1\11"+
    "\1\4\2\12\1\27\1\30\1\11\1\31\1\11\1\32"+
    "\2\0\1\33\1\22\1\7\1\26\1\0\1\20\1\33"+
    "\1\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[72];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\40\0\100\0\140\0\200\0\240\0\300\0\340"+
    "\0\u0100\0\u0120\0\u0140\0\u0160\0\u0180\0\u01a0\0\u01a0\0\u0100"+
    "\0\u01c0\0\u01e0\0\u01e0\0\u0200\0\u0220\0\u0240\0\u0260\0\u0280"+
    "\0\u02a0\0\u02c0\0\u0240\0\u02e0\0\u0300\0\u0320\0\u0340\0\u0340"+
    "\0\u0100\0\u0360\0\u0380\0\u03a0\0\u03c0\0\u03e0\0\u0400\0\u0240"+
    "\0\u0420\0\u0440\0\u0440\0\u0460\0\u0480\0\u04a0\0\u0420\0\u0180"+
    "\0\u04c0\0\u01e0\0\u04e0\0\u0240\0\u0500\0\u02a0\0\u0520\0\u0540"+
    "\0\u0100\0\u0100\0\u0560\0\u0240\0\u0580\0\u0400\0\u0440\0\u05a0"+
    "\0\u0420\0\u05c0\0\u04c0\0\u0100\0\u05e0\0\u0580\0\u0100\0\u0600";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[72];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\11\4\12\1\13\6\14\1\11\1\15\1\11\2\14"+
    "\3\11\1\14\14\11\1\16\2\17\1\16\20\11\1\20"+
    "\13\11\1\16\2\17\1\16\6\11\1\21\25\11\1\22"+
    "\2\23\1\24\1\25\32\11\1\26\4\12\1\13\5\27"+
    "\1\30\2\26\1\31\1\30\1\27\1\32\2\26\1\30"+
    "\1\26\1\33\1\34\1\31\1\35\1\36\2\31\3\26"+
    "\1\11\1\37\2\40\1\37\20\11\1\41\7\11\1\42"+
    "\1\43\1\11\1\26\4\12\1\13\1\44\2\26\1\45"+
    "\1\26\1\46\1\26\1\47\3\26\1\32\15\26\1\50"+
    "\1\51\1\52\2\53\1\54\1\55\13\51\1\56\4\51"+
    "\1\57\11\51\41\0\4\12\33\0\3\13\1\0\34\13"+
    "\6\0\6\14\3\0\2\14\3\0\1\14\21\0\6\60"+
    "\2\0\1\60\1\0\1\60\3\0\1\60\2\0\6\60"+
    "\4\0\1\16\2\17\1\16\46\0\1\21\1\61\24\0"+
    "\1\62\2\23\1\62\34\0\1\62\2\23\1\24\33\0"+
    "\3\25\1\0\34\25\1\26\4\0\34\26\4\0\1\26"+
    "\6\27\3\26\2\27\3\26\1\27\14\26\4\0\1\26"+
    "\5\27\1\30\2\26\1\31\1\30\1\27\3\26\1\30"+
    "\2\26\6\31\4\26\4\0\6\26\1\31\2\26\2\31"+
    "\4\26\1\31\2\26\6\31\3\26\1\32\2\63\1\0"+
    "\1\63\14\32\1\64\1\26\1\65\14\32\1\26\4\0"+
    "\6\26\1\31\2\26\2\31\4\26\1\31\2\26\1\31"+
    "\1\66\4\31\4\26\4\0\6\26\1\31\2\26\2\31"+
    "\4\26\1\31\2\26\3\31\1\67\2\31\4\26\4\0"+
    "\6\26\1\31\2\26\2\31\4\26\1\31\2\26\1\70"+
    "\5\31\3\26\1\0\1\37\2\40\1\37\60\0\1\71"+
    "\37\0\1\72\12\0\1\26\4\0\2\26\1\73\31\26"+
    "\4\0\5\26\1\74\26\26\4\0\6\26\1\46\1\75"+
    "\24\26\4\0\1\26\6\76\2\26\1\76\1\26\1\76"+
    "\3\26\1\76\2\26\6\76\3\26\1\51\4\0\33\51"+
    "\1\0\1\77\2\53\1\77\34\0\1\77\2\53\1\54"+
    "\33\0\3\55\1\0\34\55\1\56\2\100\1\0\1\100"+
    "\14\56\1\101\1\51\1\102\14\56\13\0\1\103\24\0"+
    "\3\63\1\0\15\63\1\104\1\0\1\105\14\63\1\32"+
    "\1\63\2\0\1\63\33\32\1\26\4\0\6\26\1\31"+
    "\2\26\2\31\4\26\1\31\2\26\4\31\1\66\1\31"+
    "\4\26\4\0\6\26\1\31\2\26\2\31\4\26\1\31"+
    "\2\26\1\31\1\66\3\31\1\66\4\26\4\0\3\26"+
    "\1\74\30\26\4\0\6\26\1\106\24\26\3\100\1\0"+
    "\15\100\1\107\1\0\1\110\14\100\1\56\1\100\2\0"+
    "\1\100\33\56\2\63\2\0\34\63\2\100\2\0\34\100";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1568];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\10\0\1\11\6\1\1\11\20\1\1\11\17\1\3\0"+
    "\5\1\2\11\4\1\2\0\3\1\1\11\1\0\1\1"+
    "\1\11\1\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[72];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  int depth=0;


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public StellarisScriptLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    int size = 0;
    for (int i = 0, length = packed.length(); i < length; i += 2) {
      size += packed.charAt(i);
    }
    char[] map = new char[size];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < packed.length()) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      {@code false}, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position {@code pos} from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + ZZ_CMAP(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return BAD_CHARACTER;
            } 
            // fall through
          case 28: break;
          case 2: 
            { return WHITE_SPACE;
            } 
            // fall through
          case 29: break;
          case 3: 
            { return COMMENT;
            } 
            // fall through
          case 30: break;
          case 4: 
            { yybegin(WATIING_PROPERTY_SEPARATOR); return PROPERTY_KEY_ID;
            } 
            // fall through
          case 31: break;
          case 5: 
            { yybegin(YYINITIAL); return WHITE_SPACE;
            } 
            // fall through
          case 32: break;
          case 6: 
            { yybegin(WAITING_VARIABLE_VALUE); return EQUAL_SIGN;
            } 
            // fall through
          case 33: break;
          case 7: 
            { yybegin(WAITING_VARIABLE_EOL); return NUMBER_TOKEN;
            } 
            // fall through
          case 34: break;
          case 8: 
            { yybegin(YYINITIAL); return END_OF_LINE_COMMENT;
            } 
            // fall through
          case 35: break;
          case 9: 
            { yybegin(WAITING_PROPERTY_EOL); return UNQUOTED_STRING_TOKEN;
            } 
            // fall through
          case 36: break;
          case 10: 
            { if(depth<=0){ yybegin(WATIING_PROPERTY_SEPARATOR); return PROPERTY_KEY_ID;}
        else{ yybegin(WAITING_PROPERTY_EOL); return UNQUOTED_STRING_TOKEN;}
            } 
            // fall through
          case 37: break;
          case 11: 
            { depth--; return RIGHT_BRACE;
            } 
            // fall through
          case 38: break;
          case 12: 
            { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE;
            } 
            // fall through
          case 39: break;
          case 13: 
            { yybegin(WAITING_PROPERTY_VALUE); return EQUAL_SIGN;
            } 
            // fall through
          case 40: break;
          case 14: 
            { yybegin(WAITING_PROPERTY_VALUE); return LT_SIGN;
            } 
            // fall through
          case 41: break;
          case 15: 
            { yybegin(WAITING_PROPERTY_VALUE); return GT_SIGN;
            } 
            // fall through
          case 42: break;
          case 16: 
            { yybegin(WAITING_PROPERTY_EOL); return NUMBER_TOKEN;
            } 
            // fall through
          case 43: break;
          case 17: 
            { depth++; yybegin(WAITING_PROPERTY_KEY); return LEFT_BRACE;
            } 
            // fall through
          case 44: break;
          case 18: 
            { return UNQUOTED_STRING_TOKEN;
            } 
            // fall through
          case 45: break;
          case 19: 
            { return END_OF_LINE_COMMENT;
            } 
            // fall through
          case 46: break;
          case 20: 
            { depth--; yybegin(WAITING_PROPERTY_KEY); return RIGHT_BRACE;
            } 
            // fall through
          case 47: break;
          case 21: 
            { yybegin(WAITING_VARIABLE_EQUAL_SIGN); return VARIABLE_NAME_ID;
            } 
            // fall through
          case 48: break;
          case 22: 
            { yybegin(WAITING_PROPERTY_EOL); return STRING_TOKEN;
            } 
            // fall through
          case 49: break;
          case 23: 
            { yybegin(WAITING_PROPERTY_VALUE); return LE_SIGN;
            } 
            // fall through
          case 50: break;
          case 24: 
            { yybegin(WAITING_PROPERTY_VALUE); return GE_SIGN;
            } 
            // fall through
          case 51: break;
          case 25: 
            { yybegin(WAITING_PROPERTY_EOL); return BOOLEAN_TOKEN;
            } 
            // fall through
          case 52: break;
          case 26: 
            { yybegin(WAITING_PROPERTY_EOL); return VARIABLE_REFERENCE_ID;
            } 
            // fall through
          case 53: break;
          case 27: 
            { return STRING_TOKEN;
            } 
            // fall through
          case 54: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
