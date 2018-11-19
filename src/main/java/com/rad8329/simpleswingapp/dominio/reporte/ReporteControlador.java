package com.rad8329.simpleswingapp.dominio.reporte;

import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorVistaInterface;
import com.rad8329.simpleswingapp.dominio.comun.vista.VistaInterface;

public abstract class ReporteControlador implements ControladorVistaInterface {

    private VistaInterface vista;
    private final String titulo;

    public ReporteControlador(String titulo) {
        this.titulo = titulo;
    }

    @Override
    abstract public VistaInterface renderizar();

    @Override
    public boolean esLanzable() {
        return true;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    public VistaInterface getVista() {
        return vista;
    }

    public void setVista(VistaInterface vista) {
        this.vista = vista;
    }
}
