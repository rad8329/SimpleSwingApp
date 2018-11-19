package com.rad8329.simpleswingapp.dominio.evento;

import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorReporteInterface;
import com.rad8329.simpleswingapp.Aplicacion;
import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorTablaModeloInterface;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import com.rad8329.simpleswingapp.gui.evento.EventoReportePanel;
import com.rad8329.simpleswingapp.dominio.reporte.ReporteBaseRepositorio;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioCrudInterface;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorVistaInterface;
import com.rad8329.simpleswingapp.dominio.comun.vista.VistaModeloInterface;

public abstract class EventoReporteControlador implements ControladorVistaInterface, 
        ControladorTablaModeloInterface<JSONObject>,
        ControladorReporteInterface<EventoReporteValidador> {

    private final ReporteBaseRepositorio repositorio;
    private EventoReportePanel vista;
    private final List<JSONObject> registros;

    public EventoReporteControlador() {
        this.repositorio = (ReporteBaseRepositorio) Aplicacion.repositorio("reportes");
        this.registros = new ArrayList<>();
    }

    public List<Evento> listaDeEventos() {
        return ((RepositorioCrudInterface) Aplicacion.repositorio("eventos")).consultarTodo();
    }

    @Override
    public abstract VistaModeloInterface renderizar();

    @Override
    public boolean esLanzable() {
        return true;
    }

    @Override
    public int contarRegistros() {
        return registros.size();
    }

    @Override
    public JSONObject conseguirRegistro(int fila) {
        return registros.get(fila);
    }

    @Override
    public void buscar(EventoReporteValidador criterio) throws ExcepcionValidacion {

        criterio.validar();

        registros.removeAll(registros);

        repositorio.consultarAsistenciaEvento(criterio).forEach((registro) -> {

            registros.add(registro);
        });

        vista.getModelo().actualizarCuandoSeInserta(registros.size() - 1);
    }

    @Override
    public void limpiar() {        
        registros.removeAll(registros);
        vista.getModelo().actualizarCuandoSeInserta(registros.size() - 1);
    }

    @Override
    public String getTitulo() {
        return "Asistencia a evento";
    }

    public EventoReportePanel getVista() {
        return vista;
    }

    public void setVista(EventoReportePanel vista) {
        this.vista = vista;
    }    
}
