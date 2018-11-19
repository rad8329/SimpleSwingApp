package com.rad8329.simpleswingapp.gui.evento;

import com.rad8329.simpleswingapp.gui.evento.EventoReporteControlador;
import com.rad8329.simpleswingapp.gui.comun.modelo.TablaModelo;
import org.json.JSONObject;

public class EventoReporteTablaModelo extends TablaModelo {
    
    private static final long serialVersionUID = -4452269560229681977L;

    public EventoReporteTablaModelo(EventoReporteControlador controlador) {
        super(controlador);
    }

    private final String[] columnas = {
        "#",
        "Código",
        "Nombre",
        "Apellido",
        "Fecha"
    };

    final Class[] clasesColumnas = {
        Integer.class,
        Integer.class,
        String.class,
        String.class,
        String.class
    };

    @Override
    public Object getValueAt(int fila, int columna) {
        JSONObject objeto = getControlador().conseguirRegistro(fila);

        switch (columna) {
            case 0:
                return fila + 1;
            case 1:
                return objeto.getInt("codigo");
            case 2:
                return objeto.getString("nombres");
            case 3:
                return objeto.getString("nombres");
            case 4:
                return objeto.getString("fecha");
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
    public EventoReporteControlador getControlador() {
        return (EventoReporteControlador) super.getControlador();
    }
}
