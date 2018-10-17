package com.rad8329.simpleswingapp.negocio.gui.controles;

import com.rad8329.simpleswingapp.negocio.excepcion.ValidacionExcepcion;

/**
 * @param <T> Una instancia de controlodor
 * @author rad8329
 */
interface ControladorInterface<T> {

    String getTitulo();

    int contarRegistros();

    boolean removerRegistro(int fila);

    boolean agregarRegistro(T modelo) throws ValidacionExcepcion;

    boolean actualizarRegistro(int fila, T modelo) throws ValidacionExcepcion;
    
    T conseguirRegistro(int fila);
}
