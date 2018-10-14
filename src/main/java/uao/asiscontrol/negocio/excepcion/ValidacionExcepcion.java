package main.java.uao.asiscontrol.negocio.excepcion;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author rad8329
 */
public class ValidacionExcepcion extends Exception {

    public ValidacionExcepcion(String mensaje) {
        super(mensaje);
        //Penultima llamada en la pila
        StackTraceElement lastStackTrace = super.getStackTrace()[0];

        Logger.getLogger(ValidacionExcepcion.class.getName()).log(
                Level.WARNING,
                "{0} {1} {2}",
                new Object[]{
                    mensaje,
                    lastStackTrace.getClassName(),
                    lastStackTrace.getMethodName()
                }
        );
    }
}
