package com.rad8329.simpleswingapp.dominio.comun.controlador;

import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;

/**
 *
 * @param <T>
 */
public interface ControladorCrudInterface<T> {
    
    boolean removerRegistro(int indice);

    boolean agregarRegistro(T entidad) throws ExcepcionValidacion;

    boolean actualizarRegistro(int indice, T entidad) throws ExcepcionValidacion;

    T conseguirRegistro(int indice);
}
