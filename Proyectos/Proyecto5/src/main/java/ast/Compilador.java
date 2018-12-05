package ast;
import java.io.*;
import ast.patron.compuesto.*;
import ast.patron.visitante.*;


public class Compilador{

    static String archivo;
    Parser parser;
    Nodo raízAST;
    VisitorPrint v_print;
    VisitorT concreto;
    Generador g1;
    Generador g2;
    

    Compilador(Reader fuente){
        parser = new Parser(fuente);
        v_print = new VisitorPrint();
        concreto=new VisitorT();
        g1 = new Generador();
    }

    public void ConstruyeAST(boolean debug){
        parser.yydebug = debug;
        parser.yyparse(); // análisis léxico, sintáctio y constucción del AST
        raízAST = parser.raíz;
    }

     public void imprimeAST(){
        parser.raíz.accept(concreto);
        parser.raíz.accept(g1);
        String st = g1.print();
        g2 = new Generador(archivo,st);
        
        
    }

    public static void main(String[] args){
        archivo = "src/main/resources/test.p";
        try{
            Reader a = new FileReader(archivo);
            Compilador c  = new Compilador(a);
            c.ConstruyeAST(true);
            c.imprimeAST();
        }catch(FileNotFoundException e){
            System.err.println("El archivo " + archivo +" no fue encontrado. ");
        }catch(ArrayIndexOutOfBoundsException e){
            System.err.println("Uso: java Compilador [archivo.p]: ");
        }
    }
}
