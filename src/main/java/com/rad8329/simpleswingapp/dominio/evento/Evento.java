package com.rad8329.simpleswingapp.dominio.evento;

import com.rad8329.simpleswingapp.dominio.dependencia.Dependencia;
import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadOpcionableInterface;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadValidadorInterface;
import com.rad8329.simpleswingapp.dominio.programa.Programa;

public final class Evento extends EntidadOpcionableInterface implements EntidadValidadorInterface {

    private int consecutivo;
    private String nombre;
    private String descripcion;
    private int codigo_programa;
    private int codigo_dependencia;

    private Programa programa;
    private Dependencia dependencia;

    public Evento(
            String nombre,
            String descripcion,
            Programa programa,
            Dependencia dependencia
    ) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.setPrograma(programa);
        this.setDependencia(dependencia);

    }

    public Evento(
            int consecutivo,
            String nombre,
            String descripcion,
            Programa programa,
            Dependencia dependencia
    ) {
        this.consecutivo = consecutivo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.setPrograma(programa);
        this.setDependencia(dependencia);
    }

    public Evento(int consecutivo, String nombre) {
        this.consecutivo = consecutivo;
        this.nombre = nombre;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCodigo_programa() {
        return codigo_programa;
    }

    public int getCodigo_dependencia() {
        return codigo_dependencia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
        if (this.programa != null) {
            this.codigo_programa = programa.getCodigo();
        }
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
        if (this.dependencia != null) {
            this.codigo_dependencia = dependencia.getCodigo();
        }
    }

    public Programa getPrograma() {
        return programa;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    @Override
    public String toString() {
        return "Evento{" + "consecutivo=" + consecutivo + ", nombre=" + nombre
                + ", descripcion=" + descripcion + ", codigo_programa="
                + codigo_programa + ", codigo_dependencia=" + codigo_dependencia
                + ", programa=" + programa + ", dependencia=" + dependencia
                + '}';
    }

    @Override
    public void validar() throws ExcepcionValidacion {

        if (nombre.isEmpty()) {
            throw new ExcepcionValidacion("El nombre no debe ser vacio");
        }

        if (descripcion.isEmpty()) {
            throw new ExcepcionValidacion("La descripción no debe ser vacio");
        }

        if (codigo_dependencia > 0 && codigo_programa > 0) {
            throw new ExcepcionValidacion("El evento no puede depender de un programa académico y depdenencia a la vez");
        }
    }

    @Override
    public String opcionValor() {
        return nombre;
    }

    @Override
    public String opcionLlave() {
        return Integer.toString(consecutivo);
    }
}
