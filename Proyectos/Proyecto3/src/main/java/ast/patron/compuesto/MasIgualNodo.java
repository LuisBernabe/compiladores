package ast.patron.compuesto;
import ast.patron.visitante.*;

public class MasIgualNodo extends NodoBinario{

    public MasIgualNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}