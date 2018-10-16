package com.rad8329.simpleswingapp.negocio.gui.controles;

import com.rad8329.simpleswingapp.negocio.excepcion.ValidacionExcepcion;

import javax.swing.*;

/**
 * @param <T> Una instancia de controlodor
 * @author rad8329
 */
public interface ControladorInterface<T> {

    String getTitulo();

    int contarRegistros();

    void removerRegistro(int fila);

    void agregarRegistro(T modelo, boolean guardar) throws ValidacionExcepcion;

    void actualizarRegistro(int fila, T modelo) throws ValidacionExcepcion;

    void cargarTodoLosRegistros();

    T conseguirRegistro(int fila);

    JPanel construirPanel();
}
