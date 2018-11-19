package com.rad8329.simpleswingapp.gui.evento;

import com.rad8329.simpleswingapp.Aplicacion;
import com.rad8329.simpleswingapp.gui.comun.vista.OpcionCombo;
import com.rad8329.simpleswingapp.gui.comun.vista.Utilidades;
import com.rad8329.simpleswingapp.dominio.dependencia.Dependencia;
import com.rad8329.simpleswingapp.dominio.evento.Evento;
import com.rad8329.simpleswingapp.dominio.programa.Programa;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import com.rad8329.simpleswingapp.gui.evento.EventoControlador;
import com.rad8329.simpleswingapp.gui.evento.EventoTablaModelo;
import java.util.ArrayList;
import java.util.List;
import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadOpcionableInterface;
import com.rad8329.simpleswingapp.dominio.comun.vista.VistaModeloInterface;
import com.rad8329.simpleswingapp.gui.MainFrame;
import com.rad8329.simpleswingapp.gui.comun.vista.Panel;

public class EventoPanel extends Panel implements VistaModeloInterface<EventoTablaModelo>{

    private static final long serialVersionUID = 6894782946614790334L;
    private final EventoTablaModelo modelo;
    private final List<OpcionCombo> programasAcademicos = new ArrayList<>();
    private final List<OpcionCombo> dependencias = new ArrayList<>();
    private Evento eventoSeleccionado;
    protected final EventoControlador control;

    /**
     * @param modelo
     * @param control
     */
    public EventoPanel(EventoTablaModelo modelo, EventoControlador control) {
        this.modelo = modelo;
        this.control = control;
        super.setName(control.getTitulo());

        initComponents();
        cargarComboProgramas();
        cargarComboDependencias();
    }

    private void cargarComboProgramas() {
        OpcionCombo opcion1 = EntidadOpcionableInterface.crear("-1", "");
        programasAcademicos.add(opcion1);
        ComboBoxPrograma.addItem(opcion1);

        control.listaDeProgramas().forEach(
                programa -> {
                    OpcionCombo opcion = programa.opcionCombo();
                    programasAcademicos.add(opcion);
                    ComboBoxPrograma.addItem(opcion);
                }
        );

        ComboBoxPrograma.setSelectedIndex(-1);
    }

    private void cargarComboDependencias() {
        OpcionCombo opcion1 = EntidadOpcionableInterface.crear("-1", "");
        dependencias.add(opcion1);
        ComboBoxDependencia.addItem(opcion1);

        control.listaDeDependencias().forEach(
                dependencia -> {
                    OpcionCombo opcion = dependencia.opcionCombo();
                    dependencias.add(opcion);
                    ComboBoxDependencia.addItem(opcion);
                }
        );

        ComboBoxDependencia.setSelectedIndex(-1);
    }

