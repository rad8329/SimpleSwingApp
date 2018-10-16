package com.rad8329.simpleswingapp.negocio.repositorio;

import java.util.ArrayList;

/**
 * @param <T>
 * @author rad8329
 */
interface RepositorioInterface<T> {

    boolean insertar(T modelo);

    boolean actualizar(T anterior, T nuevo);

    boolean eliminar(T modelo);

    ArrayList<T> consultarTodo();
}
