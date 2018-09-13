package testmaven;
import java.util.Stack;
%%
%public
%class AnalizadorPython
%standalone
%state INDENTA CODIGO DEINDENTA CADENA
%unicode
%{
    static Stack<Integer> pila = new Stack<Integer>();
    static Integer actual = 0;
    static String cadena = "";
    static int dedents = 0;
    static int indents = 0;




    /** Función que maneja los niveles de indetación   */
    public void indentacion(int espacios){
        if(pila.empty()){ //ponerle un cero a la pila si esta vacia
             pila.push(new Integer(0));
        }

        Integer tope = pila.peek();

        if(tope != espacios){
            if(tope > espacios){
                while(pila.peek() > espacios &&  pila.peek()!=0 ){
                    pila.pop();
                    dedents += 1;
                }
                if(pila.peek() == espacios){
        yybegin(DEINDENTA);
                }else{
        System.out.print("Error de indentación. Línea "+(yyline+1));
        System.exit(1);
    }
        //El nivel actual de indentación es mayor a los anteriores.
            pila.push(espacios);
      yybegin(CODIGO);
            indents = 1;
        }else 
            yybegin(CODIGO);
    }
}
%}

ENTERO          = 0+ | [0-9]+
REAL            = {ENTERO}? \. {ENTERO}?
TRUE                = "True"
FALSE               = "False"
IGUAL               = "="
POTENCIA            = "**"
MAS                 = "+"
MENOS               = "-"
MULT                = "*"
DIV                 = "/"
MOD                 = "%"
PISO                = "//"
MENOR               = "<"
MAYOR               = ">"
IGUALIGUAL          = "=="
MAIGUAL             = ">="
MEIGUAL             = "<="
DISTINTO            = "!="
NOT                 = "not"
AND                 = "and"
OR                  = "or"
IF                  = "if"
WHILE               = "while"
ELSE                = "else"
PRINT               = "print"
SEPARADORP          = ":"
PIZQ                = "("
PDER                = ")"
ESC                 = (\\)
CHAR_LITERAL        = ([:letter:] | [:digit:] | "_" | "$" | " " | "#" | {SEPARADORP}) | "\\"
COMENTARIO          = "#".*{SALTO}

SALTO               = \n
IDENTIFICADOR       = ([:letter:] | "_" )([:letter:] | "_" | [0-9])*

%%



{COMENTARIO}            {}
<CADENA>{
  {CHAR_LITERAL}*\"     {yybegin(CODIGO);
                                         System.out.print("");}
  {SALTO}       { System.out.print("Cadena mal construida, linea " + (yyline+1) ); System.exit(1);}
}


<YYINITIAL>{
  (" " | "\t" )+[^" ""\t""#""\n"]         { System.out.print("Error de indentación. Línea "+(yyline+1));
              System.exit(1);}
  {SALTO}                                 {}
  [^" ""\t"]                              { yypushback(1); yybegin(CODIGO);}
}
<DEINDENTA>{
  .                                       { yypushback(1);
                if(dedents > 0){
            dedents--;
            System.out.print("");
                }
              yybegin(CODIGO);}
}
<CODIGO>{


" "                   {}
  {SALTO}       {yybegin(INDENTA); actual=0; System.out.println("");}
{REAL}                {System.out.print("REAL ("+yytext()+")");}
{TRUE}                {System.out.print("BOOLEANO ("+yytext()+")");}
{FALSE}               {System.out.print("BOOLEANO ("+yytext()+")");}
{ENTERO}              {System.out.print("ENTERO ("+yytext()+")");}
{POTENCIA}            {System.out.print("OPERADOR ("+yytext()+")");}
{MAS}                 {System.out.print("OPERADOR ("+yytext()+")");}
{MENOS}               {System.out.print("OPERADOR ("+yytext()+")");}
{MULT}                {System.out.print("OPERADOR ("+yytext()+")");}
{DIV}                 {System.out.print("OPERADOR ("+yytext()+")");}
{MOD}                 {System.out.print("OPERADOR ("+yytext()+")");}
{PISO}                {System.out.print("OPERADOR ("+yytext()+")");}
{MENOR}               {System.out.print("OPERADOR ("+yytext()+")");}
{MAYOR}               {System.out.print("OPERADOR ("+yytext()+")");}
{IGUALIGUAL}          {System.out.print("OPERADOR ("+yytext()+")");}
{IGUAL}               {System.out.print("OPERADOR ("+yytext()+")");}
{MAIGUAL}             {System.out.print("OPERADOR ("+yytext()+")");}
{MEIGUAL}             {System.out.print("OPERADOR ("+yytext()+")");}
{DISTINTO}            {System.out.print("OPERADOR ("+yytext()+")");}
{NOT}                 {System.out.print("RESERVADA ("+yytext()+")");}
{AND}                 {System.out.print("RESERVADA ("+yytext()+")");}
{OR}                  {System.out.print("RESERVADA ("+yytext()+")");}
{IF}                  {System.out.print("RESERVADA ("+yytext()+")");}
{WHILE}               {System.out.print("RESERVADA ("+yytext()+")");}
{ELSE}                {System.out.print("RESERVADA ("+yytext()+")");} 
{PRINT}               {System.out.print("RESERVADA ("+yytext()+")");} 
{SEPARADORP}          {System.out.print("SEPARADOR ("+yytext()+")");}
{PIZQ}                {System.out.print("PARENTESISIZQUIERDO ("+yytext()+")");}
{PDER}                {System.out.print("PARENTESISDERECHO ("+yytext()+")");}
{SALTO}               {yybegin(INDENTA); System.out.print("SALTORESERVADA ("+yytext()+")");}
{IDENTIFICADOR}       {System.out.print("IDENTIFICADOR ("+yytext()+")");}
}
<INDENTA>{
  {SALTO}                                 { actual = 0;}
  " "                 { actual++;}
  \t            { actual += 4;}
  .           { yypushback(1);
              this.indentacion(actual);
              if(indents == 1){
                indents = 0;
                System.out.print("SALTOIDENTA("+indents+")");
              }
            }
}
<<EOF>>                                   { this.indentacion(0);
              if(dedents > 0){
                dedents--;
                System.out.print("SALTODEIDENTA ("+dedents+")");
              }else{
                    }
            }
[^]                               {}