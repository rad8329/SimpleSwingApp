package com.rad8329.simpleswingapp.gui.zona;

import com.rad8329.simpleswingapp.gui.comun.controlador.ControladorPanelInterface;

public class ZonaReporteControlador
        extends com.rad8329.simpleswingapp.dominio.zona.ZonaReporteControlador
        implements ControladorPanelInterface {

    @Override
    public ZonaReportePanel renderizar() {
        if (getVista() == null) {
            super.setVista(new ZonaReportePanel(new ZonaReporteTablaModelo(this), this));
        }

        return getVista();
    }

    @Override
    public ZonaReportePanel getVista() {
        return (ZonaReportePanel) super.getVista();
    }
}
