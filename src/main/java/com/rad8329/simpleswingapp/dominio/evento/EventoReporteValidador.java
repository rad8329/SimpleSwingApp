package com.rad8329.simpleswingapp.dominio.evento;

import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadValidadorInterface;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;

public class EventoReporteValidador implements EntidadValidadorInterface {

    private int consecutivo_evento;

    public EventoReporteValidador(int consecutivo_evento) {
        this.consecutivo_evento = consecutivo_evento;
    }

    public int getConsecutivo_evento() {
        return consecutivo_evento;
    }

    public void setConsecutivo_evento(int consecutivo_evento) {
        this.consecutivo_evento = consecutivo_evento;
    }

    @Override
    public void validar() throws ExcepcionValidacion {
        if (consecutivo_evento == 0) {
            throw new ExcepcionValidacion("El evento es necesario");
        }
    }
}
