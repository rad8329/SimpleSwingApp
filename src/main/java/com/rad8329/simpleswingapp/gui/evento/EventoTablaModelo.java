package com.rad8329.simpleswingapp.gui.evento;

import com.rad8329.simpleswingapp.dominio.evento.Evento;
import com.rad8329.simpleswingapp.gui.evento.EventoControlador;
import com.rad8329.simpleswingapp.gui.comun.modelo.TablaModelo;

public class EventoTablaModelo extends TablaModelo {

    private static final long serialVersionUID = -6914721283377890579L;

    public EventoTablaModelo(EventoControlador controlador) {
        super(controlador);
    }

    private final String[] columnas = {
        "#",
        "Consecutivo",
        "Nombre",
        "Descripción",
        "Programa",
        "Dependencia"
    };

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
        Evento evento = getControlador().conseguirRegistro(fila);

        switch (columna) {
            case 0:
                return fila + 1;
            case 1:
                return evento.getConsecutivo();
            case 2:
                return evento.getNombre();
            case 3:
                return evento.getDescripcion();
            case 4:
                if (evento.getPrograma() != null) {
                    return evento.getPrograma().getNombre();
                }

                return null;
            case 5:
                if (evento.getDependencia() != null) {
                    return "";
                }

                return null;
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
    public EventoControlador getControlador() {
        return (EventoControlador) super.getControlador();
    }
}
