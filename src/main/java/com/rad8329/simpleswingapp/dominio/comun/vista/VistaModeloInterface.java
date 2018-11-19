package com.rad8329.simpleswingapp.dominio.comun.vista;

import com.rad8329.simpleswingapp.dominio.comun.modelo.TablaModeloInterface;

public interface VistaModeloInterface<M extends TablaModeloInterface> extends VistaInterface {

    public M getModelo();
}
