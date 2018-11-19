package com.rad8329.simpleswingapp.gui.evento;

import com.rad8329.simpleswingapp.gui.comun.controlador.ControladorPanelInterface;
import com.rad8329.simpleswingapp.gui.evento.ProgramacionPanel;

public class ProgramacionControlador 
        extends com.rad8329.simpleswingapp.dominio.evento.ProgramacionControlador 
        implements ControladorPanelInterface {

    public ProgramacionControlador(String titulo) {
        super(titulo);
    }

    @Override
    public ProgramacionPanel renderizar() {
        if (getVista() == null) {
            super.setVista(new ProgramacionPanel(new ProgramacionTablaModelo(this), this));
        }

        return getVista();
    }

    @Override
    public ProgramacionPanel getVista() {
        return (ProgramacionPanel) super.getVista();
    }
}
