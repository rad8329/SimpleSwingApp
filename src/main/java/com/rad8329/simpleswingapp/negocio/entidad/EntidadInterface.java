package com.rad8329.simpleswingapp.negocio.entidad;

import com.rad8329.simpleswingapp.negocio.excepcion.ValidacionExcepcion;

/**
 *
 * @author rad8329
 * @param <T>
 */
public interface EntidadInterface<T> {

    public void validar() throws ValidacionExcepcion;    
}
