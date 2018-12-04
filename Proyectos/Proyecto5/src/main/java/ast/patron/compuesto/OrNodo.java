package ast.patron.compuesto;
import ast.patron.visitante.*;

public class OrNodo extends NodoBinario{

    public OrNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public int accept(Visitor v){
     	return v.visit(this);
    }
}
