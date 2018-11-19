package com.rad8329.simpleswingapp.gui.persona;

import com.rad8329.simpleswingapp.gui.comun.modelo.TablaModelo;
import com.rad8329.simpleswingapp.gui.persona.PersonaReporteControlador;
import org.json.JSONObject;

public class PersonaReporteTablaModelo extends TablaModelo {

    private static final long serialVersionUID = 4750951265631006006L;

    public PersonaReporteTablaModelo(PersonaReporteControlador controlador) {
        super(controlador);
    }

    private final String[] columnas = {
        "#",
        "Código estudiante",
        "Zona",
        "Fecha"
    };

    final Class[] clasesColumnas = {
        Integer.class,
        Integer.class,
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
                return objeto.get("codigo_estudiante");
            case 2:
                return objeto.get("zona");
            case 3:
                return objeto.get("fecha");
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
    public PersonaReporteControlador getControlador() {
        return (PersonaReporteControlador) super.getControlador();
    }
}
