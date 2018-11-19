package com.rad8329.simpleswingapp.dominio.comun.entidad;

import com.rad8329.simpleswingapp.gui.comun.vista.OpcionCombo;

public abstract class EntidadOpcionableInterface {

    public OpcionCombo opcionCombo() {
        if (opcionValor() != null) {
            return EntidadOpcionableInterface.crear(opcionLlave(), opcionValor());
        }

        return null;
    }

    public abstract String opcionValor();

    public abstract String opcionLlave();

    public static OpcionCombo crear(String llave, String valor) {
        return new OpcionCombo(llave, valor);
    }

    public static OpcionCombo crear(EntidadOpcionableInterface objeto) {
        if (objeto != null) {
            return crear(objeto.opcionLlave(), objeto.opcionValor());
        }

        return null;
    }
}
