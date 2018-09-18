package asintactico;
%%
%{
    private ParserD parser;

    public Nodos1 (java.io.Reader r, ParserD p){
    	   this(r);
    	   parser = p;
    }

%}
%class Nodos1
%standalone
%public
%unicode
ENTERO  = [1-9][0-9]* | 0+
MAS = "+" 
MENOS = "-"
MUL = "*"
DIV = "/"
%%
a               { parser.yylval = new ParserDVal(yytext()); return parser.NODO; }
{ENTERO}        { parser.yylval = new ParserDVal(yytext()); return parser.ENTERO; }
{MAS}       { parser.yylval = new ParserDVal(yytext()); return parser.MAS; }
{MENOS}       { parser.yylval = new ParserDVal(yytext()); return parser.MENOS; }
{MUL}       { parser.yylval = new ParserDVal(yytext()); return parser.MUL; }
{DIV}       { parser.yylval = new ParserDVal(yytext()); return parser.DIV; }
.             { }

