package ast.patron.compuesto;
import ast.patron.visitante.*;

public class AndNodo extends NodoBinario{

    public AndNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public int accept(Visitor v){
     	return v.visit(this);
    }
}

