package ast.patron.compuesto;
import ast.patron.visitante.*;

public class MenorNodo extends NodoBinario{

    public MenorNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public int accept(Visitor v){
     	return v.visit(this);
    }
}