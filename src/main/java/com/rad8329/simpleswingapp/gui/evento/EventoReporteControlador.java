package com.rad8329.simpleswingapp.gui.evento;

import com.rad8329.simpleswingapp.gui.comun.controlador.ControladorPanelInterface;

public class EventoReporteControlador
        extends com.rad8329.simpleswingapp.dominio.evento.EventoReporteControlador
        implements ControladorPanelInterface {

    @Override
    public EventoReportePanel renderizar() {
        if (getVista() == null) {
            super.setVista(new EventoReportePanel(new EventoReporteTablaModelo(this), this));
        }

        return getVista();
    }

    @Override
    public EventoReportePanel getVista() {
        return (EventoReportePanel) super.getVista();
    }
}
