package com.rad8329.simpleswingapp.gui.persona;

import com.rad8329.simpleswingapp.gui.comun.controlador.ControladorPanelInterface;
import com.rad8329.simpleswingapp.gui.persona.PersonaReportePanel;

public class PersonaReporteControlador 
        extends com.rad8329.simpleswingapp.dominio.persona.PersonaReporteControlador 
        implements ControladorPanelInterface {

    @Override
    public PersonaReportePanel renderizar() {
        if (getVista() == null) {
            super.setVista(new PersonaReportePanel(new PersonaReporteTablaModelo(this), this));
        }

        return getVista();
    }

    @Override
    public PersonaReportePanel getVista() {
        return (PersonaReportePanel) super.getVista();
    }
}
