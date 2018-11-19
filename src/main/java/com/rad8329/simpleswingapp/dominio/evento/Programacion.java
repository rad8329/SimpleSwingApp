package com.rad8329.simpleswingapp.dominio.evento;

import com.rad8329.simpleswingapp.dominio.zona.Zona;
import com.rad8329.simpleswingapp.dominio.evento.Evento;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadValidadorInterface;

public final class Programacion implements EntidadValidadorInterface {

    private final int consecutivo;
    private int codigo_zona;
    private String fecha_inicio;
    private String fecha_fin;
    private int cod_evento;
    private String actividades;

    private Zona zona;
    private Evento evento;

    public Programacion(int consecutivo,
            String fecha_inicio,
            String fecha_fin,
            String actividades,
            Evento evento,
            Zona zona) {
        this.consecutivo = consecutivo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.actividades = actividades;
        this.setEvento(evento);
        this.setZona(zona);
    }

    public Programacion(int consecutivo,
            String fecha_inicio,
            String fecha_fin,
            Evento evento,
            Zona zona) {
        this.consecutivo = consecutivo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.setEvento(evento);
        this.setZona(zona);
    }

    public Programacion(String fecha_inicio,
            String fecha_fin,
            String actividades,
            Evento evento,
            Zona zona) {
        this.consecutivo = 0;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.actividades = actividades;
        this.setEvento(evento);
        this.setZona(zona);
    }

    public Programacion(String fecha_inicio,
            String fecha_fin,
            Evento evento,
            Zona zona) {
        this.consecutivo = 0;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.setEvento(evento);
        this.setZona(zona);
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public int getCodigo_zona() {
        return codigo_zona;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getCod_evento() {
        return cod_evento;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        if (zona != null) {
            codigo_zona = zona.getCodigo_zona();
        }

        this.zona = zona;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        if (evento != null) {
            cod_evento = evento.getConsecutivo();
        }

        this.evento = evento;
    }

    @Override
    public void validar() throws ExcepcionValidacion {
        if (codigo_zona == 0) {
            throw new ExcepcionValidacion("La zona es necesaria");
        }

        if (fecha_inicio.isEmpty()) {
            throw new ExcepcionValidacion("Fecha inicio es necesario");
        }

        if (fecha_fin.isEmpty()) {
            throw new ExcepcionValidacion("Fecha fin es necesario");
        }
        
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            format.parse(fecha_inicio);
        } catch (ParseException pe) {
            throw new ExcepcionValidacion("Fecha inicio debe tener el formato yyyy-MM-dd HH:mm");
        }

        try {
            format.parse(fecha_fin);
        } catch (ParseException pe) {
            throw new ExcepcionValidacion("Fecha fin debe tener el formato yyyy-MM-dd HH:mm");
        }
    }

    @Override
    public String toString() {
        return "Programacion{" + "consecutivo=" + consecutivo + ", codigo_zona="
                + codigo_zona + ", fecha_inicio=" + fecha_inicio + ", fecha_fin="
                + fecha_fin + ", cod_evento=" + cod_evento + ", actividades="
                + actividades + '}';
    }
}
