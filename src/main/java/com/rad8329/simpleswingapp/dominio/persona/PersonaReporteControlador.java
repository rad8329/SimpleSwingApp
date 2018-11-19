package com.rad8329.simpleswingapp.dominio.persona;

import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorReporteInterface;
import com.rad8329.simpleswingapp.Aplicacion;
import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorTablaModeloInterface;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import com.rad8329.simpleswingapp.dominio.reporte.ReporteBaseRepositorio;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioCrudInterface;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorVistaInterface;
import com.rad8329.simpleswingapp.dominio.comun.vista.VistaModeloInterface;

public abstract class PersonaReporteControlador implements ControladorVistaInterface,
        ControladorTablaModeloInterface<JSONObject>,
        ControladorReporteInterface<PersonaReporteValidador> {

    private final ReporteBaseRepositorio repositorio;
    private VistaModeloInterface vista;
    private final List<JSONObject> registros;

    public PersonaReporteControlador() {
        this.repositorio = (ReporteBaseRepositorio) Aplicacion.repositorio("reportes");
        this.registros = new ArrayList<>();
    }

    public List<Persona> listaDePersonas() {

        return ((RepositorioCrudInterface) Aplicacion.repositorio("personas")).consultarTodo();
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
    public void buscar(PersonaReporteValidador criterio) throws ExcepcionValidacion {
        criterio.validar();
        registros.removeAll(registros);

        repositorio.consultarRecorridoEstudiante(criterio).forEach((registro) -> {

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
        return "Recorrido de un estudiante";
    }

    public VistaModeloInterface getVista() {
        return vista;
    }

    public void setVista(VistaModeloInterface vista) {
        this.vista = vista;
    }
}
