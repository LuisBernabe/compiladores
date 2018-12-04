package ast.patron.compuesto;
import ast.patron.visitante.Visitor;

public class DosPuntosNodo extends NodoBinario{
    
    public DosPuntosNodo(Nodo l, Nodo r){
        super(l,r);
    }
    public int accept(Visitor v){
     	return v.visit(this);
    }
    
}