    @Override
    public EventoTablaModelo getModelo() {
        return modelo;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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

        BotonBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/drop-icon.png"))); // NOI18N
        BotonBorrar.setText("Eliminar");
        BotonBorrar.setFocusable(false);
        BotonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBorrarActionPerformed(evt);
            }
        });
        PanelFormulario.add(BotonBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 510, 155, 36));

        LabelNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LabelNombre.setText("Nombre:");
        PanelFormulario.add(LabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 44, -1, -1));

        TextFieldNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TextFieldNombre.setMaximumSize(new java.awt.Dimension(2147483647, 23));
        TextFieldNombre.setPreferredSize(new java.awt.Dimension(6, 20));
        PanelFormulario.add(TextFieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 33, 557, 36));

        LabelPrograma.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LabelPrograma.setText("Programa:");
        PanelFormulario.add(LabelPrograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 99, -1, -1));

        ComboBoxPrograma.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ComboBoxPrograma.setSelectedIndex(-1);
        PanelFormulario.add(ComboBoxPrograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 88, 238, 34));

        LabelDependencia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LabelDependencia.setText("Dependencia:");
        PanelFormulario.add(LabelDependencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 99, -1, -1));

        ComboBoxDependencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PanelFormulario.add(ComboBoxDependencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 88, 222, 34));

        LabelDescripcion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LabelDescripcion.setText("Descripción:");
        PanelFormulario.add(LabelDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 141, -1, -1));

        TextAreaDescripcion.setColumns(20);
        TextAreaDescripcion.setRows(5);
        ScrollPanelDescripcion.setViewportView(TextAreaDescripcion);

        PanelFormulario.add(ScrollPanelDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 141, 563, 65));

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

        PanelFormulario.add(ScrollPanelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 231, 850, 260));

        LabelID.setFocusable(false);
        PanelFormulario.add(LabelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(806, 239, -1, -1));
        LabelID.setVisible(false);

        BotonDefinirCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/calendar-icon.png"))); // NOI18N
        BotonDefinirCalendario.setText("Programación");
        BotonDefinirCalendario.setDisabledIcon(null);
        BotonDefinirCalendario.setEnabled(false);
        BotonDefinirCalendario.setFocusable(false);
        BotonDefinirCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonDefinirCalendarioActionPerformed(evt);
            }
        });
        PanelFormulario.add(BotonDefinirCalendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, 155, 36));

        add(PanelFormulario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void limpiar() {
        TextFieldNombre.setText("");
        TextAreaDescripcion.setText("");

        ComboBoxPrograma.setSelectedIndex(-1);
        ComboBoxDependencia.setSelectedIndex(-1);
        LabelID.setText("");
        BotonDefinirCalendario.setEnabled(false);
        
        if (eventoSeleccionado != null) {
            MainFrame.removerPanel(3, 1);
            eventoSeleccionado = null;
        }
    }

    private void BotonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGuardarActionPerformed

        String mensaje;

        try {
            Programa programa = null;
            Dependencia dependencia = null;

            OpcionCombo programaSeleccionado = (OpcionCombo) Utilidades.elegirObjeto(ComboBoxPrograma, "Programa");
            OpcionCombo dependenciaSeleccionada = (OpcionCombo) Utilidades.elegirObjeto(ComboBoxDependencia, "Dependencia");

            if (programaSeleccionado != null && !programaSeleccionado.getLlave().equals("-1")) {
                programa = new Programa(
                        Integer.parseInt(programaSeleccionado.getLlave()),
                        programaSeleccionado.getValor()
                );
            }

            if (dependenciaSeleccionada != null && !dependenciaSeleccionada.getLlave().equals("-1")) {
                dependencia = new Dependencia(
                        Integer.parseInt(dependenciaSeleccionada.getLlave()),
                        dependenciaSeleccionada.getValor()
                );
            }

            Evento evento = new Evento(TextFieldNombre.getText(), TextAreaDescripcion.getText(), programa, dependencia);

            if (!LabelID.getText().isEmpty()) {
                if (control.actualizarRegistro(Integer.parseInt(LabelID.getText()), evento)) {
                    mensaje = "El evento fue actualizado con éxito";
                    limpiar();
                } else {
                    mensaje = "Ocurrió un problema al intentar actualizar el evento";

                }
            } else {
                if (control.agregarRegistro(evento)) {
                    mensaje = "El evento fue agregada con éxito";
                    limpiar();
                } else {
                    mensaje = "Ocurrió un problema al intentar agregar el evento";
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

        eventoSeleccionado = control.conseguirRegistro(fila);

        TextFieldNombre.setText(eventoSeleccionado.getNombre());
        TextAreaDescripcion.setText(eventoSeleccionado.getDescripcion());

        ComboBoxPrograma.setSelectedIndex(programasAcademicos.indexOf(EntidadOpcionableInterface.crear(eventoSeleccionado.getPrograma())));
        ComboBoxDependencia.setSelectedIndex(dependencias.indexOf(EntidadOpcionableInterface.crear(eventoSeleccionado.getDependencia())));

        LabelID.setText(Integer.toString(fila));// Para saber si debemos actualizar el registro

        BotonDefinirCalendario.setEnabled(true);
    }//GEN-LAST:event_TablaDatosMouseClicked

    private void BotonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBorrarActionPerformed

        if (!LabelID.getText().isEmpty()) {
            int fila = Integer.parseInt(LabelID.getText());

            String mensaje;

            if (control.removerRegistro(fila)) {
                limpiar();

                mensaje = "El evento fue eliminado con éxito";
            } else {
                mensaje = "Ocurrió un problema al intentar eliminar el evento";
            }

            JOptionPane.showMessageDialog(this, mensaje);
        }
    }//GEN-LAST:event_BotonBorrarActionPerformed

    private void BotonDefinirCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonDefinirCalendarioActionPerformed
        
        if (eventoSeleccionado != null) {
            ProgramacionControlador controladorProgramacion = (ProgramacionControlador) Aplicacion.controlador("programaciones");
            controladorProgramacion.setEvento(eventoSeleccionado);
            MainFrame.agregarPanel(controladorProgramacion.renderizar());
        }
    }//GEN-LAST:event_BotonDefinirCalendarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final javax.swing.JButton BotonBorrar = new javax.swing.JButton();
    private final javax.swing.JButton BotonDefinirCalendario = new javax.swing.JButton();
    private final javax.swing.JButton BotonGuardar = new javax.swing.JButton();
    private final javax.swing.JButton BotonLimpiar = new javax.swing.JButton();
    private final javax.swing.JComboBox ComboBoxDependencia = new javax.swing.JComboBox();
    private final javax.swing.JComboBox ComboBoxPrograma = new javax.swing.JComboBox();
    private final javax.swing.JLabel LabelDependencia = new javax.swing.JLabel();
    private final javax.swing.JLabel LabelDescripcion = new javax.swing.JLabel();
    private final javax.swing.JLabel LabelID = new javax.swing.JLabel();
    private final javax.swing.JLabel LabelNombre = new javax.swing.JLabel();
    private final javax.swing.JLabel LabelPrograma = new javax.swing.JLabel();
    private final javax.swing.JPanel PanelFormulario = new javax.swing.JPanel();
    private final javax.swing.JScrollPane ScrollPanelDescripcion = new javax.swing.JScrollPane();
    private final javax.swing.JScrollPane ScrollPanelTabla = new javax.swing.JScrollPane();
    private final javax.swing.JTable TablaDatos = new javax.swing.JTable();
    private final javax.swing.JTextArea TextAreaDescripcion = new javax.swing.JTextArea();
    private final javax.swing.JTextField TextFieldNombre = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
}
