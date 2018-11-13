package ast.patron.compuesto;
import ast.patron.visitante.*;


public class DiferenteNodo extends NodoBinario
{

    public DiferenteNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public int accept(Visitor v){
	return v.visit(this);
    }
}
