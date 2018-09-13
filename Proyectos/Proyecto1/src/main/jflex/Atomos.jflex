package testmaven;
import java.util.Stack;
%%
%public
%class AnalizadorPython
%standalone

%unicode

%{

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
