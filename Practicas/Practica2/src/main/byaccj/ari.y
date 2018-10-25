%{
  import java.lang.Math;
  import java.io.*;
%}

%token<sval> NODO ENTERO MAS MENOS MUL DIV
%type<sval> KMAS KMENOS KMUL KDIV T F

/* Gramática con recursión izquierda */
%%

F: T {$$=$1; 
        System.out.println("[reduccion a ] F de : "+$1);
        System.out.println("[OK] "+$$);}
;

T: KMAS T {
        int r=0;
        int a=Integer.parseInt($1.substring(0,$1.length()-1));
        int b=Integer.parseInt($2);
        r=a+b;
        $$=String.valueOf(r);
        System.out.println("[reduccion a ] T de : "+$1+" "+$2);
 }
 | KMENOS T {
        int r=0;
        int a=Integer.parseInt($1.substring(0,$1.length()-1));
        int b=Integer.parseInt($2);
        r=a-b;
        $$=String.valueOf(r);
        System.out.println("[reduccion a ] T de : "+$1+" "+$2);
 }
 | KMUL T {
        int r=0;
        int a=Integer.parseInt($1.substring(0,$1.length()-1));
        int b=Integer.parseInt($2);
        r=a*b;
        $$=String.valueOf(r);
        System.out.println("[reduccion a ] T de : "+$1+" "+$2);
 }
 | KDIV T {
        int r=0;
        int a=Integer.parseInt($1.substring(0,$1.length()-1));
        int b=Integer.parseInt($2);
        r=a/b;
        $$=String.valueOf(r);
        System.out.println("[reduccion a ] T de : "+$1+" "+$2);
 }
 | ENTERO {
    System.out.println("[reduccion a ] T de : "+$1);
    $$=$1;
}
 | MENOS ENTERO {
    System.out.println("[reduccion a ] T de : "+$1+" "+$2);
    $$=$1 + $2;
 }

;

KMAS: T MAS {$$=$1 + $2;
        System.out.println("[reduccion a ] KMAS de : "+$1+" "+$2);}
;

KMENOS: T MENOS {$$=$1 + $2;
        System.out.println("[reduccion a ] KMENOS de : "+$1+" "+$2);}
;

KMUL: T MUL {$$=$1 + $2;
        System.out.println("[reduccion a ] KMUL de : "+$1+" "+$2);}
;

KDIV: T DIV {$$=$1 + $2;
        System.out.println("[reduccion a ] KDIV de : "+$1+" "+$2);}
;


%%
/* Referencia a analizador léxico */
private Nodos lexer;

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
    lexer = new Nodos(r, this);
}

/* Creación del parser e inicialización del reconocimiento */
public static void main(String args[]) throws IOException {
    Parser parser = new Parser(new FileReader(args[0]));
    parser.yyparse();
}
