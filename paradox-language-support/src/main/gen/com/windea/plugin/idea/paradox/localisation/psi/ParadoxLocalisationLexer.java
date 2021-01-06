/* The following code was generated by JFlex 1.7.0 tweaked for IntelliJ platform */

package com.windea.plugin.idea.paradox.localisation.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.*;
import static com.windea.plugin.idea.paradox.localisation.psi.ParadoxLocalisationTypes.*;

//Stellaris官方本地化文件中本身就存在语法解析错误，需要保证存在错误的情况下仍然会解析后续的本地化文本，草


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>ParadoxLocalisationLexer.flex</tt>
 */
public class ParadoxLocalisationLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int WAITING_LOCALE_COLON = 2;
  public static final int WAITING_LOCALE_EOL = 4;
  public static final int WAITING_PROPERTY_KEY = 6;
  public static final int WAITING_PROPERTY_COLON = 8;
  public static final int WAITING_PROPERTY_NUMBER = 10;
  public static final int WAITING_PROPERTY_SPACE = 12;
  public static final int WAITING_PROPERTY_VALUE = 14;
  public static final int WAITING_PROPERTY_EOL = 16;
  public static final int WAITING_RICH_TEXT = 18;
  public static final int WAITING_PROPERTY_REFERENCE = 20;
  public static final int WAITING_PROPERTY_REFERENCE_PARAMETER = 22;
  public static final int WAITING_ICON = 24;
  public static final int WAITING_ICON_NAME_FINISHED = 26;
  public static final int WAITING_ICON_PARAMETER = 28;
  public static final int WAITING_SERIAL_NUMBER = 30;
  public static final int WAITING_COMMAND_KEY = 32;
  public static final int WAITING_COMMAND_KEY_SEPARATOR = 34;
  public static final int WAITING_COLOR_CODE = 36;
  public static final int WAITING_COLORFUL_TEXT = 38;
  public static final int WAITING_CHECK_ICON_START = 40;
  public static final int WAITING_CHECK_SERIAL_NUMBER_START = 42;
  public static final int WAITING_CHECK_COLORFUL_TEXT_START = 44;
  public static final int WAITING_CHECK_RIGHT_QUOTE = 46;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3,  3,  4,  4,  5,  5,  6,  6,  7,  7, 
     2,  2,  8,  8,  9,  9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 
    15, 15, 16, 16, 17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22
  };

  /** 
   * Translates characters to character classes
   * Chosen bits are [9, 6, 6]
   * Total runtime size is 4256 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[(ZZ_CMAP_Y[(ZZ_CMAP_Z[ch>>12]<<6)|((ch>>6)&0x3f)]<<6)|(ch&0x3f)];
  }

  /* The ZZ_CMAP_Z table has 272 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\1\1\1\2\1\3\6\4\1\5\4\4\1\6\1\7\1\10\4\4\1\11\6\4\1\12\1\13\361\4");

  /* The ZZ_CMAP_Y table has 768 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\1\1\1\2\26\3\1\4\1\3\1\5\3\3\1\6\5\3\1\7\1\3\1\7\1\3\1\7\1\3\1\7\1\3"+
    "\1\7\1\3\1\7\1\3\1\7\1\3\1\7\1\3\1\7\1\3\1\7\1\3\1\10\1\3\1\10\1\4\4\3\1\6"+
    "\1\10\27\3\1\11\4\3\1\4\1\10\4\3\1\12\1\3\1\10\2\3\1\13\2\3\1\10\1\5\2\3\1"+
    "\13\16\3\1\14\1\15\76\3\1\11\227\3\1\4\12\3\1\10\1\6\2\3\1\16\1\3\1\10\5\3"+
    "\1\5\114\3\1\10\25\3\1\4\56\3\1\7\1\3\1\5\1\17\2\3\1\10\3\3\1\5\5\3\1\10\1"+
    "\3\1\10\5\3\1\10\1\3\1\6\1\5\6\3\1\4\15\3\1\10\67\3\1\4\3\3\1\10\61\3\1\20"+
    "\105\3\1\10\32\3");

  /* The ZZ_CMAP_A table has 1088 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\4\1\3\2\2\1\3\22\0\1\4\1\33\1\6\1\5\1\24\1\27\1\0\1\11\2\0\2\16\1"+
    "\0\1\23\1\20\1\22\12\12\1\30\2\0\1\16\3\0\32\25\1\21\1\13\1\32\1\0\1\10\1"+
    "\0\15\17\1\15\3\17\1\15\1\17\1\15\6\17\1\0\1\31\10\0\1\2\32\0\1\1\2\0\1\26"+
    "\3\0\1\14\170\0\12\7\106\0\12\7\6\0\12\7\134\0\12\7\40\0\12\7\46\0\1\1\105"+
    "\0\12\7\60\0\12\7\6\0\12\7\46\0\13\1\35\0\2\2\5\0\1\1\57\0\1\1\60\0\12\7\26"+
    "\0\12\7\74\0\12\7\16\0\62\7");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\27\0\2\1\2\2\1\3\1\4\1\1\1\5\1\2"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\2\16\1\5\1\17\1\1\1\20\1\21\1\22\1\23"+
    "\1\24\1\25\1\26\1\25\1\27\1\30\1\31\1\32"+
    "\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42"+
    "\1\43\1\44\1\45\1\46\1\47\1\1\1\50\1\51"+
    "\1\52\1\53\1\54\1\55\1\56\1\57\1\60\2\0"+
    "\1\61\1\62\1\63\1\55\1\56\1\57\1\60\1\56";

  private static int [] zzUnpackAction() {
    int [] result = new int[90];
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
    "\0\0\0\34\0\70\0\124\0\160\0\214\0\250\0\304"+
    "\0\340\0\374\0\u0118\0\u0134\0\u0150\0\u016c\0\u0188\0\u01a4"+
    "\0\u01c0\0\u01dc\0\u01f8\0\u0214\0\u0230\0\u024c\0\u0268\0\u0284"+
    "\0\u02a0\0\u02bc\0\u02d8\0\u02f4\0\u0310\0\u032c\0\u0348\0\u0364"+
    "\0\u0284\0\u0380\0\u039c\0\u03b8\0\u0284\0\u03d4\0\u03f0\0\u0284"+
    "\0\u040c\0\u0428\0\u0444\0\u0284\0\u0460\0\u0284\0\u0284\0\u0284"+
    "\0\u0284\0\u0284\0\u047c\0\u0284\0\u0498\0\u0284\0\u0284\0\u0284"+
    "\0\u04b4\0\u04d0\0\u04ec\0\u0284\0\u0284\0\u0284\0\u0284\0\u0508"+
    "\0\u0284\0\u0284\0\u0524\0\u0284\0\u0284\0\u0540\0\u055c\0\u0284"+
    "\0\u0284\0\u055c\0\u0284\0\u0284\0\u0578\0\u0594\0\u05b0\0\u05cc"+
    "\0\u02a0\0\u032c\0\u0284\0\u0284\0\u0284\0\u0284\0\u05e8\0\u0284"+
    "\0\u0284\0\u0284";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[90];
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
    "\1\30\1\31\2\32\1\33\1\34\2\30\1\35\4\30"+
    "\1\35\1\30\1\35\15\30\1\36\2\37\1\40\23\30"+
    "\1\41\4\30\1\36\2\37\1\40\1\42\27\30\1\31"+
    "\2\32\1\33\1\43\2\30\3\44\2\30\1\44\1\30"+
    "\2\44\2\30\1\44\1\30\1\44\7\30\1\36\2\37"+
    "\1\40\23\30\1\45\4\30\1\36\2\37\1\46\2\30"+
    "\1\47\2\30\1\47\22\30\1\36\2\37\1\46\30\30"+
    "\1\36\2\37\1\40\1\30\1\50\25\30\1\51\1\52"+
    "\1\53\1\37\1\52\1\51\1\54\4\51\1\55\1\56"+
    "\4\51\1\57\2\51\1\60\1\51\1\61\1\62\4\51"+
    "\1\30\1\36\2\37\1\63\1\30\1\64\1\30\3\65"+
    "\1\30\1\56\1\65\1\30\2\65\1\66\1\30\1\65"+
    "\1\67\1\65\3\30\1\70\3\30\1\36\2\37\1\71"+
    "\1\30\1\64\3\30\1\72\1\30\1\56\5\72\1\30"+
    "\1\72\1\67\1\72\1\30\1\72\2\30\1\72\2\30"+
    "\1\36\2\37\1\71\1\30\1\64\1\30\1\73\1\30"+
    "\2\73\1\56\1\73\1\30\1\73\1\30\1\74\2\73"+
    "\1\75\1\73\1\76\2\30\1\77\3\30\1\36\2\37"+
    "\1\71\1\30\1\64\5\30\1\56\4\30\1\74\2\30"+
    "\1\75\1\30\1\76\2\30\1\77\3\30\1\36\2\37"+
    "\1\36\1\30\1\64\3\30\1\100\1\30\1\56\5\100"+
    "\1\30\3\100\1\76\1\100\2\30\1\100\2\30\1\36"+
    "\2\37\1\71\1\30\1\64\5\30\1\56\1\101\1\30"+
    "\1\101\5\30\1\101\1\30\1\102\5\30\1\36\2\37"+
    "\1\36\1\30\1\64\1\30\1\103\1\30\1\103\1\30"+
    "\1\56\1\103\1\30\1\103\4\30\2\103\4\30\1\104"+
    "\2\30\1\36\2\37\1\36\1\30\1\64\5\30\1\56"+
    "\3\30\1\105\11\30\1\104\2\30\1\36\2\37\1\106"+
    "\1\30\1\64\5\30\1\107\1\110\1\30\1\110\5\30"+
    "\1\110\6\30\1\51\1\52\1\53\1\37\1\52\1\51"+
    "\1\111\4\51\1\55\1\112\4\51\1\57\2\51\1\60"+
    "\1\51\1\113\1\114\4\51\26\30\1\115\34\30\1\116"+
    "\20\30\1\117\25\30\1\120\25\30\35\0\1\121\2\32"+
    "\1\121\30\0\4\32\30\0\1\121\2\32\1\33\27\0"+
    "\3\34\1\0\30\34\10\0\1\35\4\0\1\35\1\0"+
    "\1\35\15\0\1\122\2\37\1\122\30\0\4\37\30\0"+
    "\1\122\2\37\1\40\27\0\3\42\1\0\2\42\1\0"+
    "\25\42\3\43\1\0\30\43\10\0\3\44\2\0\1\44"+
    "\1\0\2\44\2\0\1\44\1\0\1\44\7\0\1\122"+
    "\2\37\1\46\36\0\1\47\2\0\1\47\21\0\3\51"+
    "\1\0\2\51\1\0\4\51\2\0\4\51\1\0\2\51"+
    "\1\0\1\51\2\0\5\51\1\52\1\53\1\37\1\52"+
    "\1\51\1\0\4\51\2\0\4\51\1\0\2\51\1\0"+
    "\1\51\2\0\5\51\2\53\1\37\1\53\1\51\1\0"+
    "\4\51\2\0\4\51\1\0\2\51\1\0\1\51\2\0"+
    "\4\51\2\123\2\0\2\123\1\124\5\123\2\124\3\123"+
    "\1\124\2\123\1\124\1\123\2\124\4\123\1\0\1\122"+
    "\2\37\1\63\3\0\3\65\2\0\1\65\1\0\2\65"+
    "\2\0\1\65\1\0\1\65\12\0\1\65\3\0\3\65"+
    "\2\0\1\65\1\0\2\65\2\0\1\65\1\0\1\65"+
    "\7\0\1\122\2\37\1\71\41\0\1\72\2\0\5\72"+
    "\1\0\1\72\1\0\1\72\1\0\1\72\2\0\1\72"+
    "\11\0\1\73\1\0\2\73\1\0\1\73\1\0\1\73"+
    "\2\0\2\73\1\0\1\73\20\0\1\100\2\0\5\100"+
    "\1\0\3\100\1\0\1\100\2\0\1\100\11\0\1\103"+
    "\1\0\1\103\2\0\1\103\1\0\1\103\4\0\2\103"+
    "\7\0\1\122\2\37\1\106\62\0\1\125\2\126\2\0"+
    "\30\126\2\127\2\0\30\127\2\130\2\0\30\130\3\120"+
    "\1\0\2\120\1\131\25\120\2\132\2\0\30\132";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1540];
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
    "\27\0\1\11\10\1\1\11\3\1\1\11\2\1\1\11"+
    "\3\1\1\11\1\1\5\11\1\1\1\11\1\1\3\11"+
    "\3\1\4\11\1\1\2\11\1\1\2\11\2\1\2\11"+
    "\1\1\2\11\4\1\2\0\4\11\1\1\3\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[90];
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
  int depth = 0;
  int codeLocation = 0;
  int propertyReferenceLocation = 0;
  boolean inIconName = false;
  boolean isColorfulText = false;

  public int nextStateForText(){
    return depth <= 0 ? WAITING_RICH_TEXT : WAITING_COLORFUL_TEXT;
  }

  public int nextStateForCheck(){
    return isColorfulText?WAITING_COLORFUL_TEXT:WAITING_RICH_TEXT;
  }

  public int nextStateForCode(){
    if(codeLocation == 0) return nextStateForText();
    else if (codeLocation == 1) return WAITING_PROPERTY_REFERENCE;
    else if (codeLocation == 2) return WAITING_ICON;
    else return nextStateForText();
  }

  public int nextStateForPropertyReference(){
    if(propertyReferenceLocation == 0) return nextStateForText();
    else if (propertyReferenceLocation == 2) return WAITING_ICON;
    else return nextStateForText();
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public ParadoxLocalisationLexer(java.io.Reader in) {
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
            { return TokenType.BAD_CHARACTER;
            } 
            // fall through
          case 52: break;
          case 2: 
            { return WHITE_SPACE;
            } 
            // fall through
          case 53: break;
          case 3: 
            { return ROOT_COMMENT;
            } 
            // fall through
          case 54: break;
          case 4: 
            { yybegin(WAITING_LOCALE_COLON); return LOCALE_ID;
            } 
            // fall through
          case 55: break;
          case 5: 
            { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE;
            } 
            // fall through
          case 56: break;
          case 6: 
            { yybegin(WAITING_LOCALE_EOL); return COLON;
            } 
            // fall through
          case 57: break;
          case 7: 
            { return END_OF_LINE_COMMENT;
            } 
            // fall through
          case 58: break;
          case 8: 
            { return COMMENT;
            } 
            // fall through
          case 59: break;
          case 9: 
            { yybegin(WAITING_PROPERTY_COLON); return PROPERTY_KEY_ID;
            } 
            // fall through
          case 60: break;
          case 10: 
            { yybegin(WAITING_PROPERTY_NUMBER); return COLON;
            } 
            // fall through
          case 61: break;
          case 11: 
            { yybegin(WAITING_PROPERTY_VALUE); return WHITE_SPACE;
            } 
            // fall through
          case 62: break;
          case 12: 
            { yybegin(WAITING_PROPERTY_SPACE); return NUMBER;
            } 
            // fall through
          case 63: break;
          case 13: 
            { yybegin(WAITING_RICH_TEXT); return LEFT_QUOTE;
            } 
            // fall through
          case 64: break;
          case 14: 
            { return STRING_TOKEN;
            } 
            // fall through
          case 65: break;
          case 15: 
            { isColorfulText=false; yypushback(yylength()); yybegin(WAITING_CHECK_RIGHT_QUOTE);
            } 
            // fall through
          case 66: break;
          case 16: 
            { isColorfulText=false; yypushback(yylength()); yybegin(WAITING_CHECK_COLORFUL_TEXT_START);
            } 
            // fall through
          case 67: break;
          case 17: 
            { codeLocation=0; yybegin(WAITING_COMMAND_KEY); return COMMAND_START;
            } 
            // fall through
          case 68: break;
          case 18: 
            { propertyReferenceLocation=0; yybegin(WAITING_PROPERTY_REFERENCE); return PROPERTY_REFERENCE_START;
            } 
            // fall through
          case 69: break;
          case 19: 
            { isColorfulText=false; yypushback(yylength()); yybegin(WAITING_CHECK_ICON_START);
            } 
            // fall through
          case 70: break;
          case 20: 
            { isColorfulText=false; yypushback(yylength()); yybegin(WAITING_CHECK_SERIAL_NUMBER_START);
            } 
            // fall through
          case 71: break;
          case 21: 
            { return PROPERTY_REFERENCE_ID;
            } 
            // fall through
          case 72: break;
          case 22: 
            { yybegin(WAITING_PROPERTY_EOL); return RIGHT_QUOTE;
            } 
            // fall through
          case 73: break;
          case 23: 
            { codeLocation=1; yybegin(WAITING_COMMAND_KEY); return COMMAND_START;
            } 
            // fall through
          case 74: break;
          case 24: 
            { yybegin(nextStateForPropertyReference()); return PROPERTY_REFERENCE_END;
            } 
            // fall through
          case 75: break;
          case 25: 
            { yybegin(WAITING_PROPERTY_REFERENCE_PARAMETER); return PARAMETER_SEPARATOR;
            } 
            // fall through
          case 76: break;
          case 26: 
            { yybegin(nextStateForText()); return WHITE_SPACE;
            } 
            // fall through
          case 77: break;
          case 27: 
            { return PROPERTY_REFERENCE_PARAMETER;
            } 
            // fall through
          case 78: break;
          case 28: 
            { yybegin(WAITING_ICON_NAME_FINISHED); return ICON_ID;
            } 
            // fall through
          case 79: break;
          case 29: 
            { codeLocation=2; yybegin(WAITING_COMMAND_KEY); return COMMAND_START;
            } 
            // fall through
          case 80: break;
          case 30: 
            { propertyReferenceLocation=2; yybegin(WAITING_PROPERTY_REFERENCE); return PROPERTY_REFERENCE_START;
            } 
            // fall through
          case 81: break;
          case 31: 
            { yybegin(nextStateForText()); return ICON_END;
            } 
            // fall through
          case 82: break;
          case 32: 
            { yybegin(WAITING_ICON_PARAMETER); return PARAMETER_SEPARATOR;
            } 
            // fall through
          case 83: break;
          case 33: 
            { return ICON_PARAMETER;
            } 
            // fall through
          case 84: break;
          case 34: 
            { return SERIAL_NUMBER_ID;
            } 
            // fall through
          case 85: break;
          case 35: 
            { yybegin(nextStateForText()); return SERIAL_NUMBER_END;
            } 
            // fall through
          case 86: break;
          case 36: 
            { yybegin(WAITING_COMMAND_KEY_SEPARATOR); return COMMAND_KEY_TOKEN;
            } 
            // fall through
          case 87: break;
          case 37: 
            { yybegin(nextStateForCode()); return COMMAND_END;
            } 
            // fall through
          case 88: break;
          case 38: 
            { yybegin(WAITING_COMMAND_KEY); return COMMAND_KEY_SEPARATOR;
            } 
            // fall through
          case 89: break;
          case 39: 
            { yybegin(WAITING_COLORFUL_TEXT); return WHITE_SPACE;
            } 
            // fall through
          case 90: break;
          case 40: 
            { yybegin(WAITING_COLORFUL_TEXT); return COLOR_CODE;
            } 
            // fall through
          case 91: break;
          case 41: 
            { isColorfulText=true; yypushback(yylength()); yybegin(WAITING_CHECK_RIGHT_QUOTE);
            } 
            // fall through
          case 92: break;
          case 42: 
            { isColorfulText=true; yypushback(yylength()); yybegin(WAITING_CHECK_COLORFUL_TEXT_START);
            } 
            // fall through
          case 93: break;
          case 43: 
            { isColorfulText=true; yypushback(yylength()); yybegin(WAITING_CHECK_ICON_START);
            } 
            // fall through
          case 94: break;
          case 44: 
            { isColorfulText=true; yypushback(yylength()); yybegin(WAITING_CHECK_SERIAL_NUMBER_START);
            } 
            // fall through
          case 95: break;
          case 45: 
            { //特殊处理
    //如果匹配的字符串的第2个字符存在且不为空白或双引号，则认为代表图标的开始
    //否则认为是常规字符串
    boolean isIconStart = yylength() == 2 && !Character.isWhitespace(yycharat(1)) && yycharat(1) != '"' ;
    yypushback(yylength()-1);
    if(isIconStart){
    	  yybegin(WAITING_ICON);
    	  return ICON_START;
    }else{
        yybegin(nextStateForCheck());
        return STRING_TOKEN;
    }
            } 
            // fall through
          case 96: break;
          case 46: 
            { //特殊处理
    //如果匹配的字符串的第3个字符存在且为百分号，则认为整个字符串代表一个编号
    //否则认为是常规字符串
    boolean isSerialNumberStart = yylength() == 3 && yycharat(2) == '%';
    yypushback(yylength()-1);
    if(isSerialNumberStart){
        yybegin(WAITING_SERIAL_NUMBER);
        return SERIAL_NUMBER_START;
    }else{
        yybegin(nextStateForCheck());
        return STRING_TOKEN;
    }
            } 
            // fall through
          case 97: break;
          case 47: 
            { //特殊处理
    //如果匹配的字符串的第2个字符存在且不为空白或双引号，则认为代表彩色文本的开始
    //否则认为是常规字符串
    boolean isColorfulTextStart = yylength() == 2 && !Character.isWhitespace(yycharat(1)) && yycharat(1) != '"' ;
    yypushback(yylength()-1);
    if(isColorfulTextStart){
        yybegin(WAITING_COLOR_CODE);
        depth++;
        return COLORFUL_TEXT_START;
    }else{
        yybegin(nextStateForCheck());
        return STRING_TOKEN;
    }
            } 
            // fall through
          case 98: break;
          case 48: 
            { //特殊处理
    //如果匹配到的字符串不是仅包含双引号，且最后一个字符是双引号，则表示开始的双引号不代表字符串的结束
    //否则认为是常规字符串
    boolean isRightQuote = yylength() == 1 || yycharat(yylength()-1) != '"';
    yypushback(yylength()-1);
    if(isRightQuote){
        yybegin(WAITING_PROPERTY_EOL);
        return RIGHT_QUOTE;
    }else{
        yybegin(nextStateForCheck());
        return STRING_TOKEN;
    }
            } 
            // fall through
          case 99: break;
          case 49: 
            { return INVALID_ESCAPE_TOKEN;
            } 
            // fall through
          case 100: break;
          case 50: 
            { return VALID_ESCAPE_TOKEN;
            } 
            // fall through
          case 101: break;
          case 51: 
            { depth--; yybegin(nextStateForText()); return COLORFUL_TEXT_END;
            } 
            // fall through
          case 102: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
