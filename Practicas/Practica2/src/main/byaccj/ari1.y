%{
  import java.lang.Math;
  import java.io.*;
%}

%token<sval> NODO ENTERO MAS MENOS MUL DIV
%type<sval> KMAS KMENOS KMUL KDIV T F

/* Gramática con recursión derecha */
%%

F: T {$$=$1; System.out.println("[OK] "+$$);}
;

T: T KMAS  {
        int r=0;
        int a=Integer.parseInt($2.substring(1,$2.length()));
        int b=Integer.parseInt($1);
        r=a+b;
        $$=String.valueOf(r);
        System.out.println("[reduccion a ] T de : "+$1+" "+$2);
 }
 | T KMENOS {
        int r=0;
        int a=Integer.parseInt($2.substring(1,$2.length()));
        int b=Integer.parseInt($1);
        r=a-b;
        $$=String.valueOf(r);
        System.out.println("[reduccion a ] T de : "+$1+" "+$2);
 }
 | T KMUL {
        int r=0;
        int a=Integer.parseInt($2.substring(1,$2.length()));
        int b=Integer.parseInt($1);
        r=a*b;
        $$=String.valueOf(r);
        System.out.println("[reduccion a ] T de : "+$1+" "+$2);
 }
 | T KDIV {
        int r=0;
        int a=Integer.parseInt($2.substring(1,$2.length()));
        int b=Integer.parseInt($1);
        r=a/b;
        $$=String.valueOf(r);
        System.out.println("[reduccion a ] T de : "+$1+" "+$2);
 }
 | ENTERO {
    System.out.println("[reduccion a ] T de : "+$1);
    $$=$1;}
 | MENOS ENTERO {
    System.out.println("[reduccion a ] T de : "+$1+" "+$2);
    $$=$1 + $2; }

;

KMAS: MAS T {$$=$1 + $2;
        System.out.println("[reduccion a ] KMAS de : "+$1+" "+$2);}
;

KMENOS: MENOS T {$$=$1 + $2;
        System.out.println("[reduccion a ] KMENOS de : "+$1+" "+$2);}
;

KMUL: MUL T {$$=$1 + $2;
        System.out.println("[reduccion a ] KMUL de : "+$1+" "+$2);}
;

KDIV: DIV T {$$=$1 + $2;
        System.out.println("[reduccion a ] KDIV de : "+$1+" "+$2);}
;


%%
/* Referencia a analizador léxico */
private Nodos1 lexer;

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
public ParserD(Reader r) {
    lexer = new Nodos1(r, this);
}

/* Creación del parser e inicialización del reconocimiento */
public static void main(String args[]) throws IOException {
    ParserD parser = new ParserD(new FileReader(args[0]));
    parser.yyparse();
}

