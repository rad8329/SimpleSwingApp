package com.rad8329.simpleswingapp.gui.reporte;

import com.rad8329.simpleswingapp.gui.comun.controlador.ControladorPanelInterface;
import com.rad8329.simpleswingapp.gui.reporte.ReportesPanel;

public class ReporteControlador 
        extends com.rad8329.simpleswingapp.dominio.reporte.ReporteControlador 
        implements ControladorPanelInterface {

    public ReporteControlador(String titulo) {
        super(titulo);
    }

    @Override
    public ReportesPanel renderizar() {
        if (getVista() == null) {
            super.setVista(new ReportesPanel());
        }

        return getVista();
    }

    @Override
    public ReportesPanel getVista() {
        return (ReportesPanel) super.getVista();
    }
}
