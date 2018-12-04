package ast.patron.compuesto;
import ast.patron.visitante.*;

public class MultNodo extends NodoBinario{
    
    public MultNodo(Nodo l, Nodo r){
        super(l,r);
    }
    public int accept(Visitor v){
     	return v.visit(this);
    }
    
    
    
}
