package com.rad8329.simpleswingapp.gui.evento;

import com.rad8329.simpleswingapp.dominio.evento.Programacion;
import com.rad8329.simpleswingapp.gui.comun.modelo.TablaModelo;
import com.rad8329.simpleswingapp.gui.evento.ProgramacionControlador;

public class ProgramacionTablaModelo extends TablaModelo {

    private static final long serialVersionUID = 7468713156869832826L;

    public ProgramacionTablaModelo(ProgramacionControlador controlador) {
        super(controlador);
    }

    private final String[] columnas = {
        "#",
        "Consecutivo",
        "Fecha inicio",
        "fecha fin",
        "Actividades",
        "Zona",};

    final Class[] clasesColumnas = {
        Integer.class,
        Integer.class,
        String.class,
        String.class,
        String.class,
        String.class
    };

    @Override
    public Object getValueAt(int fila, int columna) {
        Programacion programacion = getControlador().conseguirRegistro(fila);
        switch (columna) {
            case 0:
                return fila + 1;
            case 1:
                return programacion.getConsecutivo();
            case 2:
                return programacion.getFecha_inicio();
            case 3:
                return programacion.getFecha_fin();
            case 4:
                return programacion.getActividades();
            case 5:
                return programacion.getZona().getNombre();
            default:
                return null;
        }
    }

    @Override
    public String[] getColumnas() {
        return columnas;
    }

    @Override
    public Class[] getTiposColumnas() {
        return clasesColumnas;
    }

    @Override
    public ProgramacionControlador getControlador() {
        return (ProgramacionControlador) super.getControlador();
    }
}
