package ast.patron.compuesto;
import ast.patron.visitante.*;


public class DiferenteNodo extends NodoBinario
{

    public DiferenteNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
	v.visit(this);
    }
}
