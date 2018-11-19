package com.rad8329.simpleswingapp.dominio.evento;

import com.rad8329.simpleswingapp.Aplicacion;
import com.rad8329.simpleswingapp.dominio.dependencia.Dependencia;
import com.rad8329.simpleswingapp.dominio.programa.Programa;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorCrudInterface;
import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorTablaModeloInterface;
import java.util.ArrayList;
import java.util.List;

import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioCrudInterface;
import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorVistaInterface;
import com.rad8329.simpleswingapp.dominio.comun.vista.VistaModeloInterface;

public abstract class EventoControlador implements ControladorCrudInterface<Evento>,
        ControladorTablaModeloInterface<Evento>,
        ControladorVistaInterface {

    private final RepositorioCrudInterface<Evento> repositorio;
    private final List<Evento> eventos;
    private VistaModeloInterface vista;
    private final String titulo;

    public EventoControlador(String titulo) {
        this.titulo = titulo;
        this.repositorio = (RepositorioCrudInterface) Aplicacion.repositorio("eventos");
        this.eventos = new ArrayList<>();

        this.cargarTodoLosRegistros();
    }

    public List<Programa> listaDeProgramas() {
        return ((RepositorioCrudInterface) Aplicacion.repositorio("programas")).consultarTodo();
    }

    public List<Dependencia> listaDeDependencias() {
        return ((RepositorioCrudInterface) Aplicacion.repositorio("dependencias")).consultarTodo();
    }

    @Override
    public boolean removerRegistro(int indice) {
        Evento evento = eventos.get(indice);

        if (repositorio.eliminar(evento)) {
            eventos.remove(indice);

            vista.getModelo().actualizarCuandoSeElimina(indice);

            return true;
        }

        return false;
    }

    @Override
    public boolean agregarRegistro(Evento entidad) throws ExcepcionValidacion {
        entidad.validar();

        if (repositorio.insertar(entidad)) {
            eventos.add(entidad);
            vista.getModelo().actualizarCuandoSeInserta(eventos.size() - 1);

            return true;
        }

        return false;
    }

    @Override
    public boolean actualizarRegistro(int indice, Evento entidad) throws ExcepcionValidacion {
        entidad.validar();

        Evento eventoActual = conseguirRegistro(indice);

        if (repositorio.actualizar(eventoActual, entidad)) {
            //Actualizamos las propiedades
            eventoActual.setNombre(entidad.getNombre());
            eventoActual.setDescripcion(entidad.getDescripcion());

            vista.getModelo().actualizarCuandoSeActualiza(indice);

            return true;
        }

        return false;
    }

    @Override
    public Evento conseguirRegistro(int indice) {
        return eventos.get(indice);
    }

    public final void cargarTodoLosRegistros() {

        repositorio.consultarTodo().forEach((evento) -> {
            eventos.add(evento);
        });
    }

    @Override
    public int contarRegistros() {
        return eventos.size();
    }

    @Override
    abstract public VistaModeloInterface renderizar();

    public VistaModeloInterface getVista() {
        return vista;
    }

    public void setVista(VistaModeloInterface vista) {
        this.vista = vista;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public boolean esLanzable() {
        return true;
    }
}
