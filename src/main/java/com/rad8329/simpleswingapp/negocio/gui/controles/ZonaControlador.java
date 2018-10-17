package com.rad8329.simpleswingapp.negocio.gui.controles;

import com.rad8329.simpleswingapp.negocio.entidad.Zona;
import com.rad8329.simpleswingapp.negocio.excepcion.ValidacionExcepcion;
import com.rad8329.simpleswingapp.negocio.gui.modelos.ZonaTablaModelo;
import com.rad8329.simpleswingapp.negocio.gui.vistas.ZonaPanel;
import com.rad8329.simpleswingapp.negocio.repositorio.RepositorioInterface;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author rad8329
 */
public class ZonaControlador implements ControladorInterface<Zona>, LanzadorInterface {

    private final RepositorioInterface<Zona> repositorio;
    private final ArrayList<Zona> zonas;
    private ZonaPanel vista;
    private final String titulo;

    public ZonaControlador(String titulo, RepositorioInterface<Zona> repositorio) {
        this.titulo = titulo;
        this.repositorio = repositorio;
        this.zonas = new ArrayList<>();
    }

    @Override
    public boolean removerRegistro(int fila) {
        Zona zona = zonas.get(fila);

        if (repositorio.eliminar(zona)) {
            zonas.remove(fila);

            vista.getModelo().fireTableRowsDeleted(fila, fila);
            
            return true;
        }
        
        return false;
    }

    @Override
    public boolean agregarRegistro(Zona modelo) throws ValidacionExcepcion {
        modelo.validar();
        zonas.add(modelo);

        if (repositorio.insertar(modelo)) {
            vista.getModelo().fireTableRowsInserted(zonas.size() - 1, zonas.size() - 1);
            
            return true;
        }
        
        return false;
    }

    @Override
    public boolean actualizarRegistro(int fila, Zona modelo) throws ValidacionExcepcion {
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
            return true;            
        }
        
        return false;
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
