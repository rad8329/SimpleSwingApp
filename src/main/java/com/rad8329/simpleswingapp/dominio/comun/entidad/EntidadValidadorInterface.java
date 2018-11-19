package com.rad8329.simpleswingapp.dominio.comun.entidad;

import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;

public interface EntidadValidadorInterface {

    void validar() throws ExcepcionValidacion;
}
