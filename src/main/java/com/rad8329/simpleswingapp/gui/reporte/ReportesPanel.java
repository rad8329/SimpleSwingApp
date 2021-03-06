package com.rad8329.simpleswingapp.gui.reporte;

import com.rad8329.simpleswingapp.gui.comun.vista.Panel;
import com.rad8329.simpleswingapp.dominio.comun.vista.VistaInterface;
import com.rad8329.simpleswingapp.gui.evento.EventoReporteControlador;
import com.rad8329.simpleswingapp.gui.persona.PersonaReporteControlador;
import com.rad8329.simpleswingapp.gui.zona.ZonaReporteControlador;
import javax.swing.JPanel;

public class ReportesPanel extends Panel implements VistaInterface {

    private static final long serialVersionUID = 7325499803210266269L;
    private final ZonaReporteControlador controlZona = new ZonaReporteControlador();
    private final EventoReporteControlador controlEvento = new EventoReporteControlador();
    private final PersonaReporteControlador controlRecorrido = new PersonaReporteControlador();

    private JPanel panelZonas = controlZona.renderizar();
    private JPanel panelEventos = controlEvento.renderizar();
    private JPanel panelRecorridos = controlRecorrido.renderizar();

    public ReportesPanel() {

        super.setName("Reportes");

        initComponents();

        ComboBoxReporte.setModel(new javax.swing.DefaultComboBoxModel(
                new String[]{controlZona.getTitulo(), controlEvento.getTitulo(), controlRecorrido.getTitulo()})
        );

        ComboBoxReporte.setSelectedIndex(-1);

        prepararLienzoYcomponentes();
    }

    private void prepararLienzoYcomponentes() {

        panelZonas.setVisible(false);
        panelEventos.setVisible(false);
        panelRecorridos.setVisible(false);

        PanelInterno.add(panelZonas);
        PanelInterno.add(panelEventos);
        PanelInterno.add(panelRecorridos);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelReporte = new javax.swing.JLabel();
        PanelInterno = new javax.swing.JPanel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelFormulario.setBackground(new java.awt.Color(240, 236, 232));
        PanelFormulario.setMinimumSize(new java.awt.Dimension(900, 565));
        PanelFormulario.setPreferredSize(new java.awt.Dimension(900, 565));
        PanelFormulario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabelID.setFocusable(false);
        PanelFormulario.add(LabelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(824, 229, -1, -1));
        LabelID.setVisible(false);

        ComboBoxReporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ComboBoxReporte.setSelectedIndex(-1);
        ComboBoxReporte.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxReporteItemStateChanged(evt);
            }
        });
        PanelFormulario.add(ComboBoxReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 480, 40));

        LabelReporte.setText("Elija uno de los reportes a vizualizar:");
        PanelFormulario.add(LabelReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 260, 20));

        PanelInterno.setLayout(new javax.swing.BoxLayout(PanelInterno, javax.swing.BoxLayout.LINE_AXIS));
        PanelFormulario.add(PanelInterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 840, 470));

        add(PanelFormulario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxReporteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxReporteItemStateChanged
        
        int reporte = ComboBoxReporte.getSelectedIndex();

        switch (reporte) {
            case 0:

                panelZonas.setVisible(true);
                panelRecorridos.setVisible(false);
                panelEventos.setVisible(false);

                panelZonas.requestFocus();
                break;
            case 1:

                panelZonas.setVisible(false);
                panelRecorridos.setVisible(false);
                panelEventos.setVisible(true);
                panelEventos.requestFocus();
                break;
            case 2:
                panelZonas.setVisible(false);
                panelRecorridos.setVisible(true);
                panelEventos.setVisible(false);
                panelEventos.requestFocus();
                break;
        }

        PanelInterno.requestFocus();
    }//GEN-LAST:event_ComboBoxReporteItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final javax.swing.JComboBox ComboBoxReporte = new javax.swing.JComboBox();
    private final javax.swing.JLabel LabelID = new javax.swing.JLabel();
    private javax.swing.JLabel LabelReporte;
    private final javax.swing.JPanel PanelFormulario = new javax.swing.JPanel();
    private javax.swing.JPanel PanelInterno;
    // End of variables declaration//GEN-END:variables
}
