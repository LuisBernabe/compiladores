package ast.patron.compuesto;
import ast.patron.visitante.*;

public class PotNodo extends NodoBinario{

    public PotNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public int accept(Visitor v){
     	return v.visit(this);
    }
}
