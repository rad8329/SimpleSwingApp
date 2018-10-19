package com.rad8329.simpleswingapp.negocio.gui.modelos;

import com.rad8329.simpleswingapp.negocio.entidad.Zona;
import com.rad8329.simpleswingapp.negocio.gui.controles.ZonaControlador;

import javax.swing.table.AbstractTableModel;

/**
 * @author rad8329
 */
public class ZonaTablaModelo extends AbstractTableModel {

    private static final long serialVersionUID = 218325224171167746L;

    private final ZonaControlador controlador;

    public ZonaTablaModelo(ZonaControlador controlador) {
        this.controlador = controlador;
    }

    private final String[] columnas = {
            "#",
            "Código",
            "Nombre",
            "Descripción",
            "CA",
            "Dirección IP"
    };

    private final Class[] clasesColumnas = {
            Integer.class,
            Integer.class,
            String.class,
            String.class,
            Boolean.class,
            String.class
    };

    @Override
    public String getColumnName(int columna) {
        return columnas[columna];
    }

    @Override
    public int getRowCount() {
        return controlador.contarRegistros();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Class getColumnClass(int columna) {
        return clasesColumnas[columna];
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Zona zona = controlador.conseguirRegistro(fila);

        switch (columna) {
            case 0:
                return fila + 1;
            case 1:
                return zona.getCodigoZona();
            case 2:
                return zona.getNombre();
            case 3:
                return zona.getDescripcion();

            case 4:
                return zona.tieneControlDeAcceso();
            case 5:
                return zona.getDireccionIpCerradura();
            default:
                return null;
        }
    }
}
