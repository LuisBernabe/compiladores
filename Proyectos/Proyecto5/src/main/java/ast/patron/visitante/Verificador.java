package ast.patron.visitante;
import ast.patron.compuesto.Variable;
import java.util.ArrayList;


public class Verificador {
    // 3 string, 2 real, 1 integer,0 bool,-1 error 
    
    private final int[] unarios = new int[]{1, 1, 2, -1};
    
    private final int[] not = new int[]{0, 0, 0, 0};


    int[][] resta = new int[][]{
    {-1, -1, -1, -1},
    {-1, 1, 2, -1},
    {-1, 2, 2, -1},
    {-1, -1, -1, -1}
    };
    
     int[][] suma = new int[][]{
    {-1, -1, -1, -1},
    {-1, 1, 2, -1},
    {-1, 2, 2, -1},
    {-1, -1, -1, 3}
    };
    
     int[][] multiplicacion = new int[][]{
    {-1, -1, -1, -1},
    {-1, 1, 2, -1},
    {-1, 2, 2, -1},
    {-1, -1, -1, -1}};
    
     int[][] div = new int[][]{
    {-1, -1, -1, -1},
    {-1, 2, 2, -1},
    {-1, 2, 2, -1},
    {-1, -1, -1, -1}};

     int[][] mod = new int[][]{
    {-1, -1, -1, -1},
    {-1, 1, 2, -1},
    {-1, 2, 2, -1},
    {-1, -1, -1, -1}};

     int[][] divEntera = new int[][]{
    {-1, -1, -1, -1},
    {-1, 1, 1, -1},
    {-1, 1, 1, -1},
    {-1, -1, -1, -1}
    };

     int[][] potencia = new int[][]{
    {-1, -1, -1, -1},
    {-1, 1, 1, -1},
    {-1, 1, 1, -1},
    {-1, -1, -1, -1}
    };

     final int[][] and = new int[][]{
     {0, 1, 2, 3},
     {0, 1, 2, 3},
     {0, 1, 2, 3},
     {0, 1, 2, 3}
    };

     final int[][] or = new int[][]{
    {0, 0, 0, 0},
    {1, 1, 1, 1},
    {2, 2, 2, 2},
    {3, 3, 3, 3}
    };

    int condiciones[][] = {
    {-1, -1, -1, -1},
        {-1, 0, 0, -1},
        {-1, 0, 0, -1},
        {-1, -1, -1, -1}};
    
    
    int asignaciones[][] = {
        {-1, -1, -1, -1},
        {-1, 1, 1, -1},
        {-1, 2, 2, -1},
        {-1, -1, -1, -1}};

         
         


    public int verificaAdd(int i, int j) {
        return suma[i][j];
    }
    
    public int verificarDif(int i, int j) {
        return resta[i][j];
    }

    public int verificaMult(int i, int j) {
        return multiplicacion[i][j];
    }

    public int verificaDiv(int i, int j) {
        return div[i][j];
    }

    public int verificaDivEntera(int i, int j) {
        return divEntera[i][j];
    }

    public int verificaModulo(int i, int j) {
        return mod[i][j];
    }

    public int verificaPotencia(int i, int j) {
        return potencia[i][j];
    }

    public int verificaAnd(int i, int j) {
        return and[i][j];
    }

    public int verificaOr(int i, int j) {
        return or[i][j];
    }

    public int verificaNot(int i) {
        return not[i];
    }
        public int verificaUnario(int i) {
        return unarios[i];
    }

    public int verificaCondicion(int i, int j) {
        return condiciones[i][j];
    }

        public int verificaAsignacion(int i, int j) {
        return asignaciones[i][j];
    }
}
