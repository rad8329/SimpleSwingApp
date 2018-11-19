package com.rad8329.simpleswingapp.dominio.persona;

import com.rad8329.simpleswingapp.dominio.comun.entidad.EntidadValidadorInterface;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import com.rad8329.simpleswingapp.dominio.reporte.ReporteFormulario;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonaReporteValidador extends ReporteFormulario implements EntidadValidadorInterface {

    int codigo_persona;

    public PersonaReporteValidador(int codigo_persona, String fecha_inicio, String fecha_final) {
        super(fecha_inicio, fecha_final);
        this.codigo_persona = codigo_persona;
    }

    public int getCodigo_persona() {
        return codigo_persona;
    }

    public void setCodigo_persona(int codigo_persona) {
        this.codigo_persona = codigo_persona;
    }

    @Override
    public void validar() throws ExcepcionValidacion {
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");

        if (codigo_persona == 0) {
            throw new ExcepcionValidacion("La persona es necesaria");
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
