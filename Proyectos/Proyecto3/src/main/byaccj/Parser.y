%{
  import java.lang.Math;
  import java.io.*;
%}

%token ENTERO REAL IDENTIFICADOR TRUE FALSE POTENCIA MAS MENOS MULT DIV MOD PISO
%token MENOR MAYOR IGUAL MAIGUAL MEIGUAL DISTINTO NOT AND OR IF WHILE ELSE
%token PRINT SEPARADORP PIZQ PDER DEINDENTA INDENTA OTRO IGUALIGUAL CADENA SALTO


/* Gramática con recursión izquierda */
%%

ffile_input: file_input  { System.out.println("[OK] ");}
;

file_input: SALTO 
          |  SALTO  file_input
          | stmt  file_input
          | stmt


;

;


stmt: simple_stmt 
    | compound_stmt
;

simple_stmt: small_stmt SALTO
;


small_stmt: expr_stmt 
          | print_stmt
;

/* expr_stmt: test [= test] */
expr_stmt: test IGUAL test
         | test
;


print_stmt: PRINT test
;

compound_stmt: if_stmt
             | while_stmt

;


if_stmt: IF test SEPARADORP suite 
       | IF test SEPARADORP suite ELSE SEPARADORP suite 
;


while_stmt: WHILE test  SEPARADORP suite
;


suite: simple_stmt 
        | SALTO INDENTA kstmt DEINDENTA
;

kstmt: stmt
     | kstmt stmt







test: or_test
;

or_test: kor_test and_test 
        | and_test
;

kor_test: and_test OR
;

and_test: not_test kand_test 
        | not_test
;

kand_test: AND not_test
;

not_test: NOT not_test 
        | comparison
;

comparison: kcomparison expr
          | expr            
;

kcomparison: expr comp_op 
;

expr: kexpr term 
    | term
;

kexpr: term MAS
    | term MENOS
;

term: kterm factor
    | factor
;

kterm: factor MULT
        | factor DIV
        | factor MOD 
        | factor PISO
;



factor: MAS factor 
        | MENOS factor 
        | power
;


power: atom POTENCIA power
     | atom
     
;


comp_op: MENOR
        |MAYOR
        |IGUALIGUAL
        |MAIGUAL
        |MEIGUAL
        |DISTINTO
;

atom: IDENTIFICADOR
    | ENTERO
    | REAL
    | CADENA
    | TRUE
    | FALSE
    | PIZQ test PDER
;


%%
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
