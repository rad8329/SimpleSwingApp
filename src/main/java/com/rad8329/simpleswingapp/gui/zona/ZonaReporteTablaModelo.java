package com.rad8329.simpleswingapp.gui.zona;

import com.rad8329.simpleswingapp.gui.comun.modelo.TablaModelo;
import com.rad8329.simpleswingapp.gui.zona.ZonaReporteControlador;
import org.json.JSONObject;

public class ZonaReporteTablaModelo extends TablaModelo {

    private static final long serialVersionUID = -1746632571296623027L;

    public ZonaReporteTablaModelo(ZonaReporteControlador controlador) {
        super(controlador);
    }

    private final String[] columnas = {
        "#",
        "Nùmero de estudiantes",
        "Fecha",
        "Programa"
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
                return objeto.get("numero_estudiantes");
            case 2:
                return "";
            case 3:
                return objeto.get("programa");
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
    public ZonaReporteControlador getControlador() {
        return (ZonaReporteControlador) super.getControlador();
    }
}
