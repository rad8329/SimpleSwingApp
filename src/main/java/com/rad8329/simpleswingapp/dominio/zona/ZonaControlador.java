package com.rad8329.simpleswingapp.dominio.zona;

import com.rad8329.simpleswingapp.Aplicacion;
import com.rad8329.simpleswingapp.dominio.comun.excepcion.ExcepcionValidacion;
import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorCrudInterface;
import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorTablaModeloInterface;
import java.util.ArrayList;
import java.util.List;
import com.rad8329.simpleswingapp.dominio.comun.repositorio.RepositorioCrudInterface;
import com.rad8329.simpleswingapp.dominio.comun.controlador.ControladorVistaInterface;
import com.rad8329.simpleswingapp.dominio.comun.vista.VistaModeloInterface;

public abstract class ZonaControlador implements ControladorCrudInterface<Zona>,
        ControladorTablaModeloInterface<Zona>,
        ControladorVistaInterface {

    private final RepositorioCrudInterface<Zona> repositorio;
    private final List<Zona> zonas;
    private VistaModeloInterface vista;
    private final String titulo;

    public ZonaControlador(String titulo) {
        this.titulo = titulo;
        this.repositorio = (RepositorioCrudInterface) Aplicacion.repositorio("zonas");
        this.zonas = new ArrayList<>();

        this.cargarTodoLosRegistros();
    }

    @Override
    public boolean removerRegistro(int indice) {
        Zona zona = zonas.get(indice);

        if (repositorio.eliminar(zona)) {
            zonas.remove(indice);

            vista.getModelo().actualizarCuandoSeElimina(indice);

            return true;
        }

        return false;
    }

    @Override
    public boolean agregarRegistro(Zona entidad) throws ExcepcionValidacion {
        entidad.validar();

        if (repositorio.insertar(entidad)) {
            zonas.add(entidad);
            vista.getModelo().actualizarCuandoSeInserta(zonas.size() - 1);

            return true;
        }

        return false;
    }

    @Override
    public boolean actualizarRegistro(int indice, Zona entidad) throws ExcepcionValidacion {
        entidad.validar();

        Zona zonaActual = conseguirRegistro(indice);

        if (repositorio.actualizar(zonaActual, entidad)) {
            //Actualizamos las propiedades
            zonaActual.setCodigo_zona(entidad.getCodigo_zona());
            zonaActual.setNombre(entidad.getNombre());
            zonaActual.setDescripcion(entidad.getDescripcion());
            zonaActual.setControl_de_acceso(entidad.tieneControl_de_acceso());
            zonaActual.setDireccion_ip_cerradura(entidad.getDireccion_ip_cerradura());

            vista.getModelo().actualizarCuandoSeActualiza(indice);

            return true;
        }

        return false;
    }

    @Override
    public Zona conseguirRegistro(int indice) {
        return zonas.get(indice);
    }

    public final void cargarTodoLosRegistros() {

        repositorio.consultarTodo().forEach((zona) -> {
            zonas.add(zona);
        });
    }

    @Override
    public int contarRegistros() {
        return zonas.size();
    }

    @Override
    public abstract VistaModeloInterface renderizar();

    @Override
    public String getTitulo() {
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
        return true;
    }
}
