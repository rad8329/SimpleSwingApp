package com.rad8329.simpleswingapp.dominio.comun.controlador;

import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;

/**
 * @param <V> 
 */
public interface ControladorReporteInterface<V> {

    void buscar(V criterio) throws ExcepcionValidacion;

    void limpiar();
}
