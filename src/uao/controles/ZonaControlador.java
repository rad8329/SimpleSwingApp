package uao.controles;

import uao.almacenamiento.ZonaArchivador;
import uao.excepciones.ValidacionExcepcion;
import uao.modelos.Zona;
import uao.modelos.ZonaTabla;
import java.util.ArrayList;

/**
 *
 * @author rad8329
 */
public class ZonaControlador implements ControladorInterface<Zona> {

    public static final ZonaTabla TABLA = new ZonaTabla();
    private static final ZonaArchivador ALMACENAMIENTO = new ZonaArchivador();

    public ZonaControlador() {
    }

    @Override
    public void remover(int fila) {
        TABLA.remover(fila);
        ALMACENAMIENTO.guardarTodas(TABLA.getDatos());
    }

    @Override
    public void agregar(Zona modelo, boolean guardar) throws ValidacionExcepcion {
        modelo.validar();

        TABLA.agregar(modelo);

        if (guardar) {
            ALMACENAMIENTO.guardarTodas(TABLA.getDatos());
        }
    }

    @Override
    public void actualizar(int fila, Zona modelo) throws ValidacionExcepcion {
        modelo.validar();

        Zona zonaActual = conseguir(fila);

        //Actualizamos las propiedades
        zonaActual.setCodigo_zona(modelo.getCodigo_zona());
        zonaActual.setNombre(modelo.getNombre());
        zonaActual.setDescripcion(modelo.getDescripcion());
        zonaActual.setControl_de_acceso(modelo.tieneControl_de_acceso());
        zonaActual.setDireccion_ip_cerradura(modelo.getDireccion_ip_cerradura());

        ALMACENAMIENTO.guardarTodas(TABLA.getDatos());

        TABLA.actualizar(fila);
    }

    @Override
    public Zona conseguir(int fila) {
        return TABLA.conseguir(fila);
    }

    @Override
    public void cargarTodo() {
        ArrayList<Zona> zonas = ALMACENAMIENTO.consultarMuchos();

        for (Zona zona : zonas) {
            TABLA.agregar(zona);
        }
    }
}
