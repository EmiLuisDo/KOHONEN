/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISUAL;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import red_kohonen.Matriz;
import red_kohonen.RED_KOHONEN;

/**
 *
 * @author Emiliano
 */
public class ControladoraVisual extends javax.swing.JFrame {

    RED_KOHONEN controladoraLogica;
    /**
     * Creates new form ControladoraVisual
     */
    public ControladoraVisual(RED_KOHONEN logica) {
        initComponents();
        this.controladoraLogica = logica;
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPesos = new javax.swing.JTable();
        btnEntrenar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblPesos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblPesos);

        btnEntrenar.setText("Entrenar");
        btnEntrenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrenarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(btnEntrenar, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(299, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEntrenar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrenarActionPerformed
        float [] pruebarandom = new float [this.controladoraLogica.getpEntradaComp().getM()];
        for(int i = 0; i<this.controladoraLogica.getpEntradaComp().getM(); i++)
        {
             pruebarandom [i]= (float)Math.random();
        }
        this.controladoraLogica.entrenar(pruebarandom);
        
        
    }//GEN-LAST:event_btnEntrenarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrenar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPesos;
    // End of variables declaration//GEN-END:variables

    public void actualizarTabla(Matriz pEntradaComp) {
        Object [] nombres = new Object [pEntradaComp.getN()+1];
        for(int c=1; c<nombres.length;c++)
        {
            nombres [c] = "NS"+Integer.toString(c);
        }
        Object [][] datos = new Object[pEntradaComp.getM()][pEntradaComp.getN()+1];
        for(int f=0; f<datos.length;f++)
        {
            datos [f][0] = "NE"+Integer.toString(f+1);
        }
        
        for(int f=0; f<pEntradaComp.getM();f++)
        {
            for(int c=0; c<pEntradaComp.getN();c++)
            {
                datos[f][c+1] = pEntradaComp.getData()[f][c];
            }
        }
        
        DefaultTableModel modelo = new DefaultTableModel(datos, nombres);
        this.tblPesos.setModel(modelo);
        
    }
}
