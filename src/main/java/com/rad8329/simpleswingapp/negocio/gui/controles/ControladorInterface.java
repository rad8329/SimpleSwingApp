package com.rad8329.simpleswingapp.negocio.gui.controles;

import com.rad8329.simpleswingapp.negocio.excepcion.ValidacionExcepcion;
import javax.swing.JPanel;

/**
 *
 * @author rad8329
 * @param <T>
 */
public interface ControladorInterface<T> {

    public String getTitulo();

    public int contarRegistros();

    public void removerRegistro(int fila);

    public void agregarRegistro(T modelo, boolean guardar) throws ValidacionExcepcion;

    public void actualizarRegistro(int fila, T modelo) throws ValidacionExcepcion;

    public void cargarTodoLosRegistros();

    public T conseguirRegistro(int fila);

    public JPanel construirPanel();
}
