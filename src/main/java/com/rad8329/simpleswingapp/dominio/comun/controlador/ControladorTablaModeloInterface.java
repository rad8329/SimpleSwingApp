package com.rad8329.simpleswingapp.dominio.comun.controlador;

/**
 *
 * @param <T>
 */
public interface ControladorTablaModeloInterface<T> {

    int contarRegistros();

    T conseguirRegistro(int fila);
}
