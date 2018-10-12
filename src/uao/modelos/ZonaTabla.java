package uao.modelos;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ZonaTabla extends AbstractTableModel implements TablaModeloInterface<Zona>{

    private final String[] columnas = {
        "#",
        "Código",
        "Nombre",
        "Descripción",
        "CA",
        "IP CA"
    };

    final Class[] clasesColumnas = {
        Integer.class,
        String.class,
        String.class,
        String.class,
        Boolean.class,
        String.class
    };

    final private ArrayList<Zona> datos = new ArrayList();

    @Override
    public Zona conseguir(int fila) {
        Zona zona = (Zona) datos.get(fila);

        return zona;
    }


    @Override
    public void remover(int fila){
        datos.remove(fila);

        fireTableRowsDeleted(fila, fila);
    }
    
    @Override
    public void agregar(Zona modelo) {

        datos.add(modelo);

        fireTableRowsInserted(datos.size() - 1, datos.size() - 1);
    }
    
    @Override
    public void actualizar(int fila) {
        //Actualizamos la fila de tabla
        fireTableRowsUpdated(fila, fila);
    }

    @Override
    public String getColumnName(int columna) {
        return columnas[columna];
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Class getColumnClass(int columna) {
        return clasesColumnas[columna];
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Zona zona = conseguir(fila);

        if (zona instanceof Zona) {
            switch (columna) {
                case 0:
                    return fila + 1;
                case 1:
                    return zona.getCodigo_zona();
                case 2:
                    return zona.getNombre();
                case 3:
                    return zona.getDescripcion();

                case 4:
                    return zona.tieneControl_de_acceso();
                case 5:
                    return zona.getDireccion_ip_cerradura();
                default:
                    return null;
            }
        }

        return null;
    }

    @Override
    public ArrayList<Zona> getDatos() {
        return datos;
    }
}
