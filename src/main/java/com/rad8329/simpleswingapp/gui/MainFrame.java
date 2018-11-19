package com.rad8329.simpleswingapp.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JPanel;

public class MainFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = -498955594596889802L;

    public MainFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(240, 236, 232));
        setResizable(false);

        LabelEncabezado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/encabezado.png"))); // NOI18N
        LabelEncabezado.setAlignmentX(0.1F);
        LabelEncabezado.setFocusable(false);

        TabbedPanel.setBackground(new java.awt.Color(240, 236, 232));
        TabbedPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabbedPanel.setToolTipText("");
        TabbedPanel.setAlignmentX(0.0F);
        TabbedPanel.setFocusable(false);
        TabbedPanel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TabbedPanel.setName("panelTabs"); // NOI18N
        com.rad8329.simpleswingapp.Aplicacion.get().getPaneles().forEach(panel -> {
            TabbedPanel.add(panel.getName(),panel);
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TabbedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
                    .addComponent(LabelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    public static void agregarPanel(JPanel panel) {
        TabbedPanel.add(panel.getName(), panel);
        TabbedPanel.setSelectedComponent(panel);
    }

    public static void removerPanel(int index, int focus) {
        try {
            TabbedPanel.remove(index);
            TabbedPanel.setSelectedComponent(TabbedPanel.getComponent(focus));
        } catch (IndexOutOfBoundsException iobe) {
            // No hacemos nada
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final javax.swing.JLabel LabelEncabezado = new javax.swing.JLabel();
    private static final javax.swing.JTabbedPane TabbedPanel = new javax.swing.JTabbedPane();
    // End of variables declaration//GEN-END:variables
}
