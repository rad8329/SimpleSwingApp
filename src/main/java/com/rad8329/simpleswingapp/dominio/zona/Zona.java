package com.rad8329.simpleswingapp.dominio.zona;

import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadOpcionableInterface;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import org.apache.commons.validator.routines.InetAddressValidator;
import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadValidadorInterface;

public final class Zona extends EntidadOpcionableInterface implements EntidadValidadorInterface {

    private int codigo_zona;
    private String nombre;
    private String descripcion;
    private Boolean control_de_acceso;
    private String direccion_ip_cerradura;

    public Zona(int codigo_zona,
            String nombre,
            String descripcion,
            boolean control_de_acceso,
            String direccion_ip_cerradura
    ) {
        this.codigo_zona = codigo_zona;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.control_de_acceso = control_de_acceso;
        this.direccion_ip_cerradura = direccion_ip_cerradura;
    }

    public Zona(int codigo_zona, String nombre) {
        this.codigo_zona = codigo_zona;
        this.nombre = nombre;
    }

    public int getCodigo_zona() {
        return codigo_zona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Boolean tieneControl_de_acceso() {
        return control_de_acceso;
    }

    public String getDireccion_ip_cerradura() {
        return direccion_ip_cerradura;
    }

    public void setCodigo_zona(int codigo_zona) {
        this.codigo_zona = codigo_zona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setControl_de_acceso(Boolean control_de_acceso) {
        this.control_de_acceso = control_de_acceso;
    }

    public void setDireccion_ip_cerradura(String direccion_ip_cerradura) {
        this.direccion_ip_cerradura = direccion_ip_cerradura;
    }

    @Override
    public void validar() throws ExcepcionValidacion {
        if (codigo_zona <= 0) {
            throw new ExcepcionValidacion("El código debe ser mayor a cero");
        }

        if (this.getNombre().isEmpty()) {
            throw new ExcepcionValidacion("El nombre no debe ser vacio");
        }

        if (this.getDescripcion().isEmpty()) {
            throw new ExcepcionValidacion("La descripción no debe ser vacio");
        }

        if (this.tieneControl_de_acceso()
                && this.getDireccion_ip_cerradura().isEmpty()) {
            throw new ExcepcionValidacion("La dirección IP no debe ser vacio");
        }
        if (control_de_acceso
                && direccion_ip_cerradura.isEmpty()) {
            throw new ExcepcionValidacion("La dirección IP no debe ser vacio");
        }

        if (!control_de_acceso
                && !direccion_ip_cerradura.isEmpty()) {
            throw new ExcepcionValidacion("El control de acceso debe estar habilitado");
        }

        if (control_de_acceso
                && !direccion_ip_cerradura.isEmpty()
                && !InetAddressValidator.getInstance().isValid(direccion_ip_cerradura)) {
            throw new ExcepcionValidacion("La dirección IP no es válida");
        }
    }

    @Override
    public String opcionValor() {
        return nombre;
    }

    @Override
    public String opcionLlave() {
        return Integer.toString(codigo_zona);
    }

    @Override
    public String toString() {
        return "Zona{" + "codigo_zona=" + codigo_zona + ", nombre=" + nombre
                + ", descripcion=" + descripcion + ", control_de_acceso="
                + control_de_acceso + ", direccion_ip_cerradura="
                + direccion_ip_cerradura + '}';
    }
}
