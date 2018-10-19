package com.rad8329.simpleswingapp.negocio.gui.controles;

import com.rad8329.simpleswingapp.negocio.entidad.Zona;
import com.rad8329.simpleswingapp.negocio.excepcion.ValidacionExcepcion;
import com.rad8329.simpleswingapp.negocio.gui.modelos.ZonaTablaModelo;
import com.rad8329.simpleswingapp.negocio.gui.vistas.ZonaPanel;
import com.rad8329.simpleswingapp.negocio.repositorio.RepositorioInterface;

import javax.swing.JPanel;
import java.util.ArrayList;

/**
 * @author rad8329
 */
public class ZonaControlador implements ControladorInterface<Zona>, LanzadorInterface {

    private final RepositorioInterface<Zona> repositorio;
    private final ArrayList<Zona> registros;
    private ZonaPanel vista;
    private final String titulo;

    public ZonaControlador(String titulo, RepositorioInterface<Zona> repositorio) {
        this.titulo = titulo;
        this.repositorio = repositorio;
        this.registros = new ArrayList<>();
    }

    @Override
    public boolean removerRegistro(int fila) {
        Zona zona = registros.get(fila);

        if (repositorio.eliminar(zona)) {
            registros.remove(fila);

            vista.getModelo().fireTableRowsDeleted(fila, fila);
            
            return true;
        }
        
        return false;
    }

    @Override
    public boolean agregarRegistro(Zona modelo) throws ValidacionExcepcion {
        modelo.validar();
        registros.add(modelo);

        if (repositorio.insertar(modelo)) {
            vista.getModelo().fireTableRowsInserted(registros.size() - 1, registros.size() - 1);
            
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
            zonaActual.setCodigoZona(modelo.getCodigoZona());
            zonaActual.setNombre(modelo.getNombre());
            zonaActual.setDescripcion(modelo.getDescripcion());
            zonaActual.setControlDeAcceso(modelo.tieneControlDeAcceso());
            zonaActual.setDireccionIpCerradura(modelo.getDireccionIpCerradura());

            vista.getModelo().fireTableRowsUpdated(fila, fila);
            
            return true;            
        }
        
        return false;
    }

    @Override
    public Zona conseguirRegistro(int fila) {
        return registros.get(fila);
    }

    @Override
    public void cargarTodoLosRegistros() {

        registros.addAll(repositorio.consultarTodo());
    }

    @Override
    public int contarRegistros() {
        return registros.size();
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
