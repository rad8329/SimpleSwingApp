package main.java.uao.asiscontrol.negocio.gui.controles;

import main.java.uao.asiscontrol.negocio.excepcion.ValidacionExcepcion;
import javax.swing.JPanel;

/**
 *
 * @author rad8329
 * @param <T>
 */
public interface ControladorInterface<T, V> {

    public String getTitulo();

    public int contarRegistros();

    public void removerRegistro(int fila);

    public void agregarRegistro(T modelo, boolean guardar) throws ValidacionExcepcion;

    public void actualizarRegistro(int fila, T modelo) throws ValidacionExcepcion;

    public void cargarTodoLosRegistros();

    public T conseguirRegistro(int fila);

    public JPanel construirPanel();
}
