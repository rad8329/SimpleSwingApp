package main.java.uao.asiscontrol.negocio.entidad;

import main.java.uao.asiscontrol.negocio.excepcion.ValidacionExcepcion;

/**
 *
 * @author rad8329
 * @param <T>
 */
public interface EntidadInterface<T> {

    public void validar() throws ValidacionExcepcion;    
}
