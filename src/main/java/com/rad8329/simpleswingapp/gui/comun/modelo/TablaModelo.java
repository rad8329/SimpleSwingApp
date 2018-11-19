package com.rad8329.simpleswingapp.gui.comun.modelo;

import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorTablaModeloInterface;
import com.rad8329.simpleswingapp.dominio.comun.modelo.TablaModeloInterface;
import javax.swing.table.AbstractTableModel;

abstract public class TablaModelo extends AbstractTableModel implements TablaModeloInterface {

    private static final long serialVersionUID = 5350315410289605598L;
    private final ControladorTablaModeloInterface controlador;

    public TablaModelo(ControladorTablaModeloInterface controlador) {
        this.controlador = controlador;
    }

    abstract public String[] getColumnas();

    abstract public Class[] getTiposColumnas();

    @Override
    public ControladorTablaModeloInterface getControlador() {
        return this.controlador;
    }

    @Override
    public String getColumnName(int columna) {
        return getColumnas()[columna];
    }

    @Override
    public int getRowCount() {
        return controlador.contarRegistros();
    }

    @Override
    public int getColumnCount() {
        return getColumnas().length;
    }

    @Override
    public Class getColumnClass(int columna) {
        return getTiposColumnas()[columna];
    }

    @Override
    public void actualizarCuandoSeElimina(int indice) {
        fireTableRowsDeleted(indice, indice);
    }

    @Override
    public void actualizarCuandoSeInserta(int indice) {
        fireTableRowsInserted(indice, indice);
    }

    @Override
    public void actualizarCuandoSeActualiza(int indice) {
        fireTableRowsUpdated(indice, indice);
    }
}
