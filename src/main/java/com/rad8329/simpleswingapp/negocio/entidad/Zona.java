package com.rad8329.simpleswingapp.negocio.entidad;

import com.rad8329.simpleswingapp.negocio.excepcion.ValidacionExcepcion;
import org.apache.commons.validator.routines.InetAddressValidator;

public class Zona implements EntidadInterface {

    private int codigo_zona;
    private String nombre;
    private String descripcion;
    private boolean control_de_acceso;
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

    public int getCodigoZona() {
        return codigo_zona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Boolean tieneControlDeAcceso() {
        return control_de_acceso;
    }

    public String getDireccionIpCerradura() {
        return direccion_ip_cerradura;
    }

    public void setCodigoZona(int codigo_zona) {
        this.codigo_zona = codigo_zona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setControlDeAcceso(Boolean control_de_acceso) {
        this.control_de_acceso = control_de_acceso;
    }

    public void setDireccionIpCerradura(String direccion_ip_cerradura) {
        this.direccion_ip_cerradura = direccion_ip_cerradura;
    }

    @Override
    public void validar() throws ValidacionExcepcion {
        if (codigo_zona <= 0) {
            throw new ValidacionExcepcion("El código debe ser mayor a cero");
        }

        if (nombre.isEmpty()) {
            throw new ValidacionExcepcion("El nombre no debe ser vacio");
        }

        if (descripcion.isEmpty()) {
            throw new ValidacionExcepcion("La descripción no debe ser vacio");
        }

        if (control_de_acceso
                && direccion_ip_cerradura.isEmpty()) {
            throw new ValidacionExcepcion("La dirección IP no debe ser vacio");
        }

        if (!control_de_acceso
                && !direccion_ip_cerradura.isEmpty()) {
            throw new ValidacionExcepcion("El control de acceso debe estar habilitado");
        }

        if (control_de_acceso
                && !direccion_ip_cerradura.isEmpty()
                && !InetAddressValidator.getInstance().isValid(direccion_ip_cerradura)) {
            throw new ValidacionExcepcion("La dirección IP no es válida");
        }
    }

    @Override
    public String toString() {
        return "Zona{" + "codigo_zona=" + codigo_zona + ", nombre=" + nombre
                + ", descripcion=" + descripcion + ", control_de_acceso="
                + control_de_acceso + ", direccion_ip_cerradura="
                + direccion_ip_cerradura + '}';
    }
}
