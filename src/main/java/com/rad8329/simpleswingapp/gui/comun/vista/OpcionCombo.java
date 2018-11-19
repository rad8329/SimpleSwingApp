package com.rad8329.simpleswingapp.gui.comun.vista;

import java.util.Objects;

public final class OpcionCombo {

    private final String llave;
    private final String valor;

    public OpcionCombo(String llave, String valor) {
        this.llave = llave;
        this.valor = valor;
    }

    public String getLlave() {
        return llave;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }

    @Override
    public boolean equals(Object objetoAbuscar) {

        if (objetoAbuscar == null) {
            return false;
        }
        if (objetoAbuscar == this) {
            return true;
        }
        if (!(objetoAbuscar instanceof OpcionCombo)) {
            return false;
        }
        
        return ((OpcionCombo) objetoAbuscar).getLlave().equals(llave);
    }

    @Override
    public int hashCode() {
        //Solo tendrémos en cuenta la llave
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.llave);
        return hash;
    }
}
