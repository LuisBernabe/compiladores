package ast.patron.compuesto;
import ast.patron.visitante.*;

public class DivNodo extends NodoBinario{
    
    public DivNodo(Nodo l, Nodo r){
        super(l,r);
    }
    public int accept(Visitor v){
     	 return v.visit(this);
    }
}
