package main.java.uao.asiscontrol.negocio.gui.modelos;

import java.util.ArrayList;

/**
 *
 * @author rad8329
 * @param <T>
 */
public interface TablaModeloInterface<T> {

    public void remover(int fila);

    public void agregar(T modelo);

    public void actualizar(int fila);

    public T conseguir(int fila);

    public ArrayList<T> getDatos();
}
