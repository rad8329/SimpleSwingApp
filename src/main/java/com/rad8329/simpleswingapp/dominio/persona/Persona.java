package com.rad8329.simpleswingapp.dominio.persona;

import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadOpcionableInterface;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadValidadorInterface;

public final class Persona extends EntidadOpcionableInterface implements EntidadValidadorInterface {

    private int codigo;
    private String nombres;
    private String apellidos;
    private int codigo_rol;

    public Persona(int codigo, String nombres, String apellidos, int codigo_rol) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.codigo_rol = codigo_rol;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCodigo_rol() {
        return codigo_rol;
    }

    public void setCodigo_rol(int codigo_rol) {
        this.codigo_rol = codigo_rol;
    }
    
    public String getNombreCompleto(){
        return nombres + " "+ apellidos; 
    }

    @Override
    public String opcionValor() {
        return getNombreCompleto();
    }

    @Override
    public String opcionLlave() {
        return Integer.toString(codigo);
    }

    @Override
    public String toString() {
        return "Persona{" + "codigo=" + codigo + ", nombres=" + nombres + ", apellidos=" + apellidos + ", codigo_rol=" + codigo_rol + '}';
    }

    @Override
    public void validar() throws ExcepcionValidacion {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
