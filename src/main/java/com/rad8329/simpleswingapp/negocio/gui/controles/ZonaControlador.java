package com.rad8329.simpleswingapp.negocio.gui.controles;

import com.rad8329.simpleswingapp.negocio.entidad.Zona;
import com.rad8329.simpleswingapp.negocio.excepcion.ValidacionExcepcion;
import com.rad8329.simpleswingapp.negocio.gui.modelos.ZonaTablaModelo;
import com.rad8329.simpleswingapp.negocio.gui.vistas.ZonaPanel;
import com.rad8329.simpleswingapp.negocio.repositorio.ZonaArchivoRepositorio;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author rad8329
 */
public class ZonaControlador implements ControladorInterface<Zona> {

    private final ZonaArchivoRepositorio repositorio;
    private final ArrayList<Zona> zonas;
    private ZonaPanel vista;
    private final String titulo;

    public ZonaControlador(String titulo, ZonaArchivoRepositorio repositorio) {
        this.titulo = titulo;
        this.repositorio = repositorio;
        this.zonas = new ArrayList<>();
    }

    @Override
    public void removerRegistro(int fila) {
        Zona zona = zonas.get(fila);

        if (repositorio.eliminar(zona)) {
            zonas.remove(fila);

            vista.getModelo().fireTableRowsDeleted(fila, fila);
        }
    }

    @Override
    public void agregarRegistro(Zona modelo, boolean guardar) throws ValidacionExcepcion {
        modelo.validar();
        zonas.add(modelo);

        vista.getModelo().fireTableRowsInserted(zonas.size() - 1, zonas.size() - 1);

        if (guardar) {
            repositorio.insertar(modelo);
        }
    }

    @Override
    public void actualizarRegistro(int fila, Zona modelo) throws ValidacionExcepcion {
        modelo.validar();

        Zona zonaActual = conseguirRegistro(fila);

        if (repositorio.actualizar(zonaActual, modelo)) {
            //Actualizamos las propiedades
            zonaActual.setCodigo_zona(modelo.getCodigo_zona());
            zonaActual.setNombre(modelo.getNombre());
            zonaActual.setDescripcion(modelo.getDescripcion());
            zonaActual.setControl_de_acceso(modelo.tieneControl_de_acceso());
            zonaActual.setDireccion_ip_cerradura(modelo.getDireccion_ip_cerradura());

            vista.getModelo().fireTableRowsUpdated(fila, fila);
        }
    }

    @Override
    public Zona conseguirRegistro(int fila) {
        return zonas.get(fila);
    }

    @Override
    public void cargarTodoLosRegistros() {

        zonas.addAll(repositorio.consultarTodo());
    }

    @Override
    public int contarRegistros() {
        return zonas.size();
    }

    @Override
    public JPanel construirPanel() {
        if (vista == null) {
            vista = new ZonaPanel(new ZonaTablaModelo(this), this);
        }

        return vista;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }
}
