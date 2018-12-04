package ast.patron.compuesto;
import ast.patron.visitante.*;

public class MasIgualNodo extends NodoBinario{

    public MasIgualNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public int accept(Visitor v){
     	return v.visit(this);
    }
}