package ast.patron.compuesto;
import ast.patron.visitante.*;

public class InNodo extends NodoBinario{

    public InNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}