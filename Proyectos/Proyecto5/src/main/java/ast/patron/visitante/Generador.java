package ast.patron.visitante;
import ast.patron.compuesto.*;
import java.util.Iterator;
import java.io.*;
import java.util.LinkedList;

public class Generador implements Visitor {

    private static String text=".text";
    private static String data=".data";
    private static String asm="";
    Registros registros = new Registros();
    String instrucciones = "main:\n"+data+"\n"+text+"\n";
    
    public Generador(){
        
    }
    public Generador(String a, String c){
        try {
            FileWriter fw = new FileWriter(a.substring(0,a.length()-2) + ".asm");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(c);
            bw.flush();
            bw.close();
            System.out.println(c);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


    public int visit(DifNodo n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        
        int tipo = n.getType();
        boolean entero = tipo == 2 ? false : true;

        String objetivo = registros.getObjetivo(entero);
        String[] siguientes = registros.getNsiguientes(2, entero);

        
        registros.setObjetivo(siguientes[0], entero);
        hi.accept(this);

        
        registros.setObjetivo(siguientes[1], entero);
        hd.accept(this);

        String opcode;
        opcode = tipo == 2 ? "sub.s" : "sub";

        System.out.println(opcode + " " + objetivo + ", "
                + siguientes[0] + ", " + siguientes[1]);
        return 0;
    }

    public int visit(AddNodo n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();        
        int tipo = n.getType();
        boolean entero =  tipo ==2 ? false : true;
        String objetivo = registros.getObjetivo(entero);
        String[] siguientes = registros.getNsiguientes(2, entero);
        registros.setObjetivo(siguientes[0], entero);
        hi.accept(this);
        registros.setObjetivo(siguientes[1], entero);
        hd.accept(this);
        String opcode;
        opcode =  tipo==2 ? "add.s" : "add";
        System.out.println(opcode + " " + objetivo + ", "
                + siguientes[0] + ", " + siguientes[1]);
        return 0; 
    }

  
    public int visit(MultNodo n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();        
        int tipo = n.getType();
        boolean entero =  tipo ==2 ? false : true;
        String objetivo = registros.getObjetivo(entero);
        String[] siguientes = registros.getNsiguientes(2, entero);
        registros.setObjetivo(siguientes[0], entero);
        hi.accept(this);
        registros.setObjetivo(siguientes[1], entero);
        hd.accept(this);
        String opcode;
        opcode =  tipo==2 ? "mul.s" : "mul";
        System.out.println(opcode + " " + objetivo + ", "
                + siguientes[0] + ", " + siguientes[1]);
        return 0; 
    }



    public int visit(PrintnNodo n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();
        int tipo = n.getType();
        boolean entero =  tipo ==2 ? false : true;
        String objetivo = registros.getObjetivo(entero);
        String[] siguientes = registros.getNsiguientes(1, entero);
        registros.setObjetivo(siguientes[0], entero);
        hd.accept(this);
        if (entero) {
            instrucciones += "move $a0, "+ objetivo+"\n li $v0, 1\n" + "syscall\n";
        } else {
            instrucciones += "move $f12, "+ objetivo+"\n li $v0, 1\n" + "syscall\n";
        }
        return 0;
    }
    
    public int visit(DivNodo n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();
        int tipo = n.getType();
        boolean entero =  tipo ==2 ? false : true;
        String objetivo = registros.getObjetivo(entero);
        String[] siguientes = registros.getNsiguientes(2, entero);
        registros.setObjetivo(siguientes[0], entero);
        hi.accept(this);
        registros.setObjetivo(siguientes[1], entero);
        hd.accept(this);
        String opcode;
        opcode =  tipo==2 ? "div.s" : "div";
        System.out.println(opcode + " " + objetivo + ", "
                + siguientes[0] + ", " + siguientes[1]);
        return 0; 
    }
    
    public int visit(IntHoja n) {
        instrucciones += "li "+registros.getObjetivo(true)+","+n.getValor().ival+"\n";
        return 0;
    }
    
    public int visit(DivENodo n) {
        return 0;
    }

    public int visit(ModNodo n) {
        return 0;
    }

    public int visit(PotNodo n) {
        return 0;
    }

    public int visit(AndNodo n) {
        return 0;
    }

    public int visit(OrNodo n) {
        return 0;
    }

    public int visit(NotNodo n) {
        return 0;
    }

    public int visit(MenorNodo n) {
        return 0;
    }

    public int visit(MayorNodo n) {
        return 0;
    }

    public int visit(MenorIgualNodo n) {
        return 0;
    }

    public int visit(MayorIgualNodo n) {
        return 0;
    }

    public int visit(IgualIgualNodo n) {
        return 0;
    }

    public int visit(InNodo n) {
        return 0;
    }

    public int visit(FloatHoja n) {
        return 0;
    }

    public int visit(MasIgualNodo n) {
        return 0;
    }

    public int visit(BoolHoja n) {
        return 0;
    }

    public int visit(WhileNodo n) {
        return 0;
    }


    public int visit(StringHoja n) {
        return 0;
    }

    public int visit(DiferenteNodo n) {
        return 0;
    }

    public int visit(AsigNodo n) {
        return 0;
    }

    public int visit(Hoja n) {
        return 0;
    }

    public int visit(IdentifierHoja n) {
        return 0;
    }


    public int visit(Nodo n) {
        return 0;
    }

    public int visit(NodoBinario n) {
        return 0;
    }

    public int visit(NodoStmts n) {
        return 0;
    }

    public int visit(IfNodo n) {
        return 0;
    }

    public int visit(DosPuntosNodo n) {
        return 0;
    }

    public int visit(ElseNodo n) {
        return 0;
    }

    
    public int visit(Compuesto n){
        for(Nodo nodo : n.getHijos().getAll()){
            nodo.accept(this);
        }
        return 0;
    }
    public String print() {
        return instrucciones;
    }

}
