package com.rad8329.simpleswingapp.dominio.reporte;

public class ReporteFormulario {

    private String fecha_inicio;
    private String fecha_final;

    public ReporteFormulario(String fecha_inicio, String fecha_final) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }
}
