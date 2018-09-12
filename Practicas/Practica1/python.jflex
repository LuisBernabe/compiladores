//Analizador lexico PYTHON




%%

%public
%class AnalizadorC
%unicode
%standalone



PUNTO			=	\.
DIGIT           	=       [0-9]
CERO             	=        0+
ENTERO			= 	{CERO} | {DIGIT}+

ID       	= 	([:letter:] | "_" )([:letter:] | "_" | [0-9])*

COMENTARIO 		=     	"#".*

%%

{ENTERO} {System.out.println("ENTERO");}
{ID} {System.out.println("ID");}
{ENTERO} {System.out.print("ENTERO");}
{COMENTARIO}.* {System.out.println("COMENTARIO");}
