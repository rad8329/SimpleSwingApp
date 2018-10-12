package uao.controles;

import uao.excepciones.ValidacionExcepcion;

/**
 *
 * @author rad8329
 * @param <T>
 */
public interface ControladorInterface<T> {
    
    public void remover(int fila);

    public void agregar(T modelo, boolean guardar) throws ValidacionExcepcion;

    public void actualizar(int fila, T modelo) throws ValidacionExcepcion;

    public void cargarTodo();
    
    public T conseguir(int fila);
}
