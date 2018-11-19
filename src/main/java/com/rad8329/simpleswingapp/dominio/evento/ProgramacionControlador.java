package com.rad8329.simpleswingapp.dominio.evento;

import com.rad8329.simpleswingapp.Aplicacion;
import com.rad8329.simpleswingapp.dominio.evento.Evento;
import com.rad8329.simpleswingapp.dominio.zona.Zona;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorCrudInterface;
import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorTablaModeloInterface;
import java.util.ArrayList;
import java.util.List;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioCrudInterface;
import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorVistaInterface;
import com.rad8329.simpleswingapp.dominio.comun.vista.VistaModeloInterface;

public abstract class ProgramacionControlador implements ControladorCrudInterface<Programacion>,
        ControladorTablaModeloInterface<Programacion>,
        ControladorVistaInterface {

    private final RepositorioCrudInterface<Programacion> repositorio;
    private final List<Programacion> programaciones;
    private VistaModeloInterface vista;
    private Evento evento;
    private final String titulo;

    public ProgramacionControlador(String titulo) {
        this.titulo = titulo;
        this.repositorio = (RepositorioCrudInterface) Aplicacion.repositorio("programaciones");
        this.programaciones = new ArrayList<>();

        this.cargarTodoLosRegistros();
    }

    public List<Zona> listaDeZonas() {
        return ((RepositorioCrudInterface) Aplicacion.repositorio("zonas")).consultarTodo();
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public boolean removerRegistro(int indice) {
        Programacion programacion = programaciones.get(indice);

        if (repositorio.eliminar(programacion)) {
            programaciones.remove(indice);

            vista.getModelo().actualizarCuandoSeElimina(indice);

            return true;
        }

        return false;
    }

    @Override
    public boolean agregarRegistro(Programacion entidad) throws ExcepcionValidacion {
        entidad.validar();

        if (repositorio.insertar(entidad)) {
            programaciones.add(entidad);
            vista.getModelo().actualizarCuandoSeInserta(programaciones.size() - 1);

            return true;
        }

        return false;
    }

    @Override
    public boolean actualizarRegistro(int indice, Programacion entidad) throws ExcepcionValidacion {
        entidad.validar();

        Programacion programacionActual = conseguirRegistro(indice);

        if (repositorio.actualizar(programacionActual, entidad)) {
            //Actualizamos las propiedades
            programacionActual.setFecha_inicio(entidad.getFecha_inicio());
            programacionActual.setFecha_fin(entidad.getFecha_fin());
            programacionActual.setActividades(entidad.getActividades());

            programacionActual.setZona(entidad.getZona());

            vista.getModelo().actualizarCuandoSeActualiza(indice);

            return true;
        }

        return false;
    }

    @Override
    public Programacion conseguirRegistro(int indice) {
        return programaciones.get(indice);
    }

    public final void cargarTodoLosRegistros() {

        repositorio.consultarTodo().forEach((programacion) -> {
            programaciones.add(programacion);
        });
    }

    @Override
    public int contarRegistros() {
        return programaciones.size();
    }

    @Override
    public abstract VistaModeloInterface renderizar();

    @Override
    public String getTitulo() {
        if (evento != null) {
            return evento.getNombre();
        }

        return titulo;
    }

    public VistaModeloInterface getVista() {
        return vista;
    }

    public void setVista(VistaModeloInterface vista) {
        this.vista = vista;
    }

    @Override
    public boolean esLanzable() {
        return false;
    }
}
