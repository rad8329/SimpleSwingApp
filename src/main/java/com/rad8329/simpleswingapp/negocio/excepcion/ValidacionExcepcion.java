package com.rad8329.simpleswingapp.negocio.excepcion;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rad8329
 */
public class ValidacionExcepcion extends Exception {

    private static final long serialVersionUID = -7977025569697122976L;

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
