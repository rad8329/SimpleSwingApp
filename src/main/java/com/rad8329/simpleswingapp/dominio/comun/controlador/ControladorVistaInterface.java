package com.rad8329.simpleswingapp.dominio.comun.controlador;

import com.rad8329.simpleswingapp.dominio.comun.vista.VistaInterface;

public interface ControladorVistaInterface {

    String getTitulo();

    abstract VistaInterface renderizar();

    boolean esLanzable();
}
