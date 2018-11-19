package com.rad8329.simpleswingapp.gui.evento;

import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadOpcionableInterface;
import com.rad8329.simpleswingapp.dominio.evento.Programacion;
import com.rad8329.simpleswingapp.dominio.zona.Zona;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import com.rad8329.simpleswingapp.gui.comun.vista.OpcionCombo;
import com.rad8329.simpleswingapp.gui.comun.vista.Utilidades;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import com.rad8329.simpleswingapp.dominio.comun.vista.VistaModeloInterface;
import com.rad8329.simpleswingapp.gui.MainFrame;
import com.rad8329.simpleswingapp.gui.comun.vista.Panel;

public class ProgramacionPanel extends Panel implements VistaModeloInterface<ProgramacionTablaModelo>{

    private static final long serialVersionUID = -4790115605662097940L;
    private final ProgramacionTablaModelo modelo;
    protected final ProgramacionControlador control;
    private final List<OpcionCombo> zonas = new ArrayList<>();

    /**
     *
     * @param modelo
     * @param control
     */
    public ProgramacionPanel(ProgramacionTablaModelo modelo, ProgramacionControlador control) {
        this.modelo = modelo;
        this.control = control;
        super.setName(control.getTitulo());

        initComponents();
        AutoCompleteDecorator.decorate(ComboBoxZona);
        cargarComboZonas();
        
        TextFieldEvento.setText(this.control.getEvento().getNombre());
    }
    
    @Override
    public ProgramacionTablaModelo getModelo() {
        return modelo;
    }

