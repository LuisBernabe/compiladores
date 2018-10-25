/********************************************************************************
**  Analizador léxico para p, subconjunto de Python.                           **
*********************************************************************************/
package asintactico;
import java.util.Stack;
%%
%public
%class AnalizadorPython
%debug
%byaccj
%line
%state INDENTA CODIGO DEINDENTA CADENA
%unicode
%{
   /** Variables auxiliares para
    * manejar la indentación.*/
    static Stack<Integer> pila = new Stack<Integer>();
    static Integer actual = 0;
    static String cadena = "";
    static int dedents = 0;
    static int indents = 0;

   

    public int line(){
        return yyline+1;
    }


    /** Función que maneja los niveles de indetación
    * @param int espacios - nivel de indetación actual.
    * @return void
    */
    public void indentacion(int espacios){
        if(pila.empty()){ //ponerle un cero a la pila si esta vacia
             pila.push(new Integer(0));
        }

        Integer tope = pila.peek();

        if(tope != espacios){
	    //Se debe emitir un DEINDENTA por cada nivel mayor al actual
            if(tope > espacios){
                while(pila.peek() > espacios &&  pila.peek()!=0 ){
                    pila.pop();
                    dedents += 1;
                }
                if(pila.peek() == espacios){
		    yybegin(DEINDENTA);
                }else{
		    System.out.println("Error de indentación. Línea "+(yyline+1));
		    System.exit(1);
		}
                return;
            }
   	    //El nivel actual de indentación es mayor a los anteriores.
            pila.push(espacios);
	    yybegin(CODIGO);
            indents = 1;
        }else yybegin(CODIGO);
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
CHAR_LITERAL   	    = ([:letter:] | [:digit:] | "_" | "$" | " " | "#" | {SEPARADORP}) | "\\"
COMENTARIO          = "#".*{SALTO}

SALTO               = \n
IDENTIFICADOR       = ([:letter:] | "_" )([:letter:] | "_" | [0-9])*
%%


{COMENTARIO}      			{}
<CADENA>{
  {CHAR_LITERAL}*\"			{yybegin(CODIGO);
                                         return Parser.CADENA;}
  {SALTO}				{ System.out.println("Cadena mal construida, linea " + (yyline+1) ); System.exit(1);}
}


<YYINITIAL>{
  (" " | "\t" )+[^" ""\t""#""\n"]         { System.out.println("Error de indentación. Línea "+(yyline+1));
					    System.exit(1);}
  {SALTO}                                 {}
  [^" ""\t"]                              { yypushback(1); yybegin(CODIGO);}
}
<DEINDENTA>{
  .                                       { yypushback(1);
  					    if(dedents > 0){
						dedents--;
						return Parser.DEINDENTA;
  					    }
					    yybegin(CODIGO);}
}
<CODIGO>{
" "                   {}
  {SALTO}	      {yybegin(INDENTA); actual=0; return Parser.SALTO;}
{REAL}                {return Parser.REAL;}
{TRUE}                {return Parser.TRUE;}
{FALSE}               {return Parser.FALSE;}
{ENTERO}              {return Parser.ENTERO;}
{POTENCIA}            {return Parser.POTENCIA;}
{MAS}                 {return Parser.MAS;}
{MENOS}               {return Parser.MENOS;}
{MULT}                {return Parser.MULT;}
{DIV}                 {return Parser.DIV;}
{MOD}                 {return Parser.MOD;}
{PISO}                {return Parser.PISO;}
{MENOR}               {return Parser.MENOR;}
{MAYOR}               {return Parser.MAYOR;}
{IGUALIGUAL}          {return Parser.IGUALIGUAL;}
{IGUAL}               {return Parser.IGUAL;}
{MAIGUAL}             {return Parser.MAIGUAL;}
{MEIGUAL}             {return Parser.MEIGUAL;}
{DISTINTO}            {return Parser.DISTINTO;}
{NOT}                 {return Parser.NOT;}
{AND}                 {return Parser.AND;}
{OR}                  {return Parser.OR;}
{IF}                  {return Parser.IF;}
{WHILE}               {return Parser.WHILE;}
{ELSE}                {return Parser.ELSE;} 
{PRINT}               {return Parser.PRINT;} 
{SEPARADORP}          {return Parser.SEPARADORP;}
{PIZQ}                {return Parser.PIZQ;}
{PDER}                {return Parser.PDER;}
{SALTO}               {yybegin(INDENTA); return Parser.SALTO;}
{IDENTIFICADOR}       {return Parser.IDENTIFICADOR;}
}
<INDENTA>{
  {SALTO}                                 { actual = 0;}
  " "				          { actual++;}
  \t					  { actual += 4;}
  .					  { yypushback(1);
					    this.indentacion(actual);
					    if(indents == 1){
					      indents = 0;
					      return Parser.INDENTA;
					    }
					  }
}
<<EOF>>                                   { this.indentacion(0);
					    if(dedents > 0){
					      dedents--;
					      return Parser.DEINDENTA;
					    }else{
                                              return 0;
				            }
					  }
[^]                               {}
