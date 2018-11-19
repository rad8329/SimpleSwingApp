package com.rad8329.simpleswingapp.dominio.comun.repositorio;

import java.util.List;

/**
 * @param <T>
 */
public interface RepositorioCrudInterface<T> {

    boolean insertar(T modelo);

    boolean actualizar(T anterior, T nuevo);

    boolean eliminar(T modelo);

    List<T> consultarTodo();
}
