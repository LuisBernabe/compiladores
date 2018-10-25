//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package asintactico;



//#line 2 "src/main/byaccj/Parser.y"
  import java.lang.Math;
  import java.io.*;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ENTERO=257;
public final static short REAL=258;
public final static short IDENTIFICADOR=259;
public final static short TRUE=260;
public final static short FALSE=261;
public final static short POTENCIA=262;
public final static short MAS=263;
public final static short MENOS=264;
public final static short MULT=265;
public final static short DIV=266;
public final static short MOD=267;
public final static short PISO=268;
public final static short MENOR=269;
public final static short MAYOR=270;
public final static short IGUAL=271;
public final static short MAIGUAL=272;
public final static short MEIGUAL=273;
public final static short DISTINTO=274;
public final static short NOT=275;
public final static short AND=276;
public final static short OR=277;
public final static short IF=278;
public final static short WHILE=279;
public final static short ELSE=280;
public final static short PRINT=281;
public final static short SEPARADORP=282;
public final static short PIZQ=283;
public final static short PDER=284;
public final static short DEINDENTA=285;
public final static short INDENTA=286;
public final static short OTRO=287;
public final static short IGUALIGUAL=288;
public final static short CADENA=289;
public final static short SALTO=290;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    2,    2,    3,    5,    5,
    6,    6,    7,    4,    4,    9,    9,   10,   11,   11,
   12,   12,    8,   13,   13,   14,   15,   15,   17,   16,
   16,   18,   18,   19,   20,   20,   22,   22,   23,   23,
   24,   24,   24,   24,   25,   25,   25,   26,   26,   21,
   21,   21,   21,   21,   21,   27,   27,   27,   27,   27,
   27,   27,
};
final static short yylen[] = {                            2,
    1,    1,    2,    2,    1,    1,    1,    2,    1,    1,
    3,    1,    2,    1,    1,    4,    7,    4,    1,    4,
    1,    2,    1,    2,    1,    2,    2,    1,    2,    2,
    1,    2,    1,    2,    2,    1,    2,    2,    2,    1,
    2,    2,    2,    2,    2,    2,    1,    3,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    3,
};
final static short yydefred[] = {                         0,
   57,   58,   56,   60,   61,    0,    0,    0,    0,    0,
    0,    0,   59,    0,    0,    1,    0,    6,    7,    0,
    9,   10,    0,   14,   15,   23,    0,    0,    0,   31,
    0,    0,    0,    0,    0,    0,   47,    0,   45,   46,
   30,    0,    0,   13,    0,    3,    4,    8,    0,   24,
   26,    0,   27,   32,   50,   51,   53,   54,   55,   52,
   34,   35,   37,   38,   39,   41,   42,   43,   44,    0,
    0,    0,   62,   11,   29,   48,    0,   19,    0,   18,
    0,    0,   21,    0,    0,   20,   22,   17,
};
final static short yydgoto[] = {                         15,
   16,   17,   18,   19,   20,   21,   22,   23,   24,   25,
   79,   84,   26,   27,   28,   29,   53,   30,   31,   32,
   61,   33,   34,   35,   36,   37,   38,
};
final static short yysindex[] = {                      -251,
    0,    0,    0,    0,    0, -160, -160, -168, -168, -168,
 -168, -168,    0, -251,    0,    0, -251,    0,    0, -279,
    0,    0, -242,    0,    0,    0, -168, -244, -235,    0,
 -160, -164, -160, -191, -160, -149,    0, -212,    0,    0,
    0, -215, -200,    0, -214,    0,    0,    0, -168,    0,
    0, -168,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -243,
 -238, -238,    0,    0,    0,    0, -208,    0, -193,    0,
 -195, -180,    0, -204, -238,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  111,    0,    0,  113,    0,    0,    0,
    0,    0, -176,    0,    0,    0,    0, -115,  -79,    0,
    0,  -80,    0,  -89,    0, -111,    0, -133,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   17,  -23,  -36,    0,    0,    0,    0,   -7,    0,    0,
   40,    0,    0,    0,   93,   -8,    0,    0,    0,   91,
    0,    0,   94,    0,   41,   56,    0,
};
final static int YYTABLESIZE=291;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         41,
   16,   42,   43,   44,   45,    1,    2,    3,    4,    5,
   48,    6,    7,    1,    2,    3,    4,    5,    1,    2,
    3,    4,    5,    8,    6,    7,    9,   10,   49,   11,
   46,   12,   51,   47,   78,   78,    8,   13,   14,   12,
   52,   74,   11,   75,   12,   13,   39,   40,   78,   70,
   13,   77,    1,    2,    3,    4,    5,   83,    6,    7,
   87,    1,    2,    3,    4,    5,   71,    6,    7,   73,
    8,   63,   64,    9,   10,   65,   11,   81,   12,    8,
   86,   72,    9,   10,   13,   11,   82,   12,    1,    2,
    3,    4,    5,   13,    6,    7,    1,    2,    3,    4,
    5,   85,    6,    7,   55,   56,    8,   57,   58,   59,
    2,   80,    5,   12,   12,   66,   67,   68,   69,   50,
   13,   54,   12,   60,   88,   76,   62,    0,   13,   49,
   49,   49,   49,   49,   49,   49,   49,   49,   49,   49,
   49,    0,   49,   49,    0,    0,    0,    0,   49,    0,
   49,   40,   40,    0,   49,   25,   49,   40,   40,   40,
   40,   40,   40,    0,   40,   40,   25,    0,   25,    0,
   40,    0,   40,    0,   25,    0,   40,    0,   40,   36,
   36,   36,   36,   36,   36,    0,   36,   36,    0,    0,
   33,   28,   36,    0,   36,   33,   33,   28,   36,    0,
   36,   33,   28,   33,   28,    0,    0,    0,    0,   33,
   28,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   16,   16,   16,
   16,   16,    0,   16,   16,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   16,    0,    0,   16,   16,
    0,   16,    0,   16,    0,   16,    0,    0,    0,   16,
   16,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          8,
    0,    9,   10,   11,   12,  257,  258,  259,  260,  261,
  290,  263,  264,  257,  258,  259,  260,  261,  257,  258,
  259,  260,  261,  275,  263,  264,  278,  279,  271,  281,
   14,  283,  277,   17,   71,   72,  275,  289,  290,  283,
  276,   49,  281,   52,  283,  289,    6,    7,   85,  262,
  289,  290,  257,  258,  259,  260,  261,   81,  263,  264,
   84,  257,  258,  259,  260,  261,  282,  263,  264,  284,
  275,  263,  264,  278,  279,   35,  281,  286,  283,  275,
  285,  282,  278,  279,  289,  281,  280,  283,  257,  258,
  259,  260,  261,  289,  263,  264,  257,  258,  259,  260,
  261,  282,  263,  264,  269,  270,  275,  272,  273,  274,
    0,   72,    0,  290,  283,  265,  266,  267,  268,   27,
  289,   31,  283,  288,   85,   70,   33,   -1,  289,  263,
  264,  265,  266,  267,  268,  269,  270,  271,  272,  273,
  274,   -1,  276,  277,   -1,   -1,   -1,   -1,  282,   -1,
  284,  263,  264,   -1,  288,  271,  290,  269,  270,  271,
  272,  273,  274,   -1,  276,  277,  282,   -1,  284,   -1,
  282,   -1,  284,   -1,  290,   -1,  288,   -1,  290,  269,
  270,  271,  272,  273,  274,   -1,  276,  277,   -1,   -1,
  271,  271,  282,   -1,  284,  276,  277,  277,  288,   -1,
  290,  282,  282,  284,  284,   -1,   -1,   -1,   -1,  290,
  290,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,   -1,  263,  264,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  275,   -1,   -1,  278,  279,
   -1,  281,   -1,  283,   -1,  285,   -1,   -1,   -1,  289,
  290,
};
}
final static short YYFINAL=15;
final static short YYMAXTOKEN=290;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"ENTERO","REAL","IDENTIFICADOR","TRUE","FALSE","POTENCIA","MAS",
"MENOS","MULT","DIV","MOD","PISO","MENOR","MAYOR","IGUAL","MAIGUAL","MEIGUAL",
"DISTINTO","NOT","AND","OR","IF","WHILE","ELSE","PRINT","SEPARADORP","PIZQ",
"PDER","DEINDENTA","INDENTA","OTRO","IGUALIGUAL","CADENA","SALTO",
};
final static String yyrule[] = {
"$accept : ffile_input",
"ffile_input : file_input",
"file_input : SALTO",
"file_input : SALTO file_input",
"file_input : stmt file_input",
"file_input : stmt",
"stmt : simple_stmt",
"stmt : compound_stmt",
"simple_stmt : small_stmt SALTO",
"small_stmt : expr_stmt",
"small_stmt : print_stmt",
"expr_stmt : test IGUAL test",
"expr_stmt : test",
"print_stmt : PRINT test",
"compound_stmt : if_stmt",
"compound_stmt : while_stmt",
"if_stmt : IF test SEPARADORP suite",
"if_stmt : IF test SEPARADORP suite ELSE SEPARADORP suite",
"while_stmt : WHILE test SEPARADORP suite",
"suite : simple_stmt",
"suite : SALTO INDENTA kstmt DEINDENTA",
"kstmt : stmt",
"kstmt : kstmt stmt",
"test : or_test",
"or_test : kor_test and_test",
"or_test : and_test",
"kor_test : and_test OR",
"and_test : not_test kand_test",
"and_test : not_test",
"kand_test : AND not_test",
"not_test : NOT not_test",
"not_test : comparison",
"comparison : kcomparison expr",
"comparison : expr",
"kcomparison : expr comp_op",
"expr : kexpr term",
"expr : term",
"kexpr : term MAS",
"kexpr : term MENOS",
"term : kterm factor",
"term : factor",
"kterm : factor MULT",
"kterm : factor DIV",
"kterm : factor MOD",
"kterm : factor PISO",
"factor : MAS factor",
"factor : MENOS factor",
"factor : power",
"power : atom POTENCIA power",
"power : atom",
"comp_op : MENOR",
"comp_op : MAYOR",
"comp_op : IGUALIGUAL",
"comp_op : MAIGUAL",
"comp_op : MEIGUAL",
"comp_op : DISTINTO",
"atom : IDENTIFICADOR",
"atom : ENTERO",
"atom : REAL",
"atom : CADENA",
"atom : TRUE",
"atom : FALSE",
"atom : PIZQ test PDER",
};

//#line 156 "src/main/byaccj/Parser.y"
/* Referencia a analizador léxico */
private Flexer lexer;

private int yylex () {
    int yyl_return = -1;
    try {
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
}

/* Función para reportar error */
public void yyerror (String error) {
    System.err.println ("[ERROR]  " + error);
    System.exit(1);
}

/* Constructor */
public Parser(Reader r) {
    lexer = new Flexer(r, this);
}

/* Creación del parser e inicialización del reconocimiento */
public static void main(String args[]) throws IOException {
    Parser parser = new Parser(new FileReader("src/main/resources/test.txt"));
    parser.yydebug = true;
    parser.yyparse();
}
//#line 380 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 14 "src/main/byaccj/Parser.y"
{ System.out.println("[OK] ");}
break;
//#line 533 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
