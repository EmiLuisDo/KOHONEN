/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red_kohonen;

import VISUAL.ControladoraVisual;
/**
 *
 * @author Emiliano
 */
public class RED_KOHONEN {

    Matriz pEntradaComp;   //una columna son todos los pesos relacionados a una neurona de la capa de competicion
                           //una fila son todos los pesos relacionados a una neurona de la capa de entrada
    Matriz pCompComp;
    float alfa = (float) 1;
    int entradas;
    int klusters;
    ControladoraVisual visual;
    
    public RED_KOHONEN(int entradas, int klusters)
    {
        this.pEntradaComp = new Matriz(entradas, klusters);
        this.pCompComp = new Matriz (klusters, klusters);
        this.entradas = entradas;
        this.klusters = klusters;
        inicializarPesos();
    }
    
  
    
    public float calcularDistanciaEuclidea(float[] vEntrada, float[] vPeso)
    {
        double sumatoria=0;
        float raiz=0;
        for (int a=0; a<vEntrada.length; a++)
        {
            sumatoria += Math.pow( (vEntrada[a] - vPeso[a]) ,2);
        }
        return (float)sumatoria;
    }
    
    
    public float[] calcularDistancias(float [] sujetoEntrenamiento)
    {
        float [] distancias = new float[this.klusters];
        float [] vectorPesosNeuronaCompeticion = null;
        
        for(int k=0; k<this.pEntradaComp.getN(); k++)
        {
            vectorPesosNeuronaCompeticion = this.pEntradaComp.getColumna(k);
            
            distancias[k] = calcularDistanciaEuclidea(sujetoEntrenamiento, vectorPesosNeuronaCompeticion);
        }
        return distancias;
    }
    
    public void entrenar(float [] sujetoEntrenamiento)
    {
        int ganadora;
        float[] auxDistancias;
        auxDistancias = calcularDistancias(sujetoEntrenamiento);
        ganadora = encontrarGanadora(auxDistancias);
        actualizarPesos(ganadora, sujetoEntrenamiento);
        
        System.out.println("Entrenando Sujeto: [" + Float.toString(sujetoEntrenamiento[0]) + " , " +  Float.toString(sujetoEntrenamiento[1]) + "]"  );
        System.out.println("Distancias: [" + Float.toString(auxDistancias[0]) + " , " +  Float.toString(auxDistancias[1]) + " , " +  Float.toString(auxDistancias[2]) + " , " +  Float.toString(auxDistancias[3]) + "]"  );
        System.out.println("Ganador: " + Integer.toString(ganadora));
        System.out.println("Matriz resultante:");
        System.out.println(this.pEntradaComp.toString() + "\n");
        
        this.visual.actualizarTabla(pEntradaComp);/////////////////////////////////////////////
    }
            
    public int encontrarGanadora(float [] distancias)
    {
        //se comparan todas las salidas de las neuronas de la capa de competicion y la ganadora es la de salida menor
        //a la ganadora se la etiqueta como la correspondiente para la entrada evaluada
        int neuronaDistanciaMenor = 0;
        float distanciaMenor = distancias[0];
        for(int i=1; i<distancias.length;i++)
        {
            if( distancias[i] < distanciaMenor)
            {
                distanciaMenor = distancias[i];
                neuronaDistanciaMenor = i;
            }
        }
        return neuronaDistanciaMenor;
    }
    
    public void actualizarPesos(int ganadora, float [] entrada )
    {
        float auxViejoPeso;
        float auxNuevoPeso;
        for(int m=0; m<this.pEntradaComp.getM(); m++)
        {
            auxViejoPeso = this.pEntradaComp.getData(m, ganadora);
            auxNuevoPeso = auxViejoPeso + (alfa * (entrada[m] - auxViejoPeso ) );
            this.pEntradaComp.setData(m, ganadora, auxNuevoPeso);
        }
    }

    public void setpEntradaComp(Matriz pEntradaComp) {
        this.pEntradaComp = pEntradaComp;
    }

    public void setpCompComp(Matriz pCompComp) {
        this.pCompComp = pCompComp;
    }

    public void setAlfa(float alfa) {
        this.alfa = alfa;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public void setKlusters(int klusters) {
        this.klusters = klusters;
    }

    public void setVisual(ControladoraVisual visual) {
        this.visual = visual;
    }

    public Matriz getpEntradaComp() {
        return pEntradaComp;
    }

    public Matriz getpCompComp() {
        return pCompComp;
    }

    public float getAlfa() {
        return alfa;
    }

    public int getEntradas() {
        return entradas;
    }

    public int getKlusters() {
        return klusters;
    }

    public ControladoraVisual getVisual() {
        return visual;
    }
    
    private void inicializarPesos() 
    {
        this.pEntradaComp = Matriz.random(entradas, klusters);
        
        
        
        pEntradaComp.getData()[0][0] =(float)0.5;  
        pEntradaComp.getData()[0][1] =(float)-0.5 ;   
        pEntradaComp.getData()[0][2] =(float)-0.9 ;   
        pEntradaComp.getData()[0][3] =(float)-0.2 ;    
        pEntradaComp.getData()[1][0] =(float)-0.3;  
        pEntradaComp.getData()[1][1] =(float)0.8 ;   
        pEntradaComp.getData()[1][2] =(float)-0.7;   
        pEntradaComp.getData()[1][3] =(float)-0.8;
        
        System.out.println("Matriz inicial:");
        System.out.println(this.pEntradaComp.toString());
        
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RED_KOHONEN control = new RED_KOHONEN(2,4);
        ControladoraVisual controv = new ControladoraVisual(control);
        
        
        
        control.setVisual(controv);
        float[] sujeto1 = {(float)-0.54,(float)0.36};
        float[] sujeto2 = {(float)0.16,(float)0.7};
        float[] sujeto3 = {(float)-0.8,(float)-0.18};
        float[] sujeto4 = {(float)-0.36,(float)-0.52};
        float[] sujeto5 = {(float)-0.64,(float)0.46};
        float[] sujeto6 = {(float)-0.40,(float)0.34};
        float[] sujeto7 = {(float)-0.54,(float)0.36};
        control.entrenar(sujeto1);
        control.entrenar(sujeto2);
        control.entrenar(sujeto3);
        control.entrenar(sujeto4);
        control.entrenar(sujeto5);
        control.entrenar(sujeto6);
        control.entrenar(sujeto7);
    }

    public void iterarEntrenamiento() {
        
    }

}