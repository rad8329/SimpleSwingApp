package com.rad8329.simpleswingapp.gui.evento;

import com.rad8329.simpleswingapp.gui.comun.controlador.ControladorPanelInterface;

public class EventoControlador
        extends com.rad8329.simpleswingapp.dominio.evento.EventoControlador
        implements ControladorPanelInterface {

    public EventoControlador(String titulo) {
        super(titulo);
    }

    @Override
    public EventoPanel renderizar() {
        if (getVista() == null) {
            super.setVista(new EventoPanel(new EventoTablaModelo(this), this));
        }

        return getVista();
    }

    @Override
    public EventoPanel getVista() {
        return (EventoPanel) super.getVista();
    }
}
