package main.java.uao.asiscontrol.negocio.excepcion;

/**
 *
 * @author rad8329
 */
public class ValidacionExcepcion extends Exception {

    public ValidacionExcepcion(String mensaje) {
        super(mensaje);

        java.util.logging.Logger.getLogger(ValidacionExcepcion.class.getName()).log(java.util.logging.Level.WARNING,
                "{0} {1} {2}",
                new Object[]{mensaje, this.getStackTrace()[0].getClassName(), this.getStackTrace()[0].getMethodName()}
        );
    }
}
