package ast.patron.compuesto;
import ast.patron.visitante.Visitor;

public class DosPuntosNodo extends NodoBinario{
    
    public DosPuntosNodo(Nodo l, Nodo r){
        super(l,r);
    }
    public void accept(Visitor v){
     	v.visit(this);
    }
    
}
