package ast.patron.visitante;

import ast.patron.compuesto.*;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.LinkedList;

public class VisitorT implements Visitor {

    private Hashtable<String, Integer> tabla;
    Verificador verificador;
    

    public VisitorT() {
        tabla = new Hashtable<String, Integer>();
        verificador = new Verificador();

    }
    
    public String valorTipo(int i) {
    switch (i) {
        case 3:
            return "Cadena";
        case 2:
            return "Real";
        case 1:
            return "Entero";
        case 0:
            return "Booleano";
        }
    return null;
    }

    public int visit(AddNodo n) {
        int tipo;
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);

        if (n.getPrimerHijo() == null) {
            int hd = tabla.get(hijo_derecho);
            if (hd == -1) {
               System.out.println("Invalido");
            }
             tipo = this.verificador.verificaUnario(hd);
            return tipo;
        }
        
        System.out.println("Validos : " + this.valorTipo(hijo_izquierdo) + " y " + this.valorTipo(hijo_derecho));
         tipo = this.verificador.verificaAdd(hijo_izquierdo, hijo_derecho);
        if (tipo == -1) {
            System.out.println("Error :  " + this.valorTipo(hijo_izquierdo) + " + " + this.valorTipo(hijo_derecho)+"En el AddNodo");
            System.exit(0);
        }
        return tipo;
    }

    public int visit(Nodo n) {
        return 0;

    }

    public int visit(MultNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        System.out.println("Los siguientes son validos" + this.valorTipo(hijo_izquierdo) + "  y  " + this.valorTipo(hijo_derecho));
        int tipo = this.verificador.verificaMult(hijo_izquierdo, hijo_derecho);
        if (tipo == -1) {
            System.out.println("Error : " + this.valorTipo(hijo_izquierdo) + this.valorTipo(hijo_derecho)+ "En MultNodo");
            System.exit(0);
        }
        return tipo;
    }

    public int visit(PotNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        int tipo = this.verificador.verificaPotencia(hijo_izquierdo, hijo_derecho);

        if (tipo == -1) {

            System.out.println("Error :" + this.valorTipo(hijo_izquierdo) + this.valorTipo(hijo_derecho)+"En PotNodo");
            System.exit(0);
        }

        return tipo;
    }

    public int visit(AndNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        int tipo = this.verificador.verificaAnd(hijo_izquierdo, hijo_derecho);

        if (tipo == -1) {
            System.out.println("Error : " + this.valorTipo(hijo_izquierdo) + " and " + this.valorTipo(hijo_derecho)+"En AndNodo");
            System.exit(0);
        }

        return tipo;
    }

    public int visit(OrNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        int tipo = this.verificador.verificaOr(hijo_izquierdo, hijo_derecho);

        if (tipo == -1) {
            System.out.println("Error de tipos:" + this.valorTipo(hijo_izquierdo) + " and " + this.valorTipo(hijo_derecho));
            System.exit(0);
        }

        return tipo;

    }

    public int visit(NotNodo n) {
        n.getPrimerHijo().accept(this);
        return 0;

    }

    public int visit(PrintnNodo n) {
        n.getPrimerHijo().accept(this);
        return 0;

    }

    public int visit(ModNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        int tipo = this.verificador.verificaModulo(hijo_izquierdo, hijo_derecho);

        if (tipo == -1) {
            System.out.println("Error : " + this.valorTipo(hijo_izquierdo) + this.valorTipo(hijo_derecho)+"en ModNodo");
            System.exit(0);
        }

        return tipo;
    }

    public int visit(MenorNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        int tipo = this.verificador.verificaCondicion(hijo_izquierdo, hijo_derecho);

        if (tipo == -1) {
            System.out.println("Error :  " + this.valorTipo(hijo_izquierdo) + this.valorTipo(hijo_derecho)+"En MenorNodo");
            System.exit(0);
        }

        return tipo;
    }

    public int visit(MayorNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        int tipo = this.verificador.verificaCondicion(hijo_izquierdo, hijo_derecho);

        if (tipo == -1) {
            System.out.println("Error :  " + this.valorTipo(hijo_izquierdo) + this.valorTipo(hijo_derecho)+"En MayorNodo");
            System.exit(0);
        }

        return tipo;

    }

    public int visit(WhileNodo n) {

        n.getPrimerHijo().accept(this);
        n.getUltimoHijo().accept(this);
        return 0;

    }

    public int visit(DiferenteNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        int tipo = this.verificador.verificaCondicion(hijo_izquierdo, hijo_derecho);

        if (tipo == -1) {
            System.out.println("Error :  " + this.valorTipo(hijo_izquierdo) + this.valorTipo(hijo_derecho)+"En DiferenteNodo");
            System.exit(0);
        }

        return tipo;

    }

    public int visit(MenorIgualNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        int tipo = this.verificador.verificaCondicion(hijo_izquierdo, hijo_derecho);

        if (tipo == -1) {
            System.out.println("Error :  " + this.valorTipo(hijo_izquierdo) + this.valorTipo(hijo_derecho)+"En MenorIgualNodo");
            System.exit(0);
        }

        return tipo;

    }

    public int visit(MayorIgualNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        int tipo = this.verificador.verificaCondicion(hijo_izquierdo, hijo_derecho);

        if (tipo == -1) {
            System.out.println("Error :  " + this.valorTipo(hijo_izquierdo) + this.valorTipo(hijo_derecho)+"En MayorIgualNodo");
            System.exit(0);
        }

        return tipo;

    }

    public int visit(InNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        int tipo = this.verificador.verificaCondicion(hijo_izquierdo, hijo_derecho);

        if (tipo == -1) {
            System.out.println("Error :  " + this.valorTipo(hijo_izquierdo) + this.valorTipo(hijo_derecho)+"En InNodo");
            System.exit(0);
        }

        return tipo;

    }

    public int visit(IgualIgualNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        int tipo = this.verificador.verificaCondicion(hijo_izquierdo, hijo_derecho);

        if (tipo == -1) {
            System.out.println("Error :  " + this.valorTipo(hijo_izquierdo) + this.valorTipo(hijo_derecho)+"En IgualIgualNodo");
            System.exit(0);
        }

        return tipo;

    }

    public int visit(MasIgualNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        int tipo = this.verificador.verificaAsignacion(hijo_izquierdo, hijo_derecho);

        if (tipo == -1) {
            System.out.println("Error :  " + this.valorTipo(hijo_izquierdo) + this.valorTipo(hijo_derecho)+"En MasIgualNodo");
            System.exit(0);
        }

        return tipo;
    }

    public int visit(DivNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        System.out.println("Los siguientes son validos" + this.valorTipo(hijo_izquierdo) + "  y  " + this.valorTipo(hijo_derecho));
        int tipo = this.verificador.verificaDiv(hijo_izquierdo, hijo_derecho);
        if (tipo == -1) {
            System.out.println("Error de tipos:" + this.valorTipo(hijo_izquierdo) + this.valorTipo(hijo_derecho)+"En  DivNodo");
            System.exit(0);
        }
        return tipo;

    }

    public int visit(DivENodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        System.out.println("Los siguientes son validos" + this.valorTipo(hijo_izquierdo) + "  y  " + this.valorTipo(hijo_derecho));
        int tipo = this.verificador.verificaDivEntera(hijo_izquierdo, hijo_derecho);
        if (tipo == -1) {
            System.out.println("Error de tipos:" + this.valorTipo(hijo_izquierdo) + this.valorTipo(hijo_derecho)+"En DivENodo");
            System.exit(0);
        }
        return tipo;

    }

    public int visit(AsigNodo n) {
        int hijo_derecho = n.getUltimoHijo().accept(this);
        String id = n.getPrimerHijo().getNombre();

        if (this.tabla.get(id) == null) {
            this.tabla.put(id, hijo_derecho);
            return 0;
        }

        int t = this.tabla.get(id);

        if (t != hijo_derecho) {
            System.out.println("Error :" + this.valorTipo(t) + "  y  " + this.valorTipo(hijo_derecho)+"En el AsigNodo");
            System.exit(0);
        }
        return 0;
    }

    public int visit(Compuesto n) {
        for (Iterator i = n.getHijos().iterator(); i.hasNext();) {
            Nodo hijo = (Nodo) i.next();
            hijo.accept(this);
        }
        return 0;

    }

    public int visit(DifNodo n) {
        int hijo_izquierdo = n.getPrimerHijo().accept(this);
        int hijo_derecho = n.getUltimoHijo().accept(this);
        System.out.println("Los siguientes son validos" + this.valorTipo(hijo_izquierdo) + "  y  " + this.valorTipo(hijo_derecho));
        int tipo = this.verificador.verificarDif(hijo_izquierdo, hijo_derecho);
        if (tipo == -1) {
            System.out.println("Error:" + this.valorTipo(hijo_izquierdo) + " + " + this.valorTipo(hijo_derecho)+"En DifNodo");
            System.exit(0);
        }
        return tipo;
    }

    public int visit(IfNodo n) {
        if (n.numeroHijos() == 3) {
            LinkedList<Nodo> hijos = n.getTotal();
            hijos.get(0).accept(this);
            hijos.get(1).accept(this);
            hijos.get(2).accept(this);
        } else {
            LinkedList<Nodo> hijos = n.getTotal();
            hijos.get(0).accept(this);
            hijos.get(1).accept(this);
        }
        return 0;
    }

    public int visit(ElseNodo n) {
        n.getPrimerHijo().accept(this);
        n.getUltimoHijo().accept(this);
        return 0;
    }

    public int visit(Hoja n) {
        return 0;

    }

    public int visit(IdentifierHoja n) {
        if (this.tabla.get(n.getNombre()) == null) {
            System.out.println("Error :  " + n.getNombre() + " Variable no definida En IdentifierHoja");
            System.exit(0);
        }

        return this.tabla.get(n.getNombre());
    }

    public int visit(IntHoja n) {
        return 1;

    }

    public int visit(FloatHoja n) {
        return 2;
    }

    public int visit(StringHoja n) {
        return 3;
    }

    public int visit(BoolHoja n) {
        return 0;
    }

    public int visit(NodoBinario n) {
        return 0;

    }

    public int visit(NodoStmts n) {
        return 0;
    }

    @Override
    public int visit(DosPuntosNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

  
}