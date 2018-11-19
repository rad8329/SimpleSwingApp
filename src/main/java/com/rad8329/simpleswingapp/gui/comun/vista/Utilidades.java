package com.rad8329.simpleswingapp.gui.comun.vista;

import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import java.util.Optional;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Utilidades {

    public static Optional<Integer> capturarEntero(JTextField campo, String label) throws ExcepcionValidacion {
        java.util.Optional<Integer> numero = Optional.of(0);

        if (!campo.getText().equals("")) {
            try {
                numero = Optional.of(Integer.parseInt(campo.getText()));
            } catch (NumberFormatException ne) {

                throw new ExcepcionValidacion(String.format("%s debe ser númerico", label));
            }
        }

        return numero;
    }

    public static <Objeto> Objeto elegirObjeto(JComboBox<Objeto> combo, String label) throws ExcepcionValidacion {

        try {
            Objeto objeto = (Objeto) combo.getSelectedItem();

            return objeto;
        } catch (NullPointerException | java.util.NoSuchElementException ne) {

            throw new ExcepcionValidacion(String.format("%s debe tener un valor", label));
        }
    }
}
