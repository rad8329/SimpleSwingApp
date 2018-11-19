package com.rad8329.simpleswingapp.gui.zona;

import com.rad8329.simpleswingapp.gui.comun.controlador.ControladorPanelInterface;
import com.rad8329.simpleswingapp.gui.zona.ZonaPanel;

public class ZonaControlador 
        extends com.rad8329.simpleswingapp.dominio.zona.ZonaControlador 
        implements ControladorPanelInterface {

    public ZonaControlador(String titulo) {
        super(titulo);
    }

    @Override
    public ZonaPanel renderizar() {
        if (getVista() == null) {
            super.setVista(new ZonaPanel(new ZonaTablaModelo(this), this));
        }

        return getVista();
    }

    @Override
    public ZonaPanel getVista() {
        return (ZonaPanel) super.getVista();
    }
}
