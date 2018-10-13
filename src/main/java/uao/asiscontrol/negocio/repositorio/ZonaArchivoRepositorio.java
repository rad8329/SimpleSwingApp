package main.java.uao.asiscontrol.negocio.repositorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import main.java.uao.asiscontrol.negocio.entidad.Zona;

/**
 * El manejo de archivos hechos acá no es el mejor, pero es simplemente para
 * hacer el primer ejemplo de repositorio
 *
 * @author rad8329
 */
public class ZonaArchivoRepositorio implements RepositorioInterface<Zona> {

    private final File archivo;
    private final String nombreArchivo;

    public ZonaArchivoRepositorio(String tituloArchivo) {
        nombreArchivo = tituloArchivo + ".txt";

        archivo = new File(nombreArchivo);
    }

    @Override
    public ArrayList<Zona> consultarTodo() {
        ArrayList<Zona> zonas = new ArrayList();//Limpiamos

        try {
            FileReader manejador = new FileReader(this.archivo);

            try (BufferedReader buffer = new BufferedReader(manejador)) {
                String linea;
                int contador = 0;

                while ((linea = buffer.readLine()) != null) {
                    contador++;

                    try {
                        zonas.add(lineaCsvAZona(linea));
                    } catch (ArrayIndexOutOfBoundsException | java.lang.NumberFormatException ex) {

                        java.util.logging.Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                                java.util.logging.Level.WARNING,
                                String.format(
                                        "[%s]Error cargando la línea %d: %s",
                                        nombreArchivo,
                                        contador,
                                        ex.getMessage()
                                )
                        );
                    }
                }
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                        java.util.logging.Level.SEVERE,
                        ex.getMessage()
                );
            }

            java.util.logging.Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                    java.util.logging.Level.INFO,
                    String.format(
                            "[%s]Las zonas fueron cargadas desde el archivo",
                            nombreArchivo
                    )
            );

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                    java.util.logging.Level.WARNING,
                    ex.getMessage()
            );
        }

        return zonas;
    }

    @Override
    public boolean insertar(Zona modelo) {
        try {
            FileWriter manejador = new FileWriter(this.archivo, true);

            try (BufferedWriter buffer = new BufferedWriter(manejador)) {
                buffer.append(zonaALineaCsv(modelo));
                buffer.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                        java.util.logging.Level.SEVERE,
                        ex.getMessage()
                );
            }

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    ex.getMessage()
            );
        }

        java.util.logging.Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                java.util.logging.Level.INFO,
                String.format(
                        "[%s]La zona fue guardada en el archivo",
                        nombreArchivo
                )
        );

        return true;
    }

    /**
     * Si se quiere eliminar la línea, se pasa el parametro contenidoNuevo vacío
     * 
     * @param lineaABuscar
     * @param contenidoNuevo
     * @return 
     */
    private boolean actualizarLinea(String lineaABuscar, String contenidoNuevo) {

        try {
            File archivoTemporal = new File("temp-" + nombreArchivo);

            FileReader manejadorLectura = new FileReader(this.archivo);
            FileWriter manejadorEscritura = new FileWriter(archivoTemporal);

            try (BufferedReader buffer = new BufferedReader(manejadorLectura);
                    BufferedWriter writer = new BufferedWriter(manejadorEscritura)) {

                String linea;

                while ((linea = buffer.readLine()) != null) {
                    if (linea.trim().contains(lineaABuscar)) {
                        //Si la línea es igual, la cambiamos en el archivo temporal
                        if (contenidoNuevo.isEmpty()) {
                            java.util.logging.Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                                    java.util.logging.Level.INFO,
                                    String.format(
                                            "[%s]La zona fue eliminada con éxito",
                                            nombreArchivo
                                    )
                            );

                        } else {
                            writer.write(contenidoNuevo);

                            java.util.logging.Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                                    java.util.logging.Level.INFO,
                                    String.format(
                                            "[%s]La zona fue actulizada con éxito",
                                            nombreArchivo
                                    )
                            );
                        }
                    } else {
                        writer.write(linea + System.getProperty("line.separator"));
                    }
                }

                writer.close();
            }

            archivoTemporal.renameTo(this.archivo);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ZonaArchivoRepositorio.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    ex.getMessage()
            );
        }

        return true;
    }

    @Override
    public boolean actualizar(Zona anterior, Zona nuevo) {

        actualizarLinea(zonaALineaCsv(anterior).trim(), zonaALineaCsv(nuevo));

        return true;
    }

    @Override
    public boolean eliminar(Zona modelo) {

        actualizarLinea(zonaALineaCsv(modelo).trim(), "");

        return true;
    }

    /**
     * Serializa una zona como CSV
     * 
     * @param zona
     * @return 
     */
    private String zonaALineaCsv(Zona zona) {
        return String.format(
                "%d;%s;%s;%s;%s",
                zona.getCodigo_zona(),
                //Quitamos el ; que nos puede hacer fallar
                zona.getNombre().replace(";", ""),
                //Quitamos el ; que nos puede hacer fallar
                zona.getDescripcion().replace(";", ""),
                Boolean.toString(zona.tieneControl_de_acceso()),
                //Quitamos el ; que nos puede hacer fallar
                zona.getDireccion_ip_cerradura().replace(";", "")
        ) + System.getProperty("line.separator");
    }

    /**
     * Instancia un objeto de tipo Zona a partir de una linea CSV
     * 
     * @param linea
     * @return 
     */
    private Zona lineaCsvAZona(String linea) {
        String[] datos = linea.split(";", -1);

        return new Zona(
                Integer.parseInt(datos[0]),
                datos[1], datos[2],
                Boolean.parseBoolean(datos[3]),
                (String) datos[4]
        );
    }
}
