package ast.patron.compuesto;
import ast.patron.visitante.*;

public class IgualIgualNodo extends NodoBinario{

    public IgualIgualNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public int  accept(Visitor v){
     	return v.visit(this);
    }
}