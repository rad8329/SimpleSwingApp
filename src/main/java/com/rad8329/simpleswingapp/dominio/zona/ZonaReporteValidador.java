package com.rad8329.simpleswingapp.dominio.zona;

import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadValidadorInterface;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import com.rad8329.simpleswingapp.dominio.reporte.ReporteFormulario;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ZonaReporteValidador extends ReporteFormulario implements EntidadValidadorInterface {

    private int codigo_zona;

    public ZonaReporteValidador(int codigo_zona, String fecha_inicio, String fecha_final) {
        super(fecha_inicio, fecha_final);
        this.codigo_zona = codigo_zona;        
    }

    public int getCodigo_zona() {
        return codigo_zona;
    }

    public void setCodigo_zona(int codigo_zona) {
        this.codigo_zona = codigo_zona;
    }


    @Override
    public void validar() throws ExcepcionValidacion {
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");

        if (codigo_zona == 0) {
            throw new ExcepcionValidacion("la zona es necesaria");
        }
        try {
            format.parse(getFecha_inicio());
        } catch (ParseException pe) {
            throw new ExcepcionValidacion("Fecha inicio debe tener el formato yyyy-MM-dd HH:mm");
        }

        if (getFecha_final() != null && !getFecha_final().isEmpty()) {
            try {
                format.parse(getFecha_final());
            } catch (ParseException pe) {
                throw new ExcepcionValidacion("Fecha fin debe tener el formato yyyy-MM-dd HH:mm");
            }
        }
    }
}
