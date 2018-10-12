package uao.almacenamiento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import uao.modelos.Zona;

/**
 * Esta clase debería tener implementados los métodos insertar, actualizar, y
 * eliminar, pero para hacer las pruebas es mucho más fácil guardar todos y
 * eliminar todos
 *
 * @author rad8329
 */
public class ZonaArchivador implements AlamacenamientoInterface<Zona> {

    private final File archivo;
    private String ruta = "./";

    public ZonaArchivador() {
        archivo = new File("zonas.txt");

        try {

            ruta = (new java.io.File(".").getCanonicalPath()) + File.separator;
        } catch (IOException ex) {
            System.err.println(
                    "Error al iniciar el manejador de archivos : " + ex.getMessage()
            );
        }
    }

    @Override
    public ArrayList<Zona> consultarMuchos() {
        ArrayList zonas = new ArrayList<Zona>();

        try {
            FileReader manejador = new FileReader(this.archivo);
            BufferedReader buffer = new BufferedReader(manejador);
            
            String linea;
            int contador = 0;

            while ((linea = buffer.readLine()) != null) {
                contador++;
                String[] datos = linea.split(";");

                try {
                    Zona zona = new Zona(
                            Integer.parseInt(datos[0]),
                            datos[1], datos[2],
                            Boolean.parseBoolean(datos[3]),
                            datos[4]
                    );
                    zonas.add(zona);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.err.println(
                            "Error cargando la línea "
                            + contador
                            + " --> "
                            + ex.getMessage()
                    );
                }
            }

            System.out.println(
                    "Las zonas fueron cargadas desde el archivo : "
                    + ruta
                    + "zonas.txt"
            );
        } catch (IOException ex) {
            System.err.println(
                    "Error al cargar el archivo : "
                    + ruta + "zona.txt --> "
                    + ex.getMessage()
            );
        }

        return zonas;
    }

    public void guardarTodas(ArrayList<Zona> datos) {
        String linea;

        try {
            FileWriter manejador = new FileWriter(this.archivo);

            BufferedWriter buffer = new BufferedWriter(manejador);

            for (int i = 0; i < datos.size(); i++) {
                Zona zona = datos.get(i);
                linea = String.format(
                        "%d;%s;%s;%s;%s\r\n",
                        zona.getCodigo_zona(),
                        //Quitamos el ; que nos puede hacer fallar
                        zona.getNombre().replace(";", ""),
                        //Quitamos el ; que nos puede hacer fallar
                        zona.getDescripcion().replace(";", ""),
                        Boolean.toString(zona.tieneControl_de_acceso()),
                        //Quitamos el ; que nos puede hacer fallar
                        zona.getDireccion_ip_cerradura().replace(";", "")
                );

                buffer.write(linea);
            }

            buffer.close();
        } catch (IOException ex) {
            System.err.println(
                    "Error al guardar el archivo : "
                    + ruta + "zona.txt --> "
                    + ex.getMessage()
            );
        }

        System.out.println(
                "Las zonas fueron guardados en el archivo : "
                + ruta
                + "zonas.txt"
        );
    }

    @Override
    public boolean insertar(Zona modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean actualizar(Zona modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean eliminar(Zona modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
