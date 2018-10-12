package uao.almacenamiento;

import java.util.ArrayList;

/**
 *
 * @author rad8329
 * @param <T>
 */
public interface AlamacenamientoInterface<T> {

    public boolean insertar(T modelo);

    public boolean actualizar(T modelo);

    public boolean eliminar(T modelo);

    public ArrayList<T> consultarMuchos();
}
