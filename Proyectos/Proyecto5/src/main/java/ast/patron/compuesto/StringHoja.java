package ast.patron.compuesto;
import ast.patron.visitante.*;

public class StringHoja extends Hoja{
    public StringHoja(String i){
      	valor = new Variable(i);
	tipo = 1;
    }

    public int accept(Visitor v){
     	return v.visit(this);
    }
}

