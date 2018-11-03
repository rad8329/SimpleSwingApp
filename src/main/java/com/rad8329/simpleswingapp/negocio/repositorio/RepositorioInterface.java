package com.rad8329.simpleswingapp.negocio.repositorio;

import java.util.ArrayList;

/**
 * @param <T> Una implementación de repositorio
 */
public interface RepositorioInterface<T> {

    boolean insertar(T modelo);

    boolean actualizar(T anterior, T nuevo);

    boolean eliminar(T modelo);

    ArrayList<T> consultarTodo();
}
