package com.rad8329.simpleswingapp.negocio.gui.vistas;

import com.rad8329.simpleswingapp.negocio.excepcion.ValidacionExcepcion;
import java.util.Optional;
import javax.swing.JTextField;

public class Utilidades {

    public static java.util.Optional<Integer> capturarEntero(JTextField campo, String label) throws ValidacionExcepcion {
        java.util.Optional<Integer> numero = Optional.of(0);

        if (!campo.getText().equals("")) {
            try {
                numero = Optional.of(Integer.parseInt(campo.getText()));
            } catch (NumberFormatException ex) {

                throw new ValidacionExcepcion(String.format("%s debe ser númerico", label));
            }
        }

        return numero;
    }
}
