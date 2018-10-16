package com.rad8329.simpleswingapp.negocio.gui.vistas;

import com.rad8329.simpleswingapp.Aplicacion;

/**
 * @author rad8329
 */
public class MainFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 9133921861428284014L;
    private final Aplicacion aplicacion;

    /**
     * Creates new form PrincipalFrame
     *
     * @param aplicacion Aplicación principal
     */
    public MainFrame(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;

        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelEncabezado = new javax.swing.JLabel();
        TabbedPanel = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(240, 236, 232));
        setResizable(false);

        LabelEncabezado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/encabezado.png"))); // NOI18N
        LabelEncabezado.setFocusable(false);

        TabbedPanel.setBackground(new java.awt.Color(240, 236, 232));
        TabbedPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabbedPanel.setToolTipText("");
        TabbedPanel.setAlignmentX(0.0F);
        TabbedPanel.setFocusable(false);
        TabbedPanel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TabbedPanel.setName("panelTabs"); // NOI18N
        aplicacion.getPaneles().forEach((panel) -> {
            TabbedPanel.add(panel.getName(), panel);
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabbedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(LabelEncabezado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TabbedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelEncabezado;
    public static javax.swing.JTabbedPane TabbedPanel;
    // End of variables declaration//GEN-END:variables
}
