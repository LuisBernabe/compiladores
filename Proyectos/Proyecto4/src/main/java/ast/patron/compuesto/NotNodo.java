package ast.patron.compuesto;
import ast.patron.visitante.*;

public class NotNodo extends Compuesto
{

    public NotNodo(Nodo l){
	super(l);
    }

    public int accept(Visitor v){
     	return v.visit(this);
    }
}

