package com.rad8329.simpleswingapp.dominio.comun.excepcion;

import java.util.logging.Logger;
import java.util.logging.Level;

public class ExcepcionValidacion extends Exception {

    private static final long serialVersionUID = 8791256429819533391L;

    public ExcepcionValidacion(String mensaje) {
        super(mensaje);
        //Penultima llamada en la pila
        StackTraceElement lastStackTrace = super.getStackTrace()[0];

        Logger.getLogger(ExcepcionValidacion.class.getName()).log(
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
