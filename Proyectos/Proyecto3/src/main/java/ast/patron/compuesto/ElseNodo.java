package ast.patron.compuesto;
import ast.patron.visitante.Visitor;

public class ElseNodo extends NodoBinario{
    
    public ElseNodo(Nodo l, Nodo r){
        super(l,r);
    }
    public void accept(Visitor v){
     	v.visit(this);
    }
    
}
