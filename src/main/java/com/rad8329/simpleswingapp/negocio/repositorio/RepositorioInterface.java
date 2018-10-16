package com.rad8329.simpleswingapp.negocio.repositorio;

import java.util.ArrayList;

/**
 *
 * @author rad8329
 * @param <T>
 */
public interface RepositorioInterface<T> {
    
    public boolean insertar(T modelo);

    public boolean actualizar(T anterior, T nuevo);

    public boolean eliminar(T modelo);

    public ArrayList<T> consultarTodo();
}
