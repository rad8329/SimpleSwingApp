package com.rad8329.simpleswingapp.dominio.dependencia;

import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadOpcionableInterface;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadValidadorInterface;

public final class Dependencia extends EntidadOpcionableInterface implements EntidadValidadorInterface {

    private int codigo;
    private String nombre;

    public Dependencia(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoString() {
        return Integer.toString(codigo);
    }

    @Override
    public void validar() throws ExcepcionValidacion {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String opcionValor() {
        return nombre;
    }

    @Override
    public String opcionLlave() {
        return Integer.toString(codigo);
    }

    @Override
    public String toString() {
        return "Dependencia{" + "codigo=" + codigo + ", nombre=" + nombre + '}';
    }
}
