package com.rad8329.simpleswingapp.gui.zona;

import com.rad8329.simpleswingapp.dominio.zona.Zona;
import com.rad8329.simpleswingapp.gui.comun.modelo.TablaModelo;
import com.rad8329.simpleswingapp.gui.zona.ZonaControlador;

public class ZonaTablaModelo extends TablaModelo {

    private static final long serialVersionUID = -455889075701814985L;

    public ZonaTablaModelo(ZonaControlador controlador) {
        super(controlador);
    }

    private final String[] columnas = {
        "#",
        "Código",
        "Nombre",
        "Descripción",
        "Control acceso",
        "IP Cerradura"
    };

    final Class[] clasesColumnas = {
        Integer.class,
        Integer.class,
        String.class,
        String.class,
        Boolean.class,
        String.class
    };

    @Override
    public Object getValueAt(int fila, int columna) {
        Zona zona = getControlador().conseguirRegistro(fila);

        switch (columna) {
            case 0:
                return fila + 1;
            case 1:
                return zona.getCodigo_zona();
            case 2:
                return zona.getNombre();
            case 3:
                return zona.getDescripcion();

            case 4:
                return zona.tieneControl_de_acceso();
            case 5:
                return zona.getDireccion_ip_cerradura();
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
    public ZonaControlador getControlador() {
        return (ZonaControlador) super.getControlador();
    }
}
