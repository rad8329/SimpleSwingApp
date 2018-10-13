package main.java.uao.asiscontrol.negocio.gui.controles;

import main.java.uao.asiscontrol.negocio.repositorio.ZonaArchivoRepositorio;
import main.java.uao.asiscontrol.negocio.excepcion.ValidacionExcepcion;
import main.java.uao.asiscontrol.negocio.entidad.Zona;
import java.util.ArrayList;
import main.java.uao.asiscontrol.negocio.gui.modelos.ZonaTablaModelo;
import main.java.uao.asiscontrol.negocio.gui.vistas.ZonaPanel;
import javax.swing.JPanel;

/**
 *
 * @author rad8329
 */
public class ZonaControlador implements ControladorInterface<Zona, ZonaPanel> {

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

        repositorio.consultarTodo().forEach((zona) -> {
            zonas.add(zona);
        });
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
