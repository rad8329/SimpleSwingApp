package uao.controles;

import uao.excepciones.ValidacionException;

/**
 *
 * @author rad8329
 * @param <T>
 */
public interface ControladorInterface<T> {
    
    public void remover(int fila);

    public void agregar(T modelo, boolean guardar) throws ValidacionException;

    public void actualizar(int fila, T modelo) throws ValidacionException;

    public void cargarTodo();
    
    public T conseguir(int fila);
}
