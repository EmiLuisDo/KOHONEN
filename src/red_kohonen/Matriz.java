/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red_kohonen;

/**
 *
 * @author Emiliano
 */
final public class Matriz {
    private final int M;             // number of rows
    private final int N;             // number of columns
    private final float[][] data;   // M-by-N array

    // create M-by-N matrix of 0's
    public Matriz(int M, int N) {
        this.M = M;
        this.N = N;
        data = new float[M][N];
    }    
    public Matriz(int N) {
        this.M = 1;
        this.N = N;
        data = new float[M][N];
    }

    // create matrix based on 2d array
    public Matriz(float[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new float[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                    this.data[i][j] = data[i][j];
    }

    // copy constructor
    private Matriz(Matriz A) 
    { 
        this(A.data); 
    }

    // create and return a random M-by-N matrix with values between 0 and 1
    public static Matriz random(int M, int N) {
        Matriz A = new Matriz(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = (float)Math.random();
        return A;
    }

    // create and return the N-by-N identity matrix
    public static Matriz identity(int N) {
        Matriz I = new Matriz(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // swap rows i and j
    private void swap(int i, int j) {
        float[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // create and return the transpose of the invoking matrix
    public Matriz transpose() {
        Matriz A = new Matriz(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // return C = A + B
    public Matriz plus(Matriz B) {
        Matriz A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matriz C = new Matriz(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }


    // return C = A - B
    public Matriz minus(Matriz B) {
        Matriz A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matriz C = new Matriz(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    // does A = B exactly?
    public boolean eq(Matriz B) {
        Matriz A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }

    // return C = A * B
    public Matriz times(Matriz B) {
        Matriz A = this;
        if (A.N != B.M) throw new RuntimeException("Illegal matrix dimensions.");
        Matriz C = new Matriz(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }

    // print matrix to standard output
    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) 
                System.out.println(data[i][j]);
            System.out.println();
        }
    }
    
    public float getData(int i, int j)
    {
        return this.data[i][j];
    }    
    public void setData(int i, int j, float dato)
    {
        this.data[i][j] = dato;
    }
    
    @Override
    public String toString()
    {
        String str = new String();
        str += "--------------------";
        str += "\n";
        for(int i=0; i<this.M;i++)
        {
            for(int j=0;j<this.N;j++)
            {
                str += this.data[i][j];
                str += "\t";
            }
            str += "\n";
        }
        str += "--------------------";
        return str;
    }
    
    /**
     * M es cantidad de filas, en este caso cantidad de entradas.
     */
    public int getM() {
        return M;
    }
    
    /**
     * N es cantidad de columnas, en este caso cantidad de clusters.
     */
    public int getN() {
        return N;
    }

    public float[][] getData() {
        return data;
    }

    float[] getColumna(int k) {
        float [] auxColumna = new float[M];
        for(int i=0; i<M;i++)
        {
            auxColumna[i] = this.data[i][k];
        }
        return auxColumna;
    }
    
}