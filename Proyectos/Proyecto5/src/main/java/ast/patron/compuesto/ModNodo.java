package ast.patron.compuesto;
import ast.patron.visitante.*;

public class ModNodo extends NodoBinario
{

    public ModNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public int accept(Visitor v){
     	return v.visit(this);
    }
}
