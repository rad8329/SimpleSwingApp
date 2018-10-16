package com.rad8329.simpleswingapp.negocio.entidad;

import com.rad8329.simpleswingapp.negocio.excepcion.ValidacionExcepcion;

/**
 * @author rad8329
 */
interface EntidadInterface {

    void validar() throws ValidacionExcepcion;
}
