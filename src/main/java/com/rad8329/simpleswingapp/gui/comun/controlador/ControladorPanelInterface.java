package com.rad8329.simpleswingapp.gui.comun.controlador;

import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorVistaInterface;
import com.rad8329.simpleswingapp.gui.comun.vista.Panel;

public interface ControladorPanelInterface extends ControladorVistaInterface {

    @Override
    public Panel renderizar();
}
