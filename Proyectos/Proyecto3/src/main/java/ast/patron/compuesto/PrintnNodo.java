package ast.patron.compuesto;
import ast.patron.visitante.*;

public class PrintnNodo   extends NodoBinario
{

    public PrintnNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