    private void cargarComboZonas() {
        OpcionCombo opcion1 = EntidadOpcionableInterface.crear("-1", "");
        zonas.add(opcion1);
        ComboBoxZona.addItem(opcion1);

        control.listaDeZonas().forEach(
                zona -> {
                    OpcionCombo opcion = zona.opcionCombo();
                    zonas.add(opcion);
                    ComboBoxZona.addItem(opcion);
                }
        );

        ComboBoxZona.setSelectedIndex(-1);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelFormulario.setBackground(new java.awt.Color(240, 236, 232));
        PanelFormulario.setMinimumSize(new java.awt.Dimension(900, 565));
        PanelFormulario.setPreferredSize(new java.awt.Dimension(900, 565));
        PanelFormulario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/save-icon.png"))); // NOI18N
        BotonGuardar.setText("Guardar");
        BotonGuardar.setFocusable(false);
        BotonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGuardarActionPerformed(evt);
            }
        });
        PanelFormulario.add(BotonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 155, 36));

        BotonLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/clear-icon.png"))); // NOI18N
        BotonLimpiar.setText("Limpiar");
        BotonLimpiar.setFocusable(false);
        BotonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonLimpiarActionPerformed(evt);
            }
        });
        PanelFormulario.add(BotonLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, 155, 36));

        BotonCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/close-icon.png"))); // NOI18N
        BotonCerrar.setText("Cerrar");
        BotonCerrar.setFocusable(false);
        BotonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCerrarActionPerformed(evt);
            }
        });
        PanelFormulario.add(BotonCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, 155, 36));

        BotonBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/drop-icon.png"))); // NOI18N
        BotonBorrar.setText("Eliminar");
        BotonBorrar.setFocusable(false);
        BotonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBorrarActionPerformed(evt);
            }
        });
        PanelFormulario.add(BotonBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 510, 155, 36));

        LabelEvento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LabelEvento.setText("Evento:");
        PanelFormulario.add(LabelEvento, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        TextFieldEvento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TextFieldEvento.setEnabled(false);
        TextFieldEvento.setMaximumSize(new java.awt.Dimension(2147483647, 23));
        TextFieldEvento.setPreferredSize(new java.awt.Dimension(6, 20));
        PanelFormulario.add(TextFieldEvento, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 170, 36));

        LabelZona.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LabelZona.setText("Zona:");
        PanelFormulario.add(LabelZona, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, -1));

        ComboBoxZona.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PanelFormulario.add(ComboBoxZona, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 290, 34));

        LabelFechaInicio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LabelFechaInicio.setText("Fecha inicio:");
        PanelFormulario.add(LabelFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        TextFieldFechaInicio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TextFieldFechaInicio.setMaximumSize(new java.awt.Dimension(2147483647, 23));
        TextFieldFechaInicio.setPreferredSize(new java.awt.Dimension(6, 20));
        PanelFormulario.add(TextFieldFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 170, 36));

        LabelFechaFin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LabelFechaFin.setText("Fin:");
        PanelFormulario.add(LabelFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, -1));

        TextFieldFechaFin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TextFieldFechaFin.setMaximumSize(new java.awt.Dimension(2147483647, 23));
        TextFieldFechaFin.setPreferredSize(new java.awt.Dimension(6, 20));
        PanelFormulario.add(TextFieldFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 290, 36));

        LabelActividades.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LabelActividades.setText("Actividades:");
        PanelFormulario.add(LabelActividades, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        TextAreaActividades.setColumns(20);
        TextAreaActividades.setRows(5);
        ScrollPanelActividades.setViewportView(TextAreaActividades);

        PanelFormulario.add(ScrollPanelActividades, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 540, 65));

        ScrollPanelTabla.setBackground(new java.awt.Color(255, 255, 255));
        ScrollPanelTabla.setBorder(null);
        ScrollPanelTabla.setFocusable(false);

        TablaDatos.setModel(this.modelo);
        TablaDatos.setFocusable(false);
        TablaDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaDatosMouseClicked(evt);
            }
        });
        TablaDatos.getColumnModel().getColumn(0).setMaxWidth(50);
        ScrollPanelTabla.setViewportView(TablaDatos);

        PanelFormulario.add(ScrollPanelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 232, 850, 260));

        LabelID.setFocusable(false);
        PanelFormulario.add(LabelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 184, -1, -1));
        LabelID.setVisible(false);

        add(PanelFormulario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void limpiar() {        
        TextAreaActividades.setText("");        
        TextFieldFechaInicio.setText("");
        TextFieldFechaFin.setText("");
        ComboBoxZona.setSelectedIndex(-1);
        LabelID.setText("");
    }

    private void BotonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGuardarActionPerformed

        String mensaje;

        try {
            Zona zona = null;
            OpcionCombo zonaSeleccionada = (OpcionCombo) Utilidades.elegirObjeto(ComboBoxZona, "Zona");

            if (zonaSeleccionada != null && !zonaSeleccionada.getLlave().equals("-1")) {
                zona = new Zona(
                        Integer.parseInt(zonaSeleccionada.getLlave()),
                        zonaSeleccionada.getValor()
                );
            }
            
            Programacion programacion = new Programacion(
                    TextFieldFechaInicio.getText(),
                    TextFieldFechaFin.getText(),
                    TextAreaActividades.getText(),
                    control.getEvento(),
                    zona
            );
            
            if (!LabelID.getText().isEmpty()) {
                if (control.actualizarRegistro(Integer.parseInt(LabelID.getText()), programacion)) {
                    mensaje = "La programación fue actualizado con éxito";
                    limpiar();
                } else {
                    mensaje = "Ocurrió un problema al intentar actualizar la programación";

                }
            } else {
                if (control.agregarRegistro(programacion)) {
                    mensaje = "La programación fue agregada con éxito";
                    limpiar();
                } else {
                    mensaje = "Ocurrió un problema al intentar agregar la programación";
                }
            }

            JOptionPane.showMessageDialog(this, mensaje);

        } catch (ExcepcionValidacion ve) {
            JOptionPane.showMessageDialog(this, ve.getMessage());
        }
    }//GEN-LAST:event_BotonGuardarActionPerformed

    private void BotonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_BotonLimpiarActionPerformed

    private void TablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaDatosMouseClicked
        JTable source = (JTable) evt.getSource();
        int fila = source.rowAtPoint(evt.getPoint());

        Programacion programacion = control.conseguirRegistro(fila);

        TextFieldFechaInicio.setText(programacion.getFecha_inicio());
        TextFieldFechaFin.setText(programacion.getFecha_fin());
        TextAreaActividades.setText(programacion.getActividades());
        
        ComboBoxZona.setSelectedIndex(zonas.indexOf(EntidadOpcionableInterface.crear(programacion.getZona())));

        LabelID.setText(Integer.toString(fila));// Para saber si debemos actualizar el registro
    }//GEN-LAST:event_TablaDatosMouseClicked

    private void BotonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBorrarActionPerformed

        if (!LabelID.getText().isEmpty()) {
            int fila = Integer.parseInt(LabelID.getText());

            String mensaje;

            if (control.removerRegistro(fila)) {
                limpiar();

                mensaje = "La programación fue eliminada con éxito";
            } else {
                mensaje = "Ocurrió un problema al intentar eliminar la programación";
            }

            JOptionPane.showMessageDialog(this, mensaje);
        }
    }//GEN-LAST:event_BotonBorrarActionPerformed

    private void BotonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCerrarActionPerformed
        
        MainFrame.removerPanel(3, 1);
        //MainFrame.
    }//GEN-LAST:event_BotonCerrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final javax.swing.JButton BotonBorrar = new javax.swing.JButton();
    private final javax.swing.JButton BotonCerrar = new javax.swing.JButton();
    private final javax.swing.JButton BotonGuardar = new javax.swing.JButton();
    private final javax.swing.JButton BotonLimpiar = new javax.swing.JButton();
    private final javax.swing.JComboBox ComboBoxZona = new javax.swing.JComboBox();
    private final javax.swing.JLabel LabelActividades = new javax.swing.JLabel();
    private final javax.swing.JLabel LabelEvento = new javax.swing.JLabel();
    private final javax.swing.JLabel LabelFechaFin = new javax.swing.JLabel();
    private final javax.swing.JLabel LabelFechaInicio = new javax.swing.JLabel();
    private final javax.swing.JLabel LabelID = new javax.swing.JLabel();
    private final javax.swing.JLabel LabelZona = new javax.swing.JLabel();
    private final javax.swing.JPanel PanelFormulario = new javax.swing.JPanel();
    private final javax.swing.JScrollPane ScrollPanelActividades = new javax.swing.JScrollPane();
    private final javax.swing.JScrollPane ScrollPanelTabla = new javax.swing.JScrollPane();
    private final javax.swing.JTable TablaDatos = new javax.swing.JTable();
    private final javax.swing.JTextArea TextAreaActividades = new javax.swing.JTextArea();
    private final javax.swing.JTextField TextFieldEvento = new javax.swing.JTextField();
    private final javax.swing.JTextField TextFieldFechaFin = new javax.swing.JTextField();
    private final javax.swing.JTextField TextFieldFechaInicio = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
}